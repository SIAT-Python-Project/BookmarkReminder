package test;

import common.util.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import user.entity.User;
import user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HGTest {
    private static EntityManager em = DbUtil.getEntityManager();
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
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            // test setting
            setting(em);

            // test code
            deleteUserTest(em, 1L);


            tx.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }

        DbUtil.close();
    }


    // setting
    private static void setting(EntityManager em) {
        for (User user: users) {
            em.persist(user);
        }
    }

    // successes Test
    private static void insertUserTest(EntityManager em, User user) {
        UserRepository.insert(em, user);
        User foundUser = UserRepository.findUserByUserId(em, user.getId());
        System.out.println(foundUser);
    }

    // successes Test
    private static void findUserByUserIdTest(EntityManager em, Long userId) {
        User user = UserRepository.findUserByUserId(em, userId);
        System.out.println(user);
    }

    // successes Test
    private static void findUserByNicknameTest(EntityManager em, String nickname) {
        User user = UserRepository.findUserByNickname(em, nickname);
        System.out.println(user);
    }

    // successes Test
    private static void findUserByEmailTest(EntityManager em, String email) {
        User user = UserRepository.findUserByEmail(em, email);

        System.out.println(user);
    }

    // successes Test
    private static void findUserByLoginIdTest(EntityManager em, String loginId) {
        User user = UserRepository.findUserByLoginId(em, loginId);

        System.out.println(user);
    }

    // successes Test
    public static void deleteUserTest(EntityManager em, Long id) {
        User deleteUser = UserRepository.findUserByUserId(em, id);
        UserRepository.delete(em, deleteUser);
    }
}
