package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bean.ShareFolder;

public class DAOShareFolder {
	
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<ShareFolder> getShareFolderByUserID(int userid) {
        String query = "select * from share_folder where UserID = ?";
        List<ShareFolder> list = new ArrayList<ShareFolder>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
            	ShareFolder shareFolder = new ShareFolder(rs.getInt(2), rs.getInt(3));
            	shareFolder.setShareFolderID(rs.getInt(1));
                list.add(shareFolder);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<String> getListShareID(int folderID){
    	List<String> list = new ArrayList<>();
        String query = "select distinct Mssv from user inner join share_folder on user.UserID = share_folder.UserID where share_folder.FolderID = ?";
        try {
        	Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, folderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mssv = rs.getString(1);
                list.add(mssv);
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        return list;
    }
    
    public List<ShareFolder> getAllShareFolder() {
        String query = "select * from share_folder";
        List<ShareFolder> list = new ArrayList<ShareFolder>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	ShareFolder shareFolder = new ShareFolder(rs.getInt(2), rs.getInt(3));
            	shareFolder.setShareFolderID(rs.getInt(1));
                list.add(shareFolder);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public void AddShareFolder(int folderid, int userid) {
    	String query = "insert into share_folder (FolderID, UserID) values(?,?)";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, folderid);
            ps.setInt(2, userid);
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void UpdateShareFolder(ShareFolder shareFolder) {
    	String query = "update share_folder set FolderID = ?, UserID=? where FolderID =?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, shareFolder.getFolderID());
            ps.setInt(2, shareFolder.getUserID());
            ps.setInt(3, shareFolder.getShareFolderID());
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void delShareFolder(int folderid) {
    	String query = "delete from share_folder where FolderID = ?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, folderid);
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }

    public boolean isValidUser(int usrID, int folderid) {
    	String query = "select * from share_folder where UserID = ? and FolderID = ?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, usrID);
            ps.setInt(2, folderid);
            rs = ps.executeQuery();
            if (rs.next()) {
            	return true;
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e);
        }
        return false;
    }
    
    public boolean isSharePublic(int folderid) {
    	String query = "select * from share_folder where FolderID = ?";
    	boolean check = false;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, folderid);
            rs = ps.executeQuery();
            if (rs.next()) {
            	if (rs.getInt(3) == 0) check = true;
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e);
        }
        return check;
    }
    
    public boolean isShareGroup(int folderid) {
    	String query = "select * from share_folder where FolderID = ?";
    	boolean check = false;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, folderid);
            rs = ps.executeQuery();
            if (rs.next()) {
            	if (rs.getInt(3) != 0) check = true;
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e);
        }
        return check;
    }
    
    public boolean isSharePrivate(int folderid) {
    	String query = "select * from share_folder where FolderID = ?";
    	boolean check = false;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, folderid);
            rs = ps.executeQuery();
            if (!rs.next()) {
            	check = true;
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e);
        }
        return check;
    }
}
