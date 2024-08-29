package in.arjun.controller;

import in.arjun.model.entity.Country;
import in.arjun.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/add")
    public ResponseEntity<String> saveCountry(Country country){
        boolean isSaved = countryService.addCountry(country);
        if (isSaved)
            return new ResponseEntity<>("saved successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("not saves",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Country>> getAllCountries(){
        List<Country> countryList = countryService.getAllCountries();
        return new ResponseEntity<>(countryList,HttpStatus.OK);
    }
}
