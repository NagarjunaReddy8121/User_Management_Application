package in.arjun.repository;

import in.arjun.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    @Query(value = "SELECT * FROM city WHERE sid=:sid", nativeQuery = true)
    List<City> findBySid(Integer sid);
}
