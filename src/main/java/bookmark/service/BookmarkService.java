package bookmark.service;

import java.time.LocalDateTime;
import java.util.List;

import bookmark.entity.Bookmark;
import bookmark.repository.BookmarkRepository;
import common.util.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import user.entity.User;

public class BookmarkService {
	
	public static Bookmark addBookmark(String bookmarkName, String url, User user) {
        Bookmark bookmark = Bookmark.builder()
                .bookmarkName(bookmarkName)
                .url(url)
                .createdDate(LocalDateTime.now())
                .user(user)
                .build(); 

        EntityManager em = DbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            BookmarkRepository.saveBookmark(bookmark, em);
            tx.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            tx.rollback();
            throw new IllegalArgumentException("북마크 추가 실패: " + e.getMessage());
        } finally {
            em.close();
        }

        return bookmark;
    }
	
	public static Bookmark getBookmark(Long id) {
        EntityManager em = DbUtil.getEntityManager();
        Bookmark bookmark = null;

        try {
            bookmark = BookmarkRepository.findById(id, em);
            if (bookmark == null) {
                throw new IllegalArgumentException("북마크 조회 실패: 해당 ID의 북마크가 존재하지 않습니다.");
            }
        } catch (NoResultException e) {
            throw new IllegalArgumentException("북마크 조회 실패: " + e.getMessage());
        } finally {
            em.close();
        }

        return bookmark;
    }
	
	public static List<Bookmark> getAllBookmarks() {
        EntityManager em = DbUtil.getEntityManager();
        List<Bookmark> bookmarks;

        try {
            bookmarks = BookmarkRepository.findAll(em);
        } catch (Exception e) {
            throw new IllegalArgumentException("북마크 조회 실패: " + e.getMessage());
        } finally {
            em.close();
        }

        return bookmarks;
    }
	
	
	public static void deleteBookmark(Long id) {
        EntityManager em = DbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Bookmark bookmark = BookmarkRepository.findById(id, em);
            if (bookmark == null) {
                throw new NoResultException("북마크가 존재하지 않습니다.");
            }

            BookmarkRepository.deleteBookmark(bookmark, em);

            tx.commit();
        } catch (NoResultException e) {
            tx.rollback();
            throw new IllegalArgumentException("북마크 삭제 실패: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}

