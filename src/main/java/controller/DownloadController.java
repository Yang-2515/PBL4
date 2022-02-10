package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.File;
import model.bo.FileBO;

/**
 * Servlet implementation class UploadController
 */
@WebServlet("/download")
@MultipartConfig
public class DownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       try {
	    	   String fileID = request.getParameter("file-id");
	    	   
	    	   FileBO fileBO = new FileBO();
	    	   
	    	   File file =  fileBO.download(Integer.valueOf(fileID));

	           response.setHeader("Content-Type", "multipart/form-data");

	           response.setHeader("Content-Length", file.getSize());

	           response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

	           byte[] bytes = new byte[1024];
	           int bytesRead;

	           while ((bytesRead = file.getContent().read(bytes)) != -1) {
	               response.getOutputStream().write(bytes, 0, bytesRead);
	           }

	       } catch (Exception e) {
	           throw new ServletException(e);
	       } 
	   }

}