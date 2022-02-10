package controller;

import java.io.IOException;
import java.util.ArrayList;
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
import model.bo.FolderBO;

@WebServlet("/delete")
public class DeleteFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		
		if (user == null) {
			request.setAttribute("mess", "Session timeout! Please login again!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		String fileID = request.getParameter("file-id");
		String folderID = request.getParameter("IDfolder");
		String delFolder = request.getParameter("delFolder");
		
		FileBO fileBO = new FileBO();
		FolderBO folderBO = new FolderBO();
		
		
		if (fileID != null) {
			List<Integer> listFileID = new ArrayList<Integer>();
			listFileID.add(Integer.valueOf(fileID));
			fileBO.deleteFiles(listFileID);
			
			List<File> listFile = fileBO.getFileByFolderID(Integer.valueOf(folderID));
			
			CategoryBO categoryBO = new CategoryBO();
			List<Category> listCategory = categoryBO.getAllCategories();
			
			request.setAttribute("listCategory", listCategory);
			request.setAttribute("folderID", folderID);
			request.setAttribute("listFile", listFile);
			request.getRequestDispatcher("user-file.jsp").forward(request, response);
		}
		
		if (delFolder != null) {
			folderBO.DelFolder(Integer.valueOf(delFolder));
			response.sendRedirect("my-files");
		}
	}
}
