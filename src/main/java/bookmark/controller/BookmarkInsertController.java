package bookmark.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookmark.service.BookmarkService;
import user.dto.UserDTO;
import user.service.UserService;

@WebServlet("/bookmark/insert")
public class BookmarkInsertController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookmarkName = request.getParameter("bookmarkName");
		String bookmarkUrl = request.getParameter("url");
		Long categoryId = Long.parseLong(request.getParameter("categoryId"));
		
//		HttpSession session = request.getSession();
//		Long userId = Long.parseLong((String) session.getAttribute("userId"));
		
		Long testuserId = 1L;
		BookmarkService.addBookmark(bookmarkName, bookmarkUrl, testuserId, categoryId);
		
		response.sendRedirect("/views/category/categorydetail.jsp");
	}

}
