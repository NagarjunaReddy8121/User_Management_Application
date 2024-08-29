package in.arjun.service;

import in.arjun.model.entity.Country;
import in.arjun.model.entity.State;
import in.arjun.model.request.StateRequest;
import in.arjun.model.response.StateResponse;
import in.arjun.repository.CountryRepository;
import in.arjun.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    public boolean addState(StateRequest stateRequest) {
        Optional<Country> optionalCountry = countryRepository.findById(stateRequest.getCid());
        if (optionalCountry.isPresent()){
            Country country = optionalCountry.get();
            State state = State.builder().name(stateRequest.getName())
                    .country(country)
                    .build();
            State saved = stateRepository.save(state);
            return true;
        }
        return false;
    }

    public List<StateResponse> getAllStatesByCountry(String countryName) {
        Optional<Country> country = countryRepository.findByName(countryName);
        if (country.isEmpty()){
            return null;
        }
        List<State> findStateByCountry = stateRepository.findByCid(country.get().getId());
        return findStateByCountry.stream().map(StateResponse::fromState).toList();
    }

}
