package in.arjun.model.response;

import in.arjun.model.entity.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StateResponse {

    private Integer id;
    private String name;


    public static StateResponse fromState(State state){
          return StateResponse.builder()
                  .id(state.getId())
                  .name(state.getName())
                  .build();
    }
}
