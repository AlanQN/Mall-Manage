<%@ page import="cn.edu.scau.interceptor.LoginInterceptor" %>
<%
    if (session.getAttribute(LoginInterceptor.USER_INFO_KEY) != null) {
        response.sendRedirect("/se12/html/static-html/test/index.html");
    } else {
        response.sendRedirect("/se12/html/static-html/test/login.html");
    }
%>


