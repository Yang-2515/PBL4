package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Login;
import model.bo.LoginBO;
import model.bo.UserBO;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password");
        LoginBO bo = new LoginBO();
        UserBO usrBO = new UserBO();
        boolean check = bo.isValidUser(username, password);
        if (!check) {
            request.setAttribute("mess", "Wrong user or pass");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            Login lo = bo.getLoginByUsername(username);
            session.setAttribute("acc", lo);
            session.setAttribute("role", usrBO.getUserByUserID(lo.getUserID()).getRoleID());
            session.setMaxInactiveInterval(50000);
            response.sendRedirect("homepage");
        }
    }
}
