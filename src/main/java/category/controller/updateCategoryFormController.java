package category.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.entity.Category;
import category.service.CategoryService;

/**
 * Servlet implementation class updateCategoryFormController
 */
@WebServlet("/updateCategoryForm.do")
public class updateCategoryFormController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/views/error/error.jsp";
		
		String categoryId = request.getParameter("categoryId");
		
		try {
			// 검증
			Category category = CategoryService.getCategoryByCategoryId(Long.parseLong(categoryId));
			if(category == null) {
				throw new IllegalArgumentException("조회 실패 : 카테고리가 존재하지 않습니다.");
			}
			
			url = "/views/category/categoryupdate.jsp";
			request.setAttribute("category", category);
			request.getRequestDispatcher(url).forward(request, response);
		} catch(IllegalArgumentException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
