package user.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bookmark.entity.Bookmark;
import category.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import user.dto.UserDTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "bookMarks")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login_id", unique = true)
    private String loginId;

    @Column(name="password", length = 1000)
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name="email")
    private String email;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "user")
    private List<Bookmark> bookMarks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Category> categories = new ArrayList<>();

    public void change(User newUser) {
        if (newUser.password != null) {
            this.password = newUser.password;
        }
    }

    public UserDTO toDTO() {

        return UserDTO.builder()
                .loginId(loginId)
                .nickname(nickname)
                .email(email)
                .createdDate(createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}