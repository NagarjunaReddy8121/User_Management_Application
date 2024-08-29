package in.arjun.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordRequest {

    private String email;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
