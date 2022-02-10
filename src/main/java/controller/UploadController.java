package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bean.Category;
import model.bean.File;
import model.bean.Login;
import model.bo.CategoryBO;
import model.bo.FileBO;

@WebServlet("/upload")
@MultipartConfig
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part part = request.getPart("file");
		
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		
		int cateID = Integer.valueOf(request.getParameter("category"));
		
		String description = request.getParameter("description");
		int idUser = user.getUserID();
		int folderID = Integer.valueOf(request.getParameter("folder"));
		String size =  String.valueOf(part.getSize());
		String name = part.getSubmittedFileName();
		InputStream content = part.getInputStream();
		
		File fileInput = new File(name+"ghz", "created", content, description, size, idUser, folderID);
		
		FileBO fileBO = new FileBO();
		fileBO.addFile(fileInput);
		
		CategoryBO cateBO = new CategoryBO();
		File temp = fileBO.getFileByName(name+"ghz", folderID);
				
		cateBO.addFileCate(temp.getFileID(), cateID);
		
		fileBO.updateFile("Name", name, temp.getFileID(), folderID);
		
		List<File> listFile = fileBO.getFileByFolderID(Integer.valueOf(folderID));
		
		List<Category> listCategory = cateBO.getAllCategories();
		
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("folderID", folderID);
		request.setAttribute("listFile", listFile);
		request.getRequestDispatcher("user-file.jsp").forward(request, response);
	}
}
