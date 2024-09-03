package in.arjun.controller;

import in.arjun.exception.UserNotFoundException;
import in.arjun.model.entity.Country;
import in.arjun.model.entity.User;
import in.arjun.model.request.LoginRequest;
import in.arjun.model.request.ResetPasswordRequest;
import in.arjun.model.request.UserRequest;
import in.arjun.service.CountryService;
import in.arjun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CountryService countryService;

    ResetPasswordRequest resetPasswordRequest;

    @GetMapping("/")
    public String index(Model model){
            UserRequest request=new UserRequest();
            model.addAttribute("user",request);
            return "index";
    }
    @GetMapping("/register")
    public String registerPage(Model model){
       User user=new User();
        model.addAttribute("user",user);
        List<Country> countryList = countryService.getAllCountries();
        model.addAttribute("countries",countryList);
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRequest userRequest, Model model){
        boolean isRegistered = userService.userRegistration(userRequest);
        if (isRegistered) {
            model.addAttribute("smsg", "successfully registered, Please login...");
            return "register";
        } else {
            model.addAttribute("emsg", "registration failed");
            return "register";
        }
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") LoginRequest loginRequest, Model model){
        Optional<User> user = userService.userLogin(loginRequest);
        if (user.isEmpty()){
            model.addAttribute("emsg","user not found");
            return "index";
            //throw new UserNotFoundException("User Not found with this Email :" + loginRequest.getEmail());
        }
        if (user.get().getIsFirstTimeLogin().equals("Yes")){
            resetPasswordRequest=new ResetPasswordRequest();
            resetPasswordRequest.setEmail(user.get().getEmail());
            return "redirect:/reset-password";
        } else {
            return "redirect:/dashboard";
        }

    }

    @GetMapping("/reset-password")
    public String resetPassword(Model model){
        model.addAttribute("user",resetPasswordRequest);
        return "reset-password";
    }
 /*   @GetMapping("/reset-page")
    public String passwordResetPage(Model model){
        ResetPasswordRequest resetPasswordRequest=new ResetPasswordRequest();
        resetPasswordRequest.setEmail("malli@gmail.com");
        model.addAttribute("user",resetPasswordRequest);
        return "reset-password";
    }*/

    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute("user") ResetPasswordRequest resetPasswordRequest, Model model){
        String message = userService.resetPassword(resetPasswordRequest);
        model.addAttribute("message",message);
        return "reset-password";
    }
}
