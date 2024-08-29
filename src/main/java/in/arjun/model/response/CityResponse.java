package in.arjun.model.response;

import in.arjun.model.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityResponse {

    private Integer id;
    private String name;

    public static CityResponse fromCity(City city){
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }
}
