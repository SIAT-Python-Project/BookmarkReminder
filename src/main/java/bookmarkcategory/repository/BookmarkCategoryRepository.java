package bookmarkcategory.repository;

import java.util.List;

import bookmark.entity.Bookmark;
import bookmarkcategory.entity.BookmarkCategory;
import category.entity.Category;
import jakarta.persistence.EntityManager;
import user.entity.User;

public class BookmarkCategoryRepository {
	
	// b-c 관계 조회(bookmark_id 기준으로 한 category_id 조회)
	public static List<Category> findCategoryIdByBookmarkId(EntityManager em, Bookmark bookmark) {
		String sql = "SELECT DISTINCT bc.category FROM BookmarkCategory bc WHERE bc.bookmark = :bookmark";
		return em.createQuery(sql, Category.class)
				 .setParameter("bookmark", bookmark)
				 .getResultList();
	}
	
	// b-c 관계 조회(bc 관계가 존재하는 bookmark_id 조회)
	public static List<Bookmark> findBookmarkIdInBookmarkCategory(EntityManager em){
		String sql = "SELECT bc.bookmark FROM BookmarkCategory bc";
		return em.createQuery(sql, Bookmark.class)
				 .getResultList();
	}
	
	// b-c 관계 조회(category_id 기준으로 한 bookmark_id 조회)
	public static List<Bookmark> findBookmarkIdByCategoryId(EntityManager em, Category category){
		String sql = "SELECT bc.bookmark FROM BookmarkCategory bc WHERE bc.category = :category";
		return em.createQuery(sql, Bookmark.class)
				 .setParameter("category", category)
				 .getResultList();
	}
	
	// b-c 관계 조회(bc 관계에 포함되지 않은 bookmark_id 조회)
	public static List<Bookmark> findBookmarkNotInBookmarkCategory(EntityManager em, User user){
		String sql = "SELECT b FROM Bookmark b JOIN User u ON b.user = :user WHERE b.bookmarkId NOT IN (SELECT bc.bookmark.bookmarkId FROM BookmarkCategory bc)";
		return em.createQuery(sql, Bookmark.class)
				 .setParameter("user", user)
				 .getResultList();
	}
	
	// b-c 관계 조회(user_id 기준으로 bc 관계가 있는 category_id 조회)
	public static List<Category> findCategoryIdInBcByUserId(EntityManager em, User user){
		String sql = "SELECT bc.category FROM BookmarkCategory bc WHERE bc.category IN (SELECT c FROM Category c WHERE c.user = :user)";
		return em.createQuery(sql, Category.class)
				 .setParameter("user", user)
				 .getResultList();
	}
	
	// b-c 관계 조회(user_id 기준으로 bc 관계가 있는 bookmark_id 조회)
	public static List<Bookmark> findBookmarkIdInBcByUserId(EntityManager em, User user){
		String sql = "SELECT bc.bookmark FROM BookmarkCategory bc WHERE bc.bookmark IN (SELECT b FROM Bookmark b WHERE b.user = :user)";
		return em.createQuery(sql, Bookmark.class)
				 .setParameter("user", user)
				 .getResultList();
	}
	
	// b-c 관계 조회(bookmark_id 기준으로 bookmark_category_id 조회)  
	public static List<BookmarkCategory> findBookmarkCategoryByBookmarkId(EntityManager em, Bookmark bookmark){
		String sql = "SELECT bc FROM BookmarkCategory bc WHERE bc.bookmark = :bookmark";
		return em.createQuery(sql, BookmarkCategory.class)
				 .setParameter("bookmark", bookmark)
				 .getResultList();
	}
	
	// b-c 관계 조회(category_id 기준으로 bookmark_category_id 조회)
	public static List<BookmarkCategory> findBookmarkCategoryByCategoryId(EntityManager em, Category category){
		String sql = "SELECT bc FROM BookmarkCategory bc WHERE bc.category = :category";
		return em.createQuery(sql, BookmarkCategory.class)
				 .setParameter("category", category)
				 .getResultList();
	}
	
	// b-c 관계 추가
	public static void insertBookmarkCategory(EntityManager em, BookmarkCategory bookmarkCategory) {
		em.persist(bookmarkCategory);
	}
	
	// b-c 관계 삭제
	public static void deleteBookmarkCategoryByCategoryId(EntityManager em, BookmarkCategory bookmarkCategory) {
		em.remove(bookmarkCategory);
	}
	
	// bc Id 조회
	public static BookmarkCategory findBookmarkCategory(EntityManager em, Bookmark bookmark, Category category) {
		String sql = "SELECT bc FROM BookmarkCategory bc WHERE bc.bookmark = ?1 AND bc.category = ?2";
		return em.createQuery(sql, BookmarkCategory.class)
				 .setParameter(1, bookmark)
				 .setParameter(2, category)
				 .getSingleResult();
	}
}
