package category.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import category.entity.Category;
import category.service.CategoryService;

@WebServlet("/updateCategory.do")
public class updateCategoryController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/views/error/error.jsp";
		
		HttpSession session = request.getSession(false);
		String userId = session.getAttribute("userId").toString();
		String newCategoryName = request.getParameter("categoryName");
		String categoryId = request.getParameter("categoryId");
		
		try {
			CategoryService.updateCategory(Long.parseLong(userId), Long.parseLong(categoryId), newCategoryName);
			
			url = "/category.do?categoryId=" + categoryId;
			response.sendRedirect(url);
		} catch (IllegalArgumentException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
