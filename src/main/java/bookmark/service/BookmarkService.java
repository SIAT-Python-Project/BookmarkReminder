package bookmark.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bookmark.dto.BookmarkDTO;
import bookmark.entity.Bookmark;
import bookmark.repository.BookmarkRepository;
import bookmarkcategory.entity.BookmarkCategory;
import bookmarkcategory.repository.BookmarkCategoryRepository;
import category.entity.Category;
import category.repository.CategoryRepository;
import common.util.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import user.entity.User;
import user.repository.UserRepository;

public class BookmarkService {

	public static Long addBookmark(String bookmarkName, String url, Long userId, List<String> categoryNames) {

		EntityManager em = DbUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Long id = null;

		try {
			User user = UserRepository.findUserByUserId(em, userId);
			List<Category> categories = new ArrayList<>(); // 이미 존재하는 카테고리 리스트

			// 카테고리 조회 및 생성
			for (String categoryName : categoryNames) {
				if (categoryName == null || categoryName.trim().isEmpty()) {
                    // 카테고리 이름이 빈 값이면 건너뛴다
                    continue;
                }								
				Category category = CategoryRepository.findCategoryByName(em, categoryName, user);
				if (category == null) {
					category = Category.builder()
										.categoryName(categoryName)
										.user(user).build();
					CategoryRepository.createCategory(em, category);
				}
				categories.add(category);
			}

			// 북마크 생성
			Bookmark bookmark = Bookmark.builder()
										.bookmarkName(bookmarkName)
										.url(url)
										.createdDate(LocalDateTime.now())
										.user(user)
										.build();
			BookmarkRepository.saveBookmark(bookmark, em);
			
			id = bookmark.getBookmarkId();
			
			// BookmarkCategory 관계 설정
			for (Category category : categories) {
				if (category != null) {
					BookmarkCategory bookmarkCategory = BookmarkCategory.builder()
							.category(category)
							.bookmark(bookmark)
							.build();
					BookmarkCategoryRepository.insertBookmarkCategory(em, bookmarkCategory);
				}
			}

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new IllegalArgumentException("북마크 추가 실패: " + e.getMessage());
		} finally {
			em.close();
		}
		
		
		return id;

	}

	public static BookmarkDTO getBookmark(Long id) {
		EntityManager em = DbUtil.getEntityManager();
		Bookmark bookmark = null;
		BookmarkDTO bookmarkDTO = null;
		try {
			bookmark = BookmarkRepository.findById(id, em);
			if (bookmark == null) {
				throw new IllegalArgumentException("북마크 조회 실패: 해당 ID의 북마크가 존재하지 않습니다.");
			}
			bookmarkDTO = bookmark.toDTO();
		} catch (NoResultException e) {
			throw new IllegalArgumentException("북마크 조회 실패: " + e.getMessage());
		} finally {
			em.close();
		}

		return bookmarkDTO;
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
