package test.hgyellow;

import user.entity.User;
import user.repository.UserRepository;
import user.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HGUserServiceTest {
    private static List<User> users = new ArrayList<>();

    static {
        for (int i = 1; i <= 10; i++) {
            users.add(User.builder()
                    .createdDate(LocalDateTime.now())
                    .email("user" + i + "@email.com")
                    .loginId("user" + i)
                    .password("user" + i)
                    .nickname("user" + i)
                    .build());
        }
    }

    public static void main(String[] args) {
        settings();

//        signup("user11",
//                "user11",
//                "user11",
//                "user11@email.com");

        deleteUser(11L);
    }

    private static void settings() {
        users.forEach(user -> {
            UserService.signup(user.getLoginId(),
                    user.getPassword(),
                    user.getNickname(),
                    user.getEmail());
        });
    }

    private static void signup(String loginId,
                               String password,
                               String nickname,
                               String email) {

        try {
            User user = UserService.signup(loginId,
                    password,
                    nickname,
                    email);

            System.out.println(user);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void login(String loginId,
                              String password) {
        try {
            User user = UserService.login(loginId, password);
            System.out.println(user);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void updatePassword(Long id, String password, String newPassword) {
        try {
            UserService.updatePassword(id, password, newPassword);
            System.out.println("수정 성공!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void deleteUser(Long id) {
        try {
            UserService.deleteUser(id);
            System.out.println("삭제 성공!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
