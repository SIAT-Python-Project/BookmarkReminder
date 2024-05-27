package user.controller;

import user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/passwordUpdate.do")
public class PasswordUpdateController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/views/error/error.jsp";
        HttpSession session = request.getSession();

        Long id = (Long) session.getAttribute("userId");
        String originPw = request.getParameter("originPw");
        String newPw = request.getParameter("pw");
        String pwConfirm = request.getParameter("pwConfirm");

        try {
            UserService.updatePassword(id, originPw, newPw, pwConfirm);
            url = "/user.do";
            response.sendRedirect(url);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(url).forward(request, response);
        }


    }
}
