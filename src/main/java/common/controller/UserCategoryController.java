package common.controller;

import user.dto.UserDTO;
import user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/getUserCategory.do")
public class UserCategoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/views/error/error.jsp";

        HttpSession session = request.getSession();

        Long userId = Long.parseLong(request.getParameter("userId"));

        try {
            UserDTO userDTO = UserService.findById(userId);
            session.setAttribute("userId", userId);
            session.setAttribute("nickname", userDTO.getNickname());

            url = "/mainPage.do";
            response.sendRedirect(url);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
