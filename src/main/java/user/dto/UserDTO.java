package user.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class UserDTO {
    private String loginId;
    private String nickname;
    private String email;
    private String createdDate;
}
