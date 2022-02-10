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

@WebServlet("/edit-file")
public class EditFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		
		if (user == null) {
			request.setAttribute("mess", "Session timeout! Please login again!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		int fileID = Integer.valueOf(request.getParameter("file-id"));
		FileBO fileBO = new FileBO();
		File file = fileBO.getFileByID(fileID);
		CategoryBO categoryBO = new CategoryBO();
		List<Category> listCategory = categoryBO.getAllCategories();
		
		request.setAttribute("file", file);
		request.setAttribute("listCategory", listCategory);
		request.getRequestDispatcher("editfile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		
		if (user == null) {
			request.setAttribute("mess", "Session timeout! Please login again!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		int fileID = Integer.valueOf(request.getParameter("file-id"));
		String fileName = request.getParameter("file-name");
		String description = request.getParameter("description");
		int cateID = Integer.valueOf(request.getParameter("category"));
		
		FileBO fileBO = new FileBO();
		File file = fileBO.getFileByID(fileID);
		
		fileBO.updateFile("Name", fileName, fileID, file.getFolderID());
		fileBO.updateFile("Description", description, fileID, file.getFolderID());
		
		CategoryBO cateBO = new CategoryBO();
		cateBO.addFileCate(fileID, cateID);
		
		List<Category> listCategory = cateBO.getAllCategories();
		
		file = fileBO.getFileByID(fileID);
		
		request.setAttribute("file", file);
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("mess", "Edit file successfully!");
		request.getRequestDispatcher("editfile.jsp").forward(request, response);
	}

}
