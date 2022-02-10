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
import model.bean.Folder;
import model.bean.Login;
import model.bo.CategoryBO;
import model.bo.FileBO;
import model.bo.FolderBO;
import model.bo.ShareFolderBO;
import model.bo.UserBO;


@WebServlet("/folder-info")
public class FolderInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		int folderID = Integer.valueOf(request.getParameter("folder-id"));
		FileBO fileBO = new FileBO();
		FolderBO folderBO = new FolderBO();
		UserBO usrBO = new UserBO();
		ShareFolderBO shareBO = new ShareFolderBO();
		
		if (user == null) {
			request.setAttribute("mess", "Please login!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		boolean check = false;
		
		Folder fd = folderBO.getFolderByID(folderID);
		
		if (user.getUserID() == fd.getUserID()) check = true;
		
		if (usrBO.getUserByUserID(user.getUserID()).getRoleID() == 1) check = true;
		
		if (shareBO.isValidUser(user.getUserID(), folderID)) check = true;
		
		if (shareBO.isSharePublic(folderID)) check = true;
		
		if (check) {
			CategoryBO categoryBO = new CategoryBO();
			List<Category> listCategory = categoryBO.getAllCategories();
			List<File> listFile = fileBO.getFileByFolderID(folderID);
			
			request.setAttribute("folderName", fd.getName());
			request.setAttribute("listFile", listFile);
			request.setAttribute("listCategory", listCategory);
			request.getRequestDispatcher("folder-info.jsp").forward(request, response);
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
