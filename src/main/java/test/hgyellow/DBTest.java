package test.hgyellow;

import category.entity.Category;
import category.repository.CategoryRepository;
import common.util.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import user.entity.User;
import user.repository.UserRepository;
import user.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBTest {

    private static EntityManager em = DbUtil.getEntityManager();
    private static List<Category> categories = new ArrayList<>();

    public static void main(String[] args) {
        settings();
    }

    private static void settings() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        User user = UserRepository.findUserByUserId(em, 1L);

        for (int i = 1; i <= 5; i++) {
            CategoryRepository.createCategory(em, Category.builder()
                    .categoryName("category" + i)
                    .user(user)
                    .build());
        }

        tx.commit();
        em.close();
    }
}
