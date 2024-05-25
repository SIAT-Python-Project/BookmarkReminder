package user.dto;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import user.entity.User;

@Data
@AllArgsConstructor
@Builder
public class UserDTO {
    private String loginId;
    private String nickname;
    private String email;
    private String createdDate;
    
    public User toEntity() {
        return User.builder()
                .loginId(loginId)
                .nickname(nickname)
                .email(email)
                .createdDate(LocalDateTime.parse(createdDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}
