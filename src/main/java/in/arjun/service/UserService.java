package in.arjun.service;

import in.arjun.exception.UserNotFoundException;
import in.arjun.model.entity.User;
import in.arjun.model.request.LoginRequest;
import in.arjun.model.request.ResetPasswordRequest;
import in.arjun.model.request.UserRequest;
import in.arjun.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public boolean userRegistration(UserRequest userRequest){
        Optional<User> userByEmail = userRepository.findUserByEmail(userRequest.getEmail());
        if (userByEmail.isPresent())
            return false;
        User user=User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(RandomStringUtils.random(5,"abide12345"))
                .phoneNo(userRequest.getPhoneNo())
                .country(userRequest.getCountry())
                .state(userRequest.getState())
                .city(userRequest.getCity())
                .isFirstTimeLogin("Yes")
                .build();

        User saved = userRepository.save(user);
        if (saved.getId() !=null){
            String subject="User Registration";
            String body= "you are registered successfully" +
                    "\nyour Email :" +saved.getEmail()+
                    "\nplease login with this Password :" +saved.getPassword();
            String to=saved.getEmail();
            emailService.sendEmail(subject,body,to);
            return true;
        }
       return false;
    }

    public Optional<User> userLogin(LoginRequest loginRequest){
        Optional<User> byEmailAndPassword = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        /*if (byEmailAndPassword.isPresent())
            return byEmailAndPassword.get();
        throw  new UserNotFoundException("User Not found with this Email :"+ byEmailAndPassword.get().getEmail());*/
        return byEmailAndPassword;
    }

    public String resetPassword(ResetPasswordRequest resetPasswordRequest){
        Optional<User> userByEmail = userRepository.findByEmailAndPassword(resetPasswordRequest.getEmail(),resetPasswordRequest.getOldPassword());
        if (userByEmail.isEmpty()){
            return "Invalid Credentials";
        }
        User user = userByEmail.get();
        String passwordStatus = user.getIsFirstTimeLogin();
            if (resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmPassword())){
                user.setPassword(resetPasswordRequest.getNewPassword());
                user.setIsFirstTimeLogin("No");
                userRepository.save(user);
                return "password updated successfully, please login...";
            } else {
                return "your new password and confirm password doesn't match";
            }
    }

}
