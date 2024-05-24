package common.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/main.do")
public class MainController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/views/error/error.jsp";

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            url = "/loginForm.do";
            response.sendRedirect(url);
            return;
        }

        url = "/getCategory.do";
        response.sendRedirect(url);
    }
}