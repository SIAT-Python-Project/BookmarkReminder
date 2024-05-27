package category.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookmarkcategory.service.BookmarkCategoryService;
import category.service.CategoryService;

/**
 * Servlet implementation class deleteCategoryForm
 */
@WebServlet("/deleteCategory.do")
public class deleteCategoryController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/views/error/error.jsp";
		String categoryId = request.getParameter("categoryId");
		
		try {
			BookmarkCategoryService.deleteBookmarkByCategoryId(Long.parseLong(categoryId)); // bookmark - cascade로 연관 관계도 날아감
			CategoryService.deleteCategory(Long.parseLong(categoryId)); // category - cascade로 연관 관계도 날아감
			
			url = "/mainPage.do";
			response.sendRedirect(url);
		} catch (IllegalArgumentException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
