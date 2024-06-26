package category.controller;

import category.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/categoryInsert.do")
public class CategoryInsertController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/views/error/error.jsp";

        HttpSession httpSession = request.getSession();

        String categoryName = request.getParameter("categoryName");
        Long userId = Long.parseLong(httpSession.getAttribute("userId").toString());


        try {
            CategoryService.createCategory(categoryName, userId);
            url = "/mainPage.do";
            response.sendRedirect(url);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
