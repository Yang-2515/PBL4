package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bean.Folder;

public class DAOFolder {
	
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Folder> getFolderByUserID(int userid) {
        String query = "select * from folder where UserID = ? and status <> 'Deleted'";
        List<Folder> list = new ArrayList<Folder>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Folder fd = new Folder(rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
                fd.setFolderID(rs.getInt(1));
                list.add(fd);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public Folder getFolderByID(int id) {
        String query = "select * from folder where FolderID = ? and status <> 'Deleted'";
        Folder fd = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
            	fd = new Folder(rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
                fd.setFolderID(rs.getInt(1));
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        return fd;
    }
    
    public boolean getFoldersByName(String name, int usrID) {
        String query = "select * from folder where Name = ? and UserID = ? and status <> 'Deleted'";
        List<Folder> list = new ArrayList<Folder>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, usrID);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Folder fd = new Folder(rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
                fd.setFolderID(rs.getInt(1));
                list.add(fd);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        if(list.size()>0) return true;
        else return false;
    }
    
    public void AddFolder(Folder f) {
    	if(getFoldersByName(f.getName(), f.getUserID()) == false) {
    		String query = "insert into folder (Name, UserID, Description, Status) values(?,?,?,?)";
            try {
                conn = DBContext.getConnection();
                ps = conn.prepareStatement(query);
                ps.setString(1, f.getName());
                ps.setInt(2, f.getUserID());
                ps.setString(3, f.getDescription());
                ps.setString(4, f.getStatus());
                ps.executeUpdate();
                conn.close();
                ps.close();
            } catch (Exception e) {
            	System.out.println(e.toString());
            }
    	}
    }
    
    public void UpdateFolder(Folder f) {
    	String query = "update folder set Name = ?, UserID=?, Description=?, Status=? where FolderID =?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, f.getName());
            ps.setInt(2, f.getUserID());
            ps.setString(3, f.getDescription());
            ps.setString(4, f.getStatus());
            ps.setInt(5, f.getFolderID());
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void DelFolder(int fid) {
    	String query = "update folder set Status =? where FolderID =?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "Deleted");
            ps.setInt(2, fid);
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
