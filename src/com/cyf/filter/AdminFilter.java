package com.cyf.filter;

import com.cyf.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//执行顺序：最先初始化init(),然后doFilter函数，最后destroy()
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("==================================【初始化管理员拦截器】");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");  //获取当前用户
        if (currentUser != null && currentUser.getUserLevelId() == 0) {  //检查currentUser判断是否登录且是否为管理员
            filterChain.doFilter(servletRequest, servletResponse);  //管理员已登录，放行
        } else {
            response.sendRedirect("/403.jsp");  //未登录或者没有权限进行操作
        }
    }

    @Override
    public void destroy() {
        System.out.println("==================================【销毁管理员拦截器】");
    }
}
