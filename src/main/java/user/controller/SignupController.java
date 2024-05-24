package user.controller;

import user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup.do")
public class SignupController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginId = request.getParameter("loginId");
        String pw = request.getParameter("pw");
        String pwConfirm = request.getParameter("pwConfirm");
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");

        String url = "/views/error/error.jsp";

        try {
            UserService.signup(loginId, pw, pwConfirm, nickname, email);
            url = "/main.do";
            response.sendRedirect(url);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
