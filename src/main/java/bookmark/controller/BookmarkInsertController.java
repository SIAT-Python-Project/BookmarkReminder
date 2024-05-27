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
        
//		HttpSession session = request.getSession();
//		Long userId = Long.parseLong((String) session.getAttribute("userId"));
        
		Long userId = 1L;
		BookmarkService.addBookmark(bookmarkName, bookmarkUrl, userId, categoryList);
		       
        response.sendRedirect("/views/category/categorydetail.jsp");
		
	}

}
