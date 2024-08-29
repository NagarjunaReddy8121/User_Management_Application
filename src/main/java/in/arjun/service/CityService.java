package in.arjun.service;

import in.arjun.model.entity.City;
import in.arjun.model.entity.Country;
import in.arjun.model.entity.State;
import in.arjun.model.request.CityRequest;
import in.arjun.model.response.CityResponse;
import in.arjun.model.response.StateResponse;
import in.arjun.repository.CityRepository;
import in.arjun.repository.CountryRepository;
import in.arjun.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    public boolean addCity(CityRequest cityRequest) {
        Optional<State> stateById = stateRepository.findById(cityRequest.getSid());
        if (stateById.isEmpty())
            return false;

        City city = City.builder()
                .name(cityRequest.getName())
                .state(stateById.get())
                .build();
        cityRepository.save(city);
        return true;
    }

    public List<CityResponse> getCitiesByState(String stateName){
        Optional<State> state = stateRepository.findByName(stateName);
        if (state.isEmpty()){
            return null;
        }
        List<City> findCityByState = cityRepository.findBySid(state.get().getId());
        return findCityByState.stream().map(CityResponse::fromCity).toList();
    }

}
