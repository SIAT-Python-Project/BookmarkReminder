package category.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookmark.entity.Bookmark;
import bookmarkcategory.service.BookmarkCategoryService;
import category.entity.Category;
import category.service.CategoryService;

/**
 * Servlet implementation class getCategoryController
 */
@WebServlet("/category.do")
public class getCategoryController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/views/error/error.jsp";
		
		String categoryId = request.getParameter("categoryId");
		
		try {
			Category category = CategoryService.getCategoryByCategoryId(Long.parseLong(categoryId));
			List<Bookmark> bookmarkList = BookmarkCategoryService.getBookmarkListByCategoryId(Long.parseLong(categoryId));
			
			request.setAttribute("category", category);
			request.setAttribute("bookmarkList", bookmarkList);
			
			url = "/views/category/categorydetail.jsp";
			request.getRequestDispatcher(url).forward(request, response);
			
		} catch(IllegalArgumentException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher(url).forward(request, response);
		}
		
		
	}

}
