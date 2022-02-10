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
import model.bean.User;
import model.bo.CategoryBO;
import model.bo.FileBO;
import model.bo.ShareFileBO;
import model.bo.UserBO;

@WebServlet("/share-file")
public class ShareFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		
		if (user == null) {
			request.setAttribute("mess", "Session timeout! Please login again!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		String uri = request.getScheme() + "://" +   // "http" + "://
	             request.getServerName() +       // "myhost"
	             ":" +                           // ":"
	             request.getServerPort() +       // "8080"
	             "/PBL4/file-info"; // PBL4
		
		int fileID = Integer.valueOf(request.getParameter("file-id"));
		
		FileBO fileBO = new FileBO();
		File file = fileBO.getFileByID(fileID);
		CategoryBO categoryBO = new CategoryBO();
		List<Category> listCategory = categoryBO.getAllCategories();
		ShareFileBO sfBO = new ShareFileBO();
		String mode = sfBO.getShareMode(fileID);
		String listShareID = "";
		if (mode.equals("group")) {
			listShareID = String.join(", ", sfBO.getListShareID(fileID));
		}
				
		request.setAttribute("file", file);
		request.setAttribute("mode", mode);
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("listShareID", listShareID);
		request.setAttribute("myuri", uri);
		request.getRequestDispatcher("sharefile.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("acc");
		
		if (user == null) {
			request.setAttribute("mess", "Session timeout! Please login again!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		String mode = request.getParameter("sharemode");
		int fileID = Integer.valueOf(request.getParameter("fileID"));
		ShareFileBO shareBO = new ShareFileBO();
		
		if (mode.equals("public")) shareBO.sharePublic(fileID);
		else if (mode.equals("private")) shareBO.sharePrivate(fileID);
		else {
			String listshare = request.getParameter("listshare");
			
			UserBO usrBO = new UserBO();
			String[] listMSSV = listshare.split(",");
			List<Integer> listUserID = new ArrayList<Integer>();
			for (String id : listMSSV) {
				User usr = usrBO.getUserByMSSV(id.trim());
				if (usr == null) continue;
				listUserID.add(usr.getUserID());
			}
			
			shareBO.shareGroup(fileID, listUserID);
		}
		
		response.sendRedirect("my-files?m="+mode);
	}

}
