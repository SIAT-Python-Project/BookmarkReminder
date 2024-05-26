package bookmark.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookmark.dto.BookmarkDTO;
import bookmark.service.BookmarkService;
import memo.dto.MemoDTO;
import memo.service.MemoService;

@WebServlet("/bookmark/detail")
public class BookmarkDetailController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long bookmarkId = Long.parseLong(request.getParameter("bookmarkId"));
		BookmarkDTO bookmarkDto = BookmarkService.getBookmark(bookmarkId);

		try {
			List<MemoDTO> memos = MemoService.findMemosByBookmarkId(bookmarkId);
			request.setAttribute("memos", memos);
		} catch (IllegalArgumentException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}

		request.setAttribute("bookmark", bookmarkDto);
		request.getRequestDispatcher("/views/bookmark/bookmarkdetail.jsp").forward(request, response);
	}

}
