package user.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bookmark.entity.Bookmark;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name="password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name="email")
    private String email;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "user")
    private List<Bookmark> bookMarks = new ArrayList<>();

    public void change(User newUser) {
        if (newUser.password != null) {
            this.password = newUser.password;
        }
    }
}