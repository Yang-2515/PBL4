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
import model.bean.Folder;
import model.bean.Login;
import model.bo.CategoryBO;
import model.bo.FolderBO;


@WebServlet("/edit-folder")
public class EditFolderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		
		if (user == null) {
			request.setAttribute("mess", "Session timeout! Please login again!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		int folderID = Integer.valueOf(request.getParameter("folder-id"));
		FolderBO fdBO = new FolderBO();
		Folder folder = fdBO.getFolderByID(folderID);
		CategoryBO categoryBO = new CategoryBO();
		List<Category> listCategory = categoryBO.getAllCategories();
		
		request.setAttribute("folder", folder);
		request.setAttribute("listCategory", listCategory);
		request.getRequestDispatcher("editfolder.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int folderID = Integer.valueOf(request.getParameter("folder-id"));
		String folderName = request.getParameter("folder-name");
		String description = request.getParameter("description");
		FolderBO fdBO = new FolderBO();
		Folder folder = fdBO.getFolderByID(folderID);
		
		folder.setName(folderName);
		folder.setDescription(description);
		
		fdBO.UpdateFolder(folder);
				
		CategoryBO categoryBO = new CategoryBO();
		List<Category> listCategory = categoryBO.getAllCategories();
		
		request.setAttribute("folder", folder);
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("mess", "Edit folder successfully!");
		
		request.getRequestDispatcher("editfolder.jsp").forward(request, response);
	}

}
