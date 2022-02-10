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
import model.bean.Folder;
import model.bean.Login;
import model.bean.User;
import model.bo.CategoryBO;
import model.bo.FolderBO;
import model.bo.ShareFolderBO;
import model.bo.UserBO;


@WebServlet("/share-folder")
public class ShareFolderController extends HttpServlet {
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
	             "/PBL4/folder-info"; // PBL4
		
		int folderID = Integer.valueOf(request.getParameter("folder-id"));
		FolderBO folderBO = new FolderBO();
		Folder folder = folderBO.getFolderByID(folderID);
		CategoryBO categoryBO = new CategoryBO();
		List<Category> listCategory = categoryBO.getAllCategories();
		ShareFolderBO sfBO = new ShareFolderBO();
		String mode = sfBO.getShareMode(folderID);
		String listShareID = "";
		if (mode.equals("group")) {
			listShareID = String.join(", ", sfBO.getListShareID(folderID));
		}
		
		request.setAttribute("folder", folder);
		request.setAttribute("mode", mode);
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("listShareID", listShareID);
		request.setAttribute("myuri", uri);
		request.getRequestDispatcher("sharefolder.jsp").forward(request, response);
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
		int folderID = Integer.valueOf(request.getParameter("folder-id"));
		ShareFolderBO shareBO = new ShareFolderBO();
		
		
		if (mode.equals("public")) shareBO.sharePublic(folderID);
		else if (mode.equals("private")) shareBO.setPrivate(folderID);
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
			
			shareBO.shareGroup(folderID, listUserID);
		}
		
		response.sendRedirect("my-files?m="+mode);
	}

}
