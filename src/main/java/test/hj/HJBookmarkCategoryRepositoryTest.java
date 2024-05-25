package test.hj;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.tag.common.core.ForEachSupport;

import bookmark.entity.Bookmark;
import bookmark.repository.BookmarkRepository;
import bookmarkcategory.entity.BookmarkCategory;
import bookmarkcategory.repository.BookmarkCategoryRepository;
import category.entity.Category;
import category.repository.CategoryRepository;
import common.util.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import user.entity.User;
import user.repository.UserRepository;

public class HJBookmarkCategoryRepositoryTest {

	private static EntityManager em = DbUtil.getEntityManager();
    private static List<User> users = new ArrayList<>();
    private static List<BookmarkCategory> bookmarkCategories = new ArrayList<>();
    private static List<Category> categories = new ArrayList<>();

    static {
//        for (int i = 1; i <= 10; i++) {
//            users.add(User.builder()
//                    .createdDate(LocalDateTime.now())
//                    .email("user" + i + "@email.com")
//                    .loginId("user" + i)
//                    .password("user" + i)
//                    .nickname("user" + i)
//                    .build());
//        }
        for (int i = 1; i <= 5; i++) {
            categories.add(Category.builder()
            		  .categoryName("category" + i)
            		  .build());
        }
    }

    public static void main(String[] args) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            // test setting
//            setting(em);

            // test code
//            Bookmark tempB = BookmarkRepository.findById(3L, em);
//            System.out.println(tempB);
//            Category tempC = CategoryRepository.findCategoryById(em, 2L);
//            System.out.println(tempC);
//            
//            BookmarkCategory tempBc = BookmarkCategory.builder()
//            										  .bookmark(tempB)
//            										  .category(tempC)
//            										  .build();
//            System.out.println(tempBc);
//            
//            BookmarkCategoryRepository.insertBookmarkCategory(em, tempBc);
//            User testU = UserRepository.findUserByUserId(em, 1L);
//            
//            Bookmark testB = Bookmark.builder()
//            						 .bookmarkName("test2")
//            						 .url("https://test2.com")
//            						 .createdDate(LocalDateTime.now())
//            						 .user(testU)
//            						 .build();
//            System.out.println(testB);
//            BookmarkRepository.saveBookmark(testB, em);
//            
//            Category testC = Category.builder()
//            						 .categoryName("hey")
//            						 .user(testU)
//            						 .build();
//            System.out.println(testC);
//            CategoryRepository.createCategory(em, testC);
//            
//            BookmarkCategory testBc = BookmarkCategory.builder()
//            										  .bookmark(testB)
//            										  .category(testC)
//            										  .build();
//            
//            BookmarkCategoryRepository.insertBookmarkCategory(em, testBc);
//            
//            Bookmark findTestB = BookmarkRepository.findById(13L, em);
//            BookmarkRepository.deleteBookmark(findTestB, em);
//            findCategoryByBookmarkTest(em, findTestB);
            
//            Category findTestC = CategoryRepository.findCategoryById(em, 2L);
//            findBookmarkByCategoryTest(em, findTestC);

            BookmarkCategoryRepository.findBookmarkIdInBookmarkCategory(em);
            
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
        for(long i = 1; i <= 10; i++) {
        	if((i % 2) == 0) {
	        	Bookmark tempB = em.find(Bookmark.class, i);
	        	Category tempC = em.find(Category.class, 1L);
	        	
	        	bookmarkCategories.add(BookmarkCategory.builder()
	        										   .bookmark(tempB)
	        										   .category(tempC)
	        										   .build());
        	} else {
        		Bookmark tempB = em.find(Bookmark.class, i);
        		Category tempC = em.find(Category.class, 2L);
        		
        		bookmarkCategories.add(BookmarkCategory.builder()
						   							   .bookmark(tempB)
						   							   .category(tempC)
						   							   .build());
        	}
        }
        
        Category testC = Category.builder()
        						 .categoryName("test")
        						 .bookmarkCategories(bookmarkCategories)
        						 .build();
        
        em.persist(testC);
    }

    // successes Test
    private static void insertUserTest(EntityManager em, User user) {
        UserRepository.insert(em, user);
        User foundUser = UserRepository.findUserByUserId(em, user.getId());
        System.out.println(foundUser);
    }

    // successes Test
    private static void findCategoryByBookmarkTest(EntityManager em, Bookmark bookmark) {
        List<Category> categoryList = BookmarkCategoryRepository.findCategoryIdByBookmarkId(em, bookmark);
        System.out.println(categoryList);
    }

    // successes Test
    private static void findBookmarkByCategoryTest(EntityManager em, Category category) {
        List<Bookmark> bookmarkList = BookmarkCategoryRepository.findBookmarkIdByCategoryId(em, category);
        System.out.println(bookmarkList);
    }

    // successes Test
    private static void findBookmarkInBookmarkCategoryTest(EntityManager em) {
        List<Bookmark> bookmarkList = BookmarkCategoryRepository.findBookmarkIdInBookmarkCategory(em);

        System.out.println(bookmarkList);
    }

    // successes Test
    private static void findUserByLoginIdTest(EntityManager em, String loginId) {
        User user = UserRepository.findUserByLoginId(em, loginId);

        System.out.println(user);
    }

    // successes Test
    public static void deleteBookmarkCategoryTest(EntityManager em, Bookmark bookmark) {
        //
    }

}
