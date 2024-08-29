package in.arjun.controller;

import in.arjun.model.request.LoginRequest;
import in.arjun.model.request.ResetPasswordRequest;
import in.arjun.model.request.UserRequest;
import in.arjun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody UserRequest userRequest){
        boolean isRegistered = userService.userRegistration(userRequest);
        if (isRegistered)
            return new ResponseEntity<>("user registered successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("Duplicate Email",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginUser(LoginRequest loginRequest){
        boolean isLogin = userService.userLogin(loginRequest);
        if (isLogin)
            return new ResponseEntity<>("login failed",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("login successfully",HttpStatus.OK);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> updatePassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        String message = userService.resetPassword(resetPasswordRequest);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
