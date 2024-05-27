package bookmark.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookmark/insert/form")
public class BookmarkInsertFormController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("categoryName", request.getParameter("categoryName"));
		request.getRequestDispatcher("/views/bookmark/bookmarkinsert.jsp").forward(request, response);
	}

}
