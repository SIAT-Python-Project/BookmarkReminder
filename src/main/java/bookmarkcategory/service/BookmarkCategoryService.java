package bookmarkcategory.service;

import java.util.ArrayList;
import java.util.List;

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

public class BookmarkCategoryService {
	
	/*
	 * 메인 페이지 출력
	 * category --> category Service 이용하여 출력(getCategoriesByUserId)
	 * bookmark not in category
	 * user_id 기반 bookmark List 출력 --> bookmark repository 이용
	 * bookmark List 중 not in (SELECT bc.bookmark FROM BookmarkCategory bc)인 bookmarkList 반환
	 */
	
	// gettBookmarkListNotInCategory : Category에 포함되지 않은 Bookmark 조회(user_id 기준)
	public static List<Bookmark> getBookmarkListNotInCategory(Long userId){
		EntityManager em = DbUtil.getEntityManager();
		User user;
		List<Bookmark> bookmarkList;
		
		try {
			user = UserRepository.findUserByUserId(em, userId);
			bookmarkList = BookmarkCategoryRepository.findBookmarkNotInBookmarkCategory(em, user);
		} catch (Exception e) {
			throw new IllegalArgumentException("북마크 조회 실패!");
		} finally {
			em.close();
		}
		
		return bookmarkList;
	}
	
	// category 상세 페이지 출력
	/*
	 * category명 : user_id 기반 category List (Category Service getCategoriesByUserId 이용)
	 * category_id 가져온 다음에 bc table에서 category_id가 동일한 bookmark_id 추출
	 * bookmark 정보 in (bookmarkList의 bookmark_id) --> List<Bookmark> 반환
	 */
	
	// getBookmarkListByCategoryId : categoryId를 기준으로 BookmarkList 조회
	public static List<Bookmark> getBookmarkListByCategoryId(Long categoryId){
		EntityManager em = DbUtil.getEntityManager();
		Category category;
		List<Bookmark> bcList;
		
		try {
			// category 찾기
			category = CategoryRepository.findCategoryById(em, categoryId);
			// bookmarkList 찾기
			bcList = BookmarkCategoryRepository.findBookmarkIdByCategoryId(em, category);
		} catch(Exception e) {
			throw new IllegalArgumentException("북마크 조회 실패!");
		} finally {
			em.close();
		}
		
		return bcList;
	}
	
	// bc 관계 추가
	/*
	 * Bookmark 추가 시 bc 연관관계 추가(Bookmark insert 시 Controller단에서 if로 CategoryName 값 있는지 없는지 여부 확인)
	 * 1) Category 있을 경우, bc 연관관계 추가
	 * 2) Category 없을 경우, bookmark만 추가 --> BookmarkService.saveBookmark
	 */
	
	
	// createBookmarkCategory : Bookmark 추가 시 bc 연관관계 추가(Bookmark in Category)
	public static void createBookmarkCategory(Bookmark bookmark, Category category) {
		EntityManager em = DbUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		BookmarkCategory bookmarkcategory = BookmarkCategory.builder()
															.bookmark(bookmark)
															.category(category)
															.build();
		
		tx.begin();
		
		try {
			BookmarkCategoryRepository.insertBookmarkCategory(em, bookmarkcategory);
			tx.commit();
		} catch (IllegalArgumentException e) {
			tx.rollback();
			throw new IllegalArgumentException("저장 실패 : 오류가 발생했습니다.");
		} finally {
			em.close();
		}
	}
	
	// bc 관계 삭제(Bookmark 삭제 시 BookmarkCategory 삭제)
	/*
	 * bookmark 상세 페이지 -> bookmark Id get -> bookmark return
	 * bookmark Id 기준으로 b-c category 관계(List<BookmarkCategory>) 삭제
	 */
	
	// deleteBookmarkCategoryByBookmarkId : BookmarkId 기준으로 BookmarkCategory 삭제
	public static void deleteBookmarkCategoryByBookmarkId(Long bookmarkId) {
		EntityManager em = DbUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			
			Bookmark deleteBookmark = BookmarkRepository.findById(bookmarkId, em);
			
			if(deleteBookmark == null) {
				throw new NoResultException();
			}

			List<BookmarkCategory> deleteBcList = BookmarkCategoryRepository.findBookmarkCategoryByBookmarkId(em, deleteBookmark);
			
			// b-c 연관관계 없을 경우, BookmarkService.deleteBookmark만 실행되면 됨!
			if(deleteBcList.size() == 0) {
				return;
			}
			
			for(BookmarkCategory bc : deleteBcList) {
				BookmarkCategoryRepository.deleteBookmarkCategoryByCategoryId(em, bc);
			}
			
			tx.commit();
		} catch(NoResultException e) {
			tx.rollback();
			throw new IllegalArgumentException("삭제 실패 : 북마크가 존재하지 않습니다.");
		} finally {
			em.close();
		}
		
	}
	
	// b-c 관계 삭제(Category 삭제 시 BookmarkCategory 삭제)
	/*
	 * Category 상세 페이지 -> Category Id get -> category return
	 * category Id 기준으로 해당 카테고리와 bc 관계가 있는 Bookmark 찾기
	 * category Id 기준으로 b-c bookmark 관계 (List<BookmarkCategory>) 삭제
	 * 1) 연관관계 삭제 -> 연관관계에 있는 Bookmark도 같이 삭제(1:1 매핑일 경우)
	 * 2) 연관관계만 삭제(1:다 매핑일 경우)
	 */
	
	// deleteBookmarkByCategoryId : CategoryId 기준으로 BookmarkCategory / Bookmark 삭제...
	public static void deleteBookmarkByCategoryId(Long categoryId) {
		EntityManager em = DbUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			
			Category deleteCategory = CategoryRepository.findCategoryById(em, categoryId);
			
			if(deleteCategory == null) {
				throw new NoResultException();
			}
			
			// categoryId에 해당하는 bc 연관관계 있는 bookmarkList 조회
			List<Bookmark> bookmarkListByCategoryId = BookmarkCategoryRepository.findBookmarkIdByCategoryId(em, deleteCategory);
			
			// bc 연관관계가 있는 bookmarkList 없을 경우, CategoryService.deleteCategory만 실행되면 됨!
			if(bookmarkListByCategoryId.size() == 0) {
				return;
			}
			
			// categoryId와 일대일로 매핑된 bookmarkList(삭제할 bookmarkList) 조회
			List<Bookmark> deleteBookmarkList = new ArrayList<Bookmark>();
			
			for(Bookmark bookmark : bookmarkListByCategoryId) {
				List<BookmarkCategory> tempBcList = BookmarkCategoryRepository.findBookmarkCategoryByBookmarkId(em, bookmark);
				if(tempBcList.size() == 1) { // size > 1일 경우, 해당 bookmark가 category 1, 2에 모두 매핑된 상태
					deleteBookmarkList.add(bookmark);
				}
			}
			
			// 해당 category에만 속하는 bookmark 삭제
			for(Bookmark bookmark : deleteBookmarkList) {
				BookmarkRepository.deleteBookmark(bookmark, em);
			}
			
			tx.commit();
		} catch(NoResultException e) {
			tx.rollback();
			throw new IllegalArgumentException("삭제 실패 : 카테고리가 존재하지 않습니다.");
		} finally {
			em.close();
		}
		
	}

}
