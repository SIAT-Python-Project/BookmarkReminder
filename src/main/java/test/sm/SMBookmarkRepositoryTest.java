package test.sm;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bookmark.entity.Bookmark;
import bookmark.repository.BookmarkRepository;
import common.util.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import user.entity.User;

public class SMBookmarkRepositoryTest {
    private static EntityManager em = DbUtil.getEntityManager();
    private static List<User> users = new ArrayList<>();
    private static List<Bookmark> bookmarks = new ArrayList<>();
        
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
        
        for (int i = 1; i <= 10; i++) {
        	bookmarks.add(Bookmark.builder()
                .bookmarkName("Bookmark " + i)
                .url("http://example.com/" + i)
                .createdDate(LocalDateTime.now())
                .user(users.get(i - 1))
                .build());           
        }
    } 

    public static void main(String[] args) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            // Test setting
         setting(em);

            // Test code
//            insertBookmarkTest(em, Bookmark.builder()
//                .bookmarkName("New Bookmark")
//                .url("http://example.com/new")
//                .createdDate(LocalDateTime.now())
//                .user(users.get(0))
//                .build());

//            findBookmarkByIdTest(em, 1L);
//            findAllBookmarksTest(em);
//            deleteBookmarkTest(em, 1L);

            tx.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }

        DbUtil.close();
    }

    // Setting
    private static void setting(EntityManager em) {
        for (User user : users) {
        	System.out.println(user.getId());
            em.persist(user);
        }
        for (Bookmark bookmark : bookmarks) {
        	System.out.println(bookmark.getUser().getId());
            em.persist(bookmark);
        }
    }

    // Successes Test
    private static void insertBookmarkTest(EntityManager em, Bookmark bookmark) {
        BookmarkRepository.saveBookmark(bookmark, em);
        Bookmark foundBookmark = BookmarkRepository.findById(bookmark.getBookmarkId(), em);
        System.out.println(foundBookmark.getBookmarkName());
    }

    // Successes Test
    private static void findBookmarkByIdTest(EntityManager em, Long bookmarkId) {
        Bookmark bookmark = BookmarkRepository.findById(bookmarkId, em);
        System.out.println(bookmark);
        System.out.println(bookmark.getBookmarkName());
    }

    // Successes Test
    private static void findAllBookmarksTest(EntityManager em) {
        List<Bookmark> bookmarkList = BookmarkRepository.findAll(em);
        for (Bookmark bookmark : bookmarkList) {
            System.out.println(bookmark.getBookmarkName());
        }
    }

    // Successes Test
    public static void deleteBookmarkTest(EntityManager em, Long id) {
        Bookmark deleteBookmark = BookmarkRepository.findById(id, em);
        BookmarkRepository.deleteBookmark(deleteBookmark, em);
    }
}