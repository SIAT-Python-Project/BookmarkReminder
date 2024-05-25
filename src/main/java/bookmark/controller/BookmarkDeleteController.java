package bookmark.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookmark.service.BookmarkService;

@WebServlet("/bookmark/delete")
public class BookmarkDeleteController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long bookmarkId = Long.parseLong(request.getParameter("bookmarkId"));
		BookmarkService.deleteBookmark(bookmarkId);
		
		response.sendRedirect("/views/category/categorydetail.jsp");
	}

}
