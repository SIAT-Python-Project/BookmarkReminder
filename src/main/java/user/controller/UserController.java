package user.controller;

import user.dto.UserDTO;
import user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user.do")
public class UserController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Long id = (Long) session.getAttribute("userId");

        if (session.getAttribute("adminId") != null) {
            id = (Long) session.getAttribute("adminId");
        }

        String url = "/views/error/error.jsp";

        try {
            UserDTO userDTO = UserService.findById(id);
            url = "/views/user/user.jsp";

            request.setAttribute("userDTO", userDTO);

            request.getRequestDispatcher(url).forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
