package memo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memo.service.MemoService;

@WebServlet("/memo/delete")
public class MemoDeleteController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long memoId = Long.parseLong(request.getParameter("memoId"));
		MemoService.deleteMemo(memoId);
		
		response.sendRedirect("/bookmark/detail?bookmarkId=" + request.getParameter("bookmarkId"));
	}

}
