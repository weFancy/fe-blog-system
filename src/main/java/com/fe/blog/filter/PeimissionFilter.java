package com.fe.blog.filter;

import com.fe.blog.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author Fancy.we
 */
@WebFilter(filterName = "PermissionServlet",urlPatterns = {"/pages/*"})
public class PeimissionFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //获取session，效验用户信息（需要把req。resp强转为request，response,因为他们本来就是同一哥对象）
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //获取session，效验用户信息
        HttpSession session = ((HttpServletRequest) request).getSession();
        //然后获取session中的数据
        User user = (User) session.getAttribute("user");
        //判断是否从服务器中得到了这个user对象，来进行过滤,如果等于空的话，就让其跳转到登录界面重新去登陆，否则直接返回
        if (user==null) {
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath()+"/login.html");
            return;
        }
        //user 不为空的话，就会直接放行到下面的语句
        chain.doFilter(req, resp);

    }
}


