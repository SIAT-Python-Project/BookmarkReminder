package user.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout.do")
public class LogoutController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/views/error/error.jsp";

        HttpSession session = request.getSession(false);

        if(session == null) {
            url = "/main.do";
            response.sendRedirect(url);
            return;
        }

        session.invalidate();
        session = null;
        url = "/main.do";

        response.sendRedirect(url);

    }
}
