package memo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memo.service.MemoService;

@WebServlet("/memo/insert")
public class MemoInsertController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long bookmarkId = Long.parseLong(request.getParameter("bookmarkId"));
		String memoContent = request.getParameter("memo");       
              
        MemoService.addMemo(bookmarkId, memoContent);
        response.sendRedirect("/bookmark/detail?bookmarkId=" + bookmarkId);
	}

}
