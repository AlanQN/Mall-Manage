<%@ page import="cn.edu.scau.interceptor.LoginInterceptor" %>
<%
    if (session.getAttribute(LoginInterceptor.USER_INFO_KEY) != null) {
        response.sendRedirect("/html/static-html/test/index.html");
    } else {
        response.sendRedirect("/html/static-html/test/login.html");
    }
%>


