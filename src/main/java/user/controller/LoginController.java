package user.controller;

import user.entity.Role;
import user.entity.User;
import user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/views/error/error.jsp";

        String loginId = request.getParameter("loginId");
        String pw = request.getParameter("pw");

        try {
            User user = UserService.login(loginId, pw);

            HttpSession session = request.getSession();

            session.setAttribute("userId", user.getId());
            session.setAttribute("nickname", user.getNickname());
            session.setAttribute("role", user.getRole());

            if (user.getRole() == Role.ADMIN) {
                session.setAttribute("adminName", user.getNickname());
                session.setAttribute("adminId", user.getId());
            }

            url = "/main.do";

            response.sendRedirect(url);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
