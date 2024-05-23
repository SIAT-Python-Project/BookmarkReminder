package bookmark.repository;

import java.util.List;

import bookmark.entity.Bookmark;
import jakarta.persistence.EntityManager;

public class BookmarkRepository {
	
	public static Bookmark findById(Long id, EntityManager em) {
        return em.find(Bookmark.class, id);
    }
	
	public static List<Bookmark> findAll(EntityManager em) {
        return em.createQuery("SELECT b FROM Bookmark b", Bookmark.class).getResultList();
    }
	
	public static void saveBookmark(Bookmark bookmark, EntityManager em) {
        em.persist(bookmark);
    }
	
	public static void deleteBookmark(Bookmark bookmark, EntityManager em) {
        em.remove(bookmark);
    }
}
