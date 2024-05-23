package category.repository;

import java.util.List;

import category.entity.Category;
import common.util.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CategoryRepository {
	
	// category_id를 이용하여 Category 찾기
	public static Category findCategoryById(Long categoryId){
		EntityManager em = DbUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
		
		Category foundCategory = em.createQuery("SELECT c FROM Category c WHERE c.categoryId = :categoryId", Category.class)
								   .setParameter("categoryId", categoryId)
								   .getSingleResult();
		
		tx.commit();
		
		return foundCategory;
		
		} finally {
			em.close();
		}
		
	}
	
	// category_name 존재 여부 확인
	public static boolean existCategoryByName(String categoryName) {
		EntityManager em = DbUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			Category existCategory = em.createQuery("SELECT c FROM Category c WHERE c.categoryName = :categoryName", Category.class)
									 .setParameter("categoryName", categoryName.toUpperCase())
									 .getSingleResult();
			
			if(existCategory != null) {
				return true;
		}
		
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		return false;
	}
	
	// 모든 Category 찾기
	public static List<Category> findAllCategories() {
		EntityManager em = DbUtil.getEntityManager();
		
		List<Category> allCategories = em.createQuery("SELECT c FROM Category c", Category.class)
										 .getResultList();
		
		return allCategories;
	}
	
	// Category 추가
	public static boolean insertCategory(Category newCategory) {
		EntityManager em = DbUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			em.persist(newCategory);
			tx.commit();
			return true;
			
		} catch(Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		return false;
	}
	
	// Category 수정
	public static boolean updateCategory(Long categoryId, String newCategoryName) {
		EntityManager em = DbUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			Category category = em.find(Category.class, categoryId);
			
			if(category != null) {
				category.changeCategoryName(newCategoryName);
				
				tx.commit();
				return true;
			}
			
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		return false;
	}
	
	// Category 삭제
	public static boolean deleteCategory(Long categoryId) {
		EntityManager em = DbUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			Category deletedCategory = em.find(Category.class, categoryId);
			em.remove(deletedCategory);
			
			tx.commit();
			return true;
		} catch(Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		return false;
	}
	
	
}
