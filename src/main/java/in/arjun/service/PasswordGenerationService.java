package in.arjun.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class PasswordGenerationService {

    private static final String HEX_CHARACTERS = "abcdef";

    SecureRandom random=new SecureRandom();


    public String generateRandomPassword(){
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<=5; i++){
            int index = random.nextInt(HEX_CHARACTERS.length());
            stringBuilder.append(HEX_CHARACTERS.charAt(index));
        }
        return stringBuilder.toString();
    }

}
