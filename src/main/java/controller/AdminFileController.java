package controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.File;
import model.bean.Login;
import model.bo.FileBO;
import model.bo.UserBO;


@WebServlet("/admin-file")
public class AdminFileController extends HttpServlet {
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
			FileBO fileBO = new FileBO();
			List<File> listFile = fileBO.getAllFile();
			Collections.reverse(listFile);
			request.setAttribute("listFile", listFile);
			request.getRequestDispatcher("admin-file.jsp").forward(request, response);
		}
		
		response.sendRedirect("homepage");
	}

}
