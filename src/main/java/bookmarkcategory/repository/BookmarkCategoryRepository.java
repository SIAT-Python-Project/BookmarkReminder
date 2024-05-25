package bookmarkcategory.repository;

import bookmarkcategory.entity.BookmarkCategory;
import jakarta.persistence.EntityManager;

public class BookmarkCategoryRepository {
	
	public static void saveMemo(BookmarkCategory bookmarkCategory, EntityManager em) {
		em.persist(bookmarkCategory);
	}
}
