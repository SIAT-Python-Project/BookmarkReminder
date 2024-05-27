package category.repository;

import java.util.List;

import category.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import user.entity.User;

public class CategoryRepository {
	
	// category_id를 이용하여 Category 찾기
	public static Category findCategoryById(EntityManager em, Long categoryId) {
		Category category = em.find(Category.class, categoryId);
		return category;
	}
	
	// user_id를 이용하여 Category 찾기
	public static List<Category> findCategoriesByUserId(EntityManager em, User user){
		String sql = "SELECT c FROM Category c WHERE c.user = :user";
		List<Category> categoriesByUserId = em.createQuery(sql, Category.class)
											  .setParameter("user", user)
											  .getResultList();
		return categoriesByUserId;
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
	
	// 카테고리 이름으로 Category 찾기
    public static Category findCategoryByName(EntityManager em, String categoryName, User user) {
        String sql = "SELECT c FROM Category c WHERE c.categoryName = :categoryName AND c.user = :user";
        TypedQuery<Category> query = em.createQuery(sql, Category.class);
        query.setParameter("categoryName", categoryName);
        query.setParameter("user", user);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
	
}
