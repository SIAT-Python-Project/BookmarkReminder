package common.filter;

import user.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/getUserCategory.do", "/userAll.do"})
public class AdminFilter extends HttpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        Role role = (Role) session.getAttribute("role");

        String url = "/views/error/error.jsp";

        if (role != Role.ADMIN) {
            request.setAttribute("error", "인증 실패: ADMIN 권한이 없습니다.");
            httpRequest.getRequestDispatcher(url).forward(request, response);
            return;
        }


        chain.doFilter(request, response);
    }
}
