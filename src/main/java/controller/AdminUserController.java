package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Login;
import model.bean.User;
import model.bo.UserBO;

@WebServlet("/admin-user")
public class AdminUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		UserBO usrBO = new UserBO();
		
		if (user == null) {
			request.setAttribute("mess", "Session timeout! Please login again!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		} else if (usrBO.getUserByUserID(user.getUserID()).getRoleID() == 1){
			List<User> listUser = usrBO.getAllUser();
			request.setAttribute("listUser", listUser);
			request.getRequestDispatcher("admin-user.jsp").forward(request, response);
		}
		
		response.sendRedirect("homepage");
	}
}
