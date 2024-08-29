package in.arjun.controller;

import in.arjun.model.entity.State;
import in.arjun.model.request.StateRequest;
import in.arjun.model.response.StateResponse;
import in.arjun.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping("/add")
    public ResponseEntity<String> saveState(StateRequest stateRequest){
        boolean isSaved = stateService.addState(stateRequest);
        if (isSaved)
            return new ResponseEntity<>("saved successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("not saved",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/list/{country}")
    public ResponseEntity<List<StateResponse>> getAllStatesByCountry(@PathVariable("country") String countryName){
        List<StateResponse> allStatesByCountry = stateService.getAllStatesByCountry(countryName);
        return new ResponseEntity<>(allStatesByCountry,HttpStatus.OK);
    }
}
