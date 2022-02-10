package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Login;
import model.bean.User;
import model.bo.LoginBO;
import model.bo.UserBO;

@WebServlet("/add-user")
public class AddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		UserBO usrBO = new UserBO();
		
		if (user == null) {
			request.setAttribute("mess", "Session timeout! Please login again!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		} else if (usrBO.getUserByUserID(user.getUserID()).getRoleID() == 1){
			request.getRequestDispatcher("adduser.jsp").forward(request, response);
		}
		
		response.sendRedirect("homepage");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		
		if (user == null) {
			request.setAttribute("mess", "Session timeout! Please login again!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		LoginBO lgBO = new LoginBO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String room = request.getParameter("room");
        String mssv = request.getParameter("mssv");
        String role = request.getParameter("role");
        
        if (!lgBO.CheckAccount(username)) {
        	Login lg = new Login(username, password, 0);
            User usr = new User(name, email, room, mssv, Integer.valueOf(role));
            lgBO.RegisterUser(lg, usr);
            request.setAttribute("mess", "Registration successful!");
            request.getRequestDispatcher("adduser.jsp").forward(request, response);
        } else {
        	request.setAttribute("mess", "Username is not available!");
            request.getRequestDispatcher("adduser.jsp").forward(request, response);
        }
	}

}
