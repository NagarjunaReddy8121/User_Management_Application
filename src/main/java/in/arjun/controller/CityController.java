package in.arjun.controller;

import in.arjun.model.request.CityRequest;
import in.arjun.model.response.CityResponse;
import in.arjun.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/add")
    public ResponseEntity<String> saveCity(CityRequest cityRequest){
        boolean isSaved = cityService.addCity(cityRequest);
        if (isSaved)
            return new ResponseEntity<>("saved successfulluy", HttpStatus.CREATED);
        return new ResponseEntity<>("not saved",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/list/{state}")
    public ResponseEntity<List<CityResponse>> getCitiesByState(@PathVariable("state") String stateName){
        List<CityResponse> citiesByState = cityService.getCitiesByState(stateName);
        return new ResponseEntity<>(citiesByState,HttpStatus.OK);
    }
}
