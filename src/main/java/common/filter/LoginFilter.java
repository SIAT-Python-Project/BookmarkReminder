package common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"*.do"},
        initParams = {
                @WebInitParam(name = "excludeURL", value = "/main.do,/loginForm.do,/login.do,/signup.do,/signupForm.do")
        })
public class LoginFilter extends HttpFilter implements Filter {

    private List<String> excludeURLList;

    public void init(FilterConfig config) {
        excludeURLList = new ArrayList<>();
        excludeURLList = Arrays.asList(config.getInitParameter("excludeURL").split(","));
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        String url = "/views/error/error.jsp";

        if(!excludeURLList.contains(httpRequest.getRequestURI())) {
            if(session == null || session.getAttribute("userId") == null) {
                request.setAttribute("error", "인증 실패: 새션이 없습니다.");
                httpRequest.getRequestDispatcher(url).forward(request, response);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
