package user.repository;

import jakarta.persistence.EntityManager;
import user.entity.Role;
import user.entity.User;

import java.util.List;

public class UserRepository {


    // insert User
    public static User insert(EntityManager em, User user) {
        em.persist(user);
        return user;
    }

    // read User
    public static User findUserByUserId(EntityManager em, Long id) {
        String sql = "SELECT u FROM User u WHERE u.id = :id";

        return em.createQuery(sql, User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public static User findUserByNickname(EntityManager em, String nickname) {
        String sql = "SELECT u FROM User u WHERE u.nickname = :nickname";

        return em.createQuery(sql, User.class)
                .setParameter("nickname", nickname)
                .getSingleResult();
    }

    public static User findUserByEmail(EntityManager em, String email) {
        String sql = "SELECT u FROM User u WHERE u.email = :email";

        return em.createQuery(sql, User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public static User findUserByLoginId(EntityManager em, String loginId) {
        String sql = "SELECT u FROM User u WHERE u.loginId = :loginId";

        return em.createQuery(sql, User.class)
                .setParameter("loginId", loginId)
                .getSingleResult();
    }

    public static void delete(EntityManager em, User deleteUser) {
        em.remove(deleteUser);
    }

    public static List<User> findAllUserOfRoleUser(EntityManager em) {
        String sql = "SELECT u FROM User u WHERE u.role = :role";

        return em.createQuery(sql, User.class)
                .setParameter("role", Role.USER)
                .getResultList();
    }
}
