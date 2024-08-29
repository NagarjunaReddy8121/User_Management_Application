package in.arjun.service;

import in.arjun.model.entity.User;
import in.arjun.model.request.LoginRequest;
import in.arjun.model.request.ResetPasswordRequest;
import in.arjun.model.request.UserRequest;
import in.arjun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordGenerationService generationService;

    @Autowired
    private EmailService emailService;

    public boolean userRegistration(UserRequest userRequest){
        Optional<User> userByEmail = userRepository.findUserByEmail(userRequest.getEmail());
        if (userByEmail.isPresent())
            return false;
        User user=User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(generationService.generateRandomPassword())
                .phoneNo(userRequest.getPhoneNo())
                .country(userRequest.getCountry())
                .state(userRequest.getState())
                .city(userRequest.getCity())
                .resetPassword("No")
                .build();

        User saved = userRepository.save(user);
        if (saved.getId() !=null){
            String subject="User Registration";
            String body= "you are registered successfully" +
                    "please login with this Password :"+saved.getPassword();
            String to=saved.getEmail();
            emailService.sendEmail(subject,body,to);
            return true;
        }
       return false;
    }

    public boolean userLogin(LoginRequest loginRequest){
        Optional<User> byEmailAndPassword = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        return byEmailAndPassword.isEmpty();
    }

    public String resetPassword(ResetPasswordRequest resetPasswordRequest){
        Optional<User> userByEmail = userRepository.findByEmailAndPassword(resetPasswordRequest.getEmail(),resetPasswordRequest.getOldPassword());
        if (userByEmail.isEmpty()){
            return "Invalid Credentials";
        }
        User user = userByEmail.get();
        String passwordStatus = user.getResetPassword();
        if (passwordStatus.equals("No")){
            if (resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmPassword())){
                user.setPassword(resetPasswordRequest.getNewPassword());
                user.setResetPassword("Yes");
                userRepository.save(user);
                return "password updated successfully";
            }
            return "your new password and confirm password doesn't match";
        }
        return "you already updated the password";
    }

}
