package user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookmark.entity.Bookmark;
import bookmark.service.BookmarkService;
import bookmarkcategory.service.BookmarkCategoryService;
import category.entity.Category;
import category.service.CategoryService;
import user.service.UserService;

@WebServlet("/userDelete.do")
public class UserDeleteController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/views/error/error.jsp";
		HttpSession session = request.getSession(false);
		
		try {
		Long userId = Long.parseLong(session.getAttribute("userId").toString());
		
		// User entity Bookmark, Category cascade 설정 후 User 삭제하면 안 되는지...?
		
		// 해당 User가 생성한 카테고리 가져와서 모두 삭제(카테고리 및 bc 연관 관계 삭제)
		List<Category> deleteCategoryList = CategoryService.getCategoriesByUserId(userId);
		for(Category category : deleteCategoryList) {
			CategoryService.deleteCategory(category.getCategoryId());
		}
		
		// 해당 User가 생성한 카테고리에 포함되지 않은 북마크 삭제(기존 user가 만든 북마크 모두 카테고리에 포함되지 X)
		List<Bookmark> deleteBookmarkListNotInCategory = BookmarkCategoryService.getBookmarkListNotInCategory(userId);
		for(Bookmark bookmark : deleteBookmarkListNotInCategory) {
			BookmarkService.deleteBookmark(bookmark.getBookmarkId());
		}
		
		// 마지막으로 User 삭제
		UserService.deleteUser(userId);
		
		// Logout Controller로 이동 --> 세션 무효화
		url = "/logout.do";
		response.sendRedirect(url);
		
		} catch (IllegalArgumentException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher(url);
		}
		
	}
	
	
	
	
}
