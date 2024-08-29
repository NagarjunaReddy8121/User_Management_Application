package in.arjun.model.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String name;
    private String email;
    private Long phoneNo;
    private String country;
    private String state;
    private String city;
}
