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

@WebServlet("/edit-user")
public class EditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		UserBO usrBO = new UserBO();
		
		if (user == null) {
			request.setAttribute("mess", "Session timeout! Please login again!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		} else if (usrBO.getUserByUserID(user.getUserID()).getRoleID() == 1){
			int userID = Integer.valueOf(request.getParameter("user-id"));
			
			User usr = usrBO.getUserByUserID(userID);
			
			request.setAttribute("user", usr);
			request.getRequestDispatcher("edituser.jsp").forward(request, response);
		}
		
		response.sendRedirect("homepage");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = Integer.valueOf(request.getParameter("user-id"));
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        String room = request.getParameter("room");
        String mssv = request.getParameter("mssv");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        
        UserBO usrBO = new UserBO();
		User usr = usrBO.getUserByUserID(userID);
		
		LoginBO lgBO = new LoginBO();
		Login lg = lgBO.getLoginByUserID(userID);
		if (password != null) lg.setPassword(password);
		lgBO.UpdateLogin(lg);
		
		usr.setName(name);
		usr.setEmail(email);
		usr.setMssv(mssv);
		usr.setRoom(room);
		usr.setRoleID(Integer.valueOf(role));
		
		usrBO.UpdateUser(usr);
		
		request.setAttribute("mess", "Update successfully!");
		request.setAttribute("user", usr);
		request.getRequestDispatcher("edituser.jsp").forward(request, response);
	}
}
