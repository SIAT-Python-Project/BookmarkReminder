package category.repository;

import java.util.List;
import java.util.Optional;

import category.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class CategoryRepository {
	
	// category_id를 이용하여 Category 찾기
	public static Category findCategoryById(EntityManager em, Long categoryId) {
		Category category = em.find(Category.class, categoryId);
		return category;
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
	
	// Category 삭제
	public static void deleteCategory(EntityManager em, Category category) {
		em.remove(category);
	}
	
	
}