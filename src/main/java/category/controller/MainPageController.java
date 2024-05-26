package category.controller;

import bookmark.entity.Bookmark;
import bookmarkcategory.entity.BookmarkCategory;
import bookmarkcategory.service.BookmarkCategoryService;
import category.entity.Category;
import category.service.CategoryService;
import user.entity.User;
import user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/mainPage.do")
public class MainPageController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/views/error/error.jsp";
        HttpSession session = request.getSession();

        Long userId = Long.parseLong(session.getAttribute("userId").toString());

        try {
            List<Category> categories = CategoryService.getCategoriesByUserId(userId);
            List<Bookmark> bookmarks = BookmarkCategoryService.getBookmarkListNotInCategory(userId);
            request.setAttribute("categories", categories);
            request.setAttribute("bookmarks", bookmarks);

            url = "/views/category/mainPage.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(url).forward(request, response);
        }

    }
}
