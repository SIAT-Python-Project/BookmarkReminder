package user.controller;

import org.json.JSONObject;
import user.entity.Role;
import user.entity.User;
import user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/views/error/error.jsp";

        JSONObject body = new JSONObject(
                new BufferedReader(
                        new InputStreamReader(request.getInputStream())
                )
                        .lines()
                        .collect(Collectors.joining()));

        String loginId = body.getString("loginId");
        String pw = body.getString("pw");

        JSONObject json = new JSONObject();

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
            json.put("loginSuccess", true);
            json.put("redirectURL", url);
        } catch (IllegalArgumentException e) {
            json.put("loginSuccess", false);
            json.put("message", e.getMessage());
        }
        response.setContentType("application/json");
        response.getWriter().write(json.toString());
    }
}
