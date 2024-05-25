package category.service;

import java.util.List;

import category.entity.Category;
import category.repository.CategoryRepository;
import common.util.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import user.entity.User;

public class CategoryService {
	// Serivce에서 예외 처리/구현 해야 할 repository
	// 1) boolean existCategoryByName
	// 2) findCategoryByCategoryId --> IllegalArgumentException (find 이용 시 primary key X)
	// 3) updateCategory --> IllegalArgumentException (find 이용 시 primary key X)
	// 4) deleteCategory --> IllegalArgumentException (find 이용 시 primary key X)
	// 5) createCategory --> 1) 이용하여 이름 중복 확인
	// 6) findAllCategories
	// 7) findCategoryByUserId
	
	public static EntityManager em;
	public static EntityTransaction tx;
	
	// categoryName 기준으로 Category 존재 여부 확인 : Name에 해당하는 Category가 있는지 확인! (전체 카테고리 가져온 후에 각각 이름 비교하여 있으면 true, 없으면 false)
	public static boolean existCategoryByName(String categoryName) {
		em = DbUtil.getEntityManager();
	
		List<Category> allCategories = CategoryRepository.findAllCategories(em);
		for(Category category : allCategories) {
			if(category.getCategoryName().toUpperCase().equals(categoryName.toUpperCase())) {
				return true;
			}
		}
		em.close();
		return false;
	}
	
	// get all categories
	
	public static List<Category> getAllCategories(){
		em = DbUtil.getEntityManager();
		List<Category> allCategories = CategoryRepository.findAllCategories(em);
		em.close();
		return allCategories;
	}
	
	// get Category by CategoryId
	public static Category getCategoryByCategoryId(Long categoryId) {
		em = DbUtil.getEntityManager();
	
		try {
			Category foundCategory = CategoryRepository.findCategoryById(em, categoryId);
			
			if(foundCategory != null) {
				return foundCategory;
			} else {
				throw new IllegalArgumentException("카테고리 확인 불가!");
			}
		} catch(NoResultException e) {
			throw new IllegalArgumentException("카테고리 조회 실패!");
		} finally {
			em.close();
		}
	}
	
	// get Category by UserId
	public static List<Category> getCategoriesByUserId(User user){
		em = DbUtil.getEntityManager();
		try {
			List<Category> categoryList = CategoryRepository.findCategoriesByUserId(em, user);
			return categoryList;
		} catch (Exception e) {
			throw new IllegalArgumentException("카테고리 조회 실패!");
		} finally {
			em.close();
		}
	}
	
	// create Category
	public static Category createCategory(String categoryName) {
		em = DbUtil.getEntityManager();
		tx = em.getTransaction();
		
		tx.begin();
		
		Category newCategory = null;
		
		try {
			// 카테고리 이름 중복 확인
			if(existCategoryByName(categoryName)) {
				System.out.println("카테고리 생성 실패");
				throw new IllegalArgumentException("카테고리 생성 실패 : 카테고리 이름 중복!");
			} else {
				newCategory = Category.builder()
	   					   			  .categoryName(categoryName)
	   					   			  .build();
				CategoryRepository.createCategory(em, newCategory);
			}
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
			throw new IllegalArgumentException("카테고리 생성 실패");
		} finally {
			em.close();
		}
		
		return newCategory;
		
	}
	
	// update Category
	public static Category updateCategory(Long categoryId, String newCategoryName) {
		em = DbUtil.getEntityManager();
		tx = em.getTransaction();
		
		Category category = null;
		
		tx.begin();
		
		try {
			
			// category 존재 여부 확인
			category = CategoryRepository.findCategoryById(em, categoryId);
			
			// 카테고리 존재할 경우
			if(category != null) {
				// 변경할 이름 중복 여부 확인
				// 중복일 경우
				if(existCategoryByName(newCategoryName)) {
					throw new IllegalArgumentException("카테고리 변경 실패 : 카테고리 이름 중복!");
				// 중복이 아닐 경우
				} else {
					category.changeCategoryName(newCategoryName);
				}
			// 카테고리가 존재하지 않을 경우
			} else {
				throw new IllegalArgumentException("카테고리 변경 실패 : 카테고리 확인 불가!");
			}
			tx.commit();
			
		} catch (NoResultException e) {
			tx.rollback();
			throw new IllegalArgumentException("카테고리 조회 실패!");
		} finally {
			em.close();
		}
		
		return category;
	}
	
	// delete Category
	public static void deleteCategory(Long categoryId) {
		em = DbUtil.getEntityManager();
		tx = em.getTransaction();
		
		tx.begin();
		
		try {
			Category category = CategoryRepository.findCategoryById(em, categoryId);
			if(category != null) {
				CategoryRepository.deleteCategory(em, category);
			} else {
				throw new IllegalArgumentException("카테고리 삭제 실패 : 카테고리 확인 불가!");
			}
			
			tx.commit();
		} catch (NoResultException e) {
			tx.rollback();
			throw new IllegalArgumentException("카테고리 조회 실패!");
		} finally {
			em.close();
		}
	}
	
}
