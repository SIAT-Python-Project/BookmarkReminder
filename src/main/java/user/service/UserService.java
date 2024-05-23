package user.service;

import common.util.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import org.hibernate.exception.ConstraintViolationException;
import user.entity.User;
import user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserService {
    public static User signup(String loginId,
                              String password,
                              String nickname,
                              String email) {

        User user = User.builder()
                .loginId(loginId)
                .password(password)
                .nickname(nickname)
                .email(email)
                .createdDate(LocalDateTime.now())
                .build();

        User initUser = null;

        EntityManager em = DbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            initUser = UserRepository.insert(em, user);
            tx.commit();
        } catch (ConstraintViolationException e) {
            tx.rollback();
            throw new IllegalArgumentException("회원가입 실패: 로그인 ID가 이미 사용 중 입니다.");
        } finally {
            em.close();
        }

        return initUser;
    }

    public static User login(String loginId,
                              String password) {
        EntityManager em = DbUtil.getEntityManager();
        User user = null;

        try {
             user = UserRepository.findUserByLoginId(em, loginId);

             if (!Objects.equals(user.getPassword(), password)) {
                 throw new IllegalArgumentException("로그인 실패: 비밀번호가 일치하지 않습니다.");
             }
        } catch (NoResultException e) {
            throw new IllegalArgumentException("로그인 실패: 로그인 ID가 존재하지 않습니다.");
        } finally {
            em.close();
        }

        return user;
    }

    public static void updatePassword(Long id, String password, String newPassword) {
        EntityManager em = DbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        User newUser = User.builder()
                .password(newPassword)
                .build();

        tx.begin();

        try {
            User targetUser = UserRepository.findUserByUserId(em, id);

            if (!Objects.equals(targetUser.getPassword(), password)) {
                throw new IllegalArgumentException("수정 실패: 기존 비밀번호가 틀렸습니다.");
            }

            targetUser.change(newUser);

            tx.commit();
        } catch (NoResultException e) {
            tx.rollback();
            throw new IllegalArgumentException("수정 실패: 유저의 정보가 없습니다.");
        } finally {
            em.close();
        }
    }

    public static void deleteUser(Long id) {
        EntityManager em = DbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            User deleteUser = UserRepository.findUserByUserId(em, id);

            UserRepository.delete(em, deleteUser);

            tx.commit();
        } catch (NoResultException e) {
            throw new IllegalArgumentException("삭제 실패: 유저의 정보가 없습니다.");
        }
    }
}
