package category.repository;

import java.util.List;

import category.entity.Category;
import common.util.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CategoryRepository {
	
	// category_id를 이용하여 Category 찾기
	public static Category findCategoryById(EntityManager em, Long categoryId){
		String sql = "SELECT c FROM Category c WHERE c.categoryId = :categoryId";
		Category foundCategory = em.createQuery(sql, Category.class)
								   .setParameter("categoryId", categoryId)
								   .getSingleResult();
		
		return foundCategory;
	}
	
	// category_name을 이용하여 Category 존재 여부 확인
	public static boolean existCategoryByName(EntityManager em, String categoryName) {
		String sql = "SELECT c FROM Category c WHERE c.categoryName = :categoryName";
		Category existCategory = em.createQuery(sql, Category.class)
								 .setParameter("categoryName", categoryName.toUpperCase())
								 .getSingleResult();
		
		if(existCategory != null) {
				return true;
		}

		return false;
	}
	
	// 모든 Category 찾기
	public static List<Category> findAllCategories(EntityManager em) {
		String sql = "SELECT c FROM Category c";
		List<Category> allCategories = em.createQuery("SELECT c FROM Category c", Category.class)
										 .getResultList();
		return allCategories;
	}
	
	// Category 추가
	public static void createCategory(EntityManager em, Category newCategory) {
		em.persist(newCategory);
	}
	
	// Category 수정
	public static void updateCategory(EntityManager em, Long categoryId, String newCategoryName) {
		Category changeCategory = findCategoryById(em, categoryId);
		
		if(changeCategory != null) {
			changeCategory.changeCategoryName(newCategoryName);
		}
	}
	
	// Category 삭제
	public static void deleteCategoryById(EntityManager em, Long categoryId) {
		Category deletedCategory = em.find(Category.class, categoryId);
		em.remove(deletedCategory);
	}
	
	
}
