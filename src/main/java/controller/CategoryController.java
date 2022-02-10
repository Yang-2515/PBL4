package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.File;
import model.bo.CategoryBO;
import model.bo.FileBO;
import model.bo.ShareFileBO;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	int id = Integer.valueOf(request.getParameter("id"));
    	FileBO fileBO = new FileBO();
		ShareFileBO sfBO = new ShareFileBO();
		CategoryBO categoryBO = new CategoryBO();
		List<File> listFile = fileBO.getFilesByCategory(id);
		List<File> listPublic = new ArrayList<File>();
        List<Category> listCategory = categoryBO.getAllCategories();
        
        for (int i=listFile.size()-1; i >= 0; i--) {
        	if (sfBO.isSharePublic(listFile.get(i).getFileID())) {
        		listPublic.add(listFile.get(i));
        	}
        }
        
        request.setAttribute("listCategory", listCategory);
		request.setAttribute("listFile", listPublic);
		request.setAttribute("folderID", 0);
		request.getRequestDispatcher("categorypage.jsp").forward(request, response);
    }
}
