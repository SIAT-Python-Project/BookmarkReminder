package bookmark.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookmark.service.BookmarkService;

@WebServlet("/bookmark/insert")
public class BookmarkInsertController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookmarkName = request.getParameter("bookmarkName");
        String bookmarkUrl = request.getParameter("url");
        String categoryNames = request.getParameter("categoryName");
        
        List<String> categoryList = new ArrayList<>();
        
        // 콤마로 구분된 카테고리 이름 처리
        if (categoryNames.contains(",")) {
            categoryList = Arrays.asList(categoryNames.split("\\s*,\\s*"));
        } else {
            categoryList.add(categoryNames);
        }
        
		HttpSession session = request.getSession(false);
		
		try {
			Long userId = Long.parseLong(session.getAttribute("userId").toString());
			Long bookmarkId = BookmarkService.addBookmark(bookmarkName, bookmarkUrl, userId, categoryList);
			
			response.sendRedirect("/bookmark/detail?bookmarkId=" + bookmarkId);
			
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/views/error/error.jsp").forward(request, response);
		}
		
		
	}

}
