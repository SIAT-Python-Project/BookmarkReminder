package test.hj;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bookmark.entity.Bookmark;
import bookmark.service.BookmarkService;
import bookmarkcategory.service.BookmarkCategoryService;
import category.entity.Category;
import category.repository.CategoryRepository;
import category.service.CategoryService;
import common.util.DbUtil;
import jakarta.persistence.EntityManager;
import user.entity.User;
import user.service.UserService;

public class HJBookmarkCategoryServiceTest {

	private static List<User> users = new ArrayList<>();
	private static List<Bookmark> bookmarks = new ArrayList<>();
	private static List<Category> categories = new ArrayList<>();

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
//        settings();
    	
//    	getBookmarksNotInCategory(3L);
    	
//    	deleteBookmarkCategoryByBookmarkId(3L);
    	deleteBookmarkCategoryByCategoryId(8L);
    	
//    	EntityManager em = DbUtil.getEntityManager();
//    	User test = em.find(User.class, 2L);
    	
//    	Category testC = CategoryService.getCategoryByCategoryId(7L);
//    	Bookmark testB = BookmarkService.getBookmark(13L);
//    	createBookmarkCategory(testB, testC);
    	
    	
    	
    }

    private static void settings() {
//        users.forEach(user -> {
//            UserService.signup(user.getLoginId(),
//                    user.getPassword(),
//                    user.getPassword(),
//                    user.getNickname(),
//                    user.getEmail());
//        });
    }
    
    private static void getBookmarksByCategoryId(Long categoryId) {
        try {
            List<Bookmark> bookmarkList = BookmarkCategoryService.getBookmarkListByCategoryId(categoryId);
            for(Bookmark b : bookmarkList) {
            	System.out.println("북마크 목록 조회 성공! " + b.getBookmarkName());
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void getBookmarksNotInCategory(Long userId) {
        try {
            List<Bookmark> bookmarkListNotInCategory = BookmarkCategoryService.getBookmarkListNotInCategory(userId);
            for(Bookmark b : bookmarkListNotInCategory) {
            	System.out.println("북마크 조회 성공! " + b.getBookmarkName());
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void deleteBookmarkCategoryByBookmarkId(Long bookmarkId) {
        try {
            BookmarkCategoryService.deleteBookmarkCategoryByBookmarkId(bookmarkId);
            System.out.println("삭제 성공!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
    
    private static void deleteBookmarkCategoryByCategoryId(Long categoryId) {
        try {
            BookmarkCategoryService.deleteBookmarkCategoryByCategoryId(categoryId);
            System.out.println("삭제 성공!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
    
    private static void createBookmarkCategory(Bookmark bookmark, Category category) {
        try {
            BookmarkCategoryService.createBookmarkCategory(bookmark, category);
            System.out.println("저장 성공!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

}
