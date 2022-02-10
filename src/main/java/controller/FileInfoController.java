package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.bean.File;
import model.bean.Login;
import model.bo.CategoryBO;
import model.bo.FileBO;
import model.bo.ShareFileBO;
import model.bo.UserBO;


@WebServlet("/file-info")
public class FileInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		UserBO usrBO = new UserBO();
		int fileID = Integer.valueOf(request.getParameter("file-id"));
		FileBO fileBO = new FileBO();
		ShareFileBO shareBO = new ShareFileBO();
		
		if (user == null) {
			request.setAttribute("mess", "Please login!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		boolean check = false;
		
		if (user.getUserID() == fileBO.getFileByID(fileID).getUserID()) check = true;
		
		if (usrBO.getUserByUserID(user.getUserID()).getRoleID() == 1) check = true;
		
		if (shareBO.isValidUser(user.getUserID(), fileID)) check = true;
		
		if (shareBO.isSharePublic(fileID)) check = true;
		
		if (check) {
			CategoryBO categoryBO = new CategoryBO();
			List<Category> listCategory = categoryBO.getAllCategories();
			File file = fileBO.getFileByID(fileID);
			
			request.setAttribute("file", file);
			request.setAttribute("listCategory", listCategory);
			request.getRequestDispatcher("file-info.jsp").forward(request, response);
		} else response.sendRedirect("homepage");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
}
