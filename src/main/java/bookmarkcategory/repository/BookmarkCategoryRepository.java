package bookmarkcategory.repository;

import java.util.List;

import bookmark.entity.Bookmark;
import bookmarkcategory.entity.BookmarkCategory;
import category.entity.Category;
import jakarta.persistence.EntityManager;

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
	
	// b-c 관계 추가
	public static void insertBookmarkCategory(EntityManager em, BookmarkCategory bookmarkCategory) {
		em.persist(bookmarkCategory);
	}
	
	// b-c 관계 삭제
	public static void deleteBookmarkCategoryByCategoryId(EntityManager em, BookmarkCategory bookmarkCategory) {
		em.remove(bookmarkCategory);
	}
}
