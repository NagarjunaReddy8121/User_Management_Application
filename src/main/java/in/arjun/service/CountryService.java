package in.arjun.service;

import in.arjun.model.entity.Country;
import in.arjun.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public boolean addCountry(Country country){
        Country saved = countryRepository.save(country);
        return true;
    }

    public Country getCountryById(Integer id) {
        Optional<Country> countryById = countryRepository.findById(id);
        return countryById.orElse(null);
    }

    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }
}
