package test.hgyellow;

import bookmark.entity.Bookmark;
import bookmark.repository.BookmarkRepository;
import bookmark.service.BookmarkService;
import bookmarkcategory.entity.BookmarkCategory;
import bookmarkcategory.repository.BookmarkCategoryRepository;
import category.entity.Category;
import category.repository.CategoryRepository;
import common.util.DbUtil;
import common.util.PasswordEncipherUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import user.entity.Role;
import user.entity.User;
import user.repository.UserRepository;
import user.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBTest {
    public static void main(String[] args) {
        settings();
    }

    private static void settings() {
        EntityManager em = DbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            inertUser(em);
            User user = UserRepository.findUserByUserId(em, 2L);

            insertCategoryAndBookmark(em, user);

            Category category = CategoryRepository.findCategoryById(em, 1L);

            insertBookmark(em, category);

            tx.commit();

        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void insertBookmark(EntityManager em, Category category) {

        try {
            for (long i = 6L; i <= 10L; i++) {
                Bookmark bookmark = BookmarkRepository.findById(i, em);

                BookmarkCategoryRepository.insertBookmarkCategory(em,
                        BookmarkCategory.builder()
                                .category(category)
                                .bookmark(bookmark)
                                .build());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void insertCategoryAndBookmark(EntityManager em, User user) {
        try {
            for (int i = 1; i <= 5; i++) {
                CategoryRepository.createCategory(em,
                        Category.builder()
                                .user(user)
                                .categoryName("category" + i)
                                .build());

            }

            for (int i = 1; i <= 10; i++) {
                BookmarkRepository.saveBookmark(
                        Bookmark.builder()
                                .url("https://naver.com")
                                .bookmarkName("bookmark" + i)
                                .createdDate(LocalDateTime.now())
                                .user(user)
                                .build(),em);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void inertUser(EntityManager em) {
        try {
            User admin = User.builder()
                    .loginId("admin")
                    .password(PasswordEncipherUtil.createPassword("admin"))
                    .email("admin@email.com")
                    .nickname("admin")
                    .createdDate(LocalDateTime.now())
                    .role(Role.ADMIN)
                    .build();

            User user = User.builder()
                    .loginId("user")
                    .password(PasswordEncipherUtil.createPassword("user"))
                    .email("user@email.com")
                    .nickname("user")
                    .createdDate(LocalDateTime.now())
                    .role(Role.USER)
                    .build();


            UserRepository.insert(em, admin);
            UserRepository.insert(em, user);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
