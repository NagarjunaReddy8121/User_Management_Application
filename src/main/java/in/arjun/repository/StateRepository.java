package in.arjun.repository;

import in.arjun.model.entity.Country;
import in.arjun.model.entity.State;
import in.arjun.model.response.StateResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StateRepository extends JpaRepository<State,Integer> {

    @Query(value = "SELECT * FROM state WHERE cid=:cid",nativeQuery = true)
    List<State> findByCid(Integer cid);

    Optional<State> findByName(String name);

}
