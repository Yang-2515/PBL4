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

@WebServlet("/my-files")
public class UserpageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		if (user == null) {
			request.setAttribute("mess", "Session timeout! Please login again!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		FolderBO folderBO = new FolderBO();
		CategoryBO categoryBO = new CategoryBO();
		List<Folder> listFolder = folderBO.getFolderByUserID(user.getUserID());
        List<Category> listCategory = categoryBO.getAllCategories();
        
        String mode = request.getParameter("m");
		
		if (mode != null) {
			request.setAttribute("mess", "Sharing mode changed to "+mode+" successfully!");
		}
        
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("folderID", 0);
		request.setAttribute("listFolder", listFolder);
		request.getRequestDispatcher("user-file.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		if (user == null) {
			request.setAttribute("mess", "Session timeout! Please login again!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		String folderID = request.getParameter("folder-id");
		FileBO fileBO = new FileBO();
		List<File> listFile = fileBO.getFileByFolderID(Integer.valueOf(folderID));
		
		CategoryBO categoryBO = new CategoryBO();
		List<Category> listCategory = categoryBO.getAllCategories();
		
		
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("folderID", folderID);
		request.setAttribute("listFile", listFile);
		request.getRequestDispatcher("user-file.jsp").forward(request, response);
	}
}
