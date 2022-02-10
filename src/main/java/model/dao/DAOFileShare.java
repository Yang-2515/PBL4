package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOFileShare {
	
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public boolean isValidUser(int usrID, int fileID) {
    	String query = "select * from share_file where UserID = ? and FileID = ?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, usrID);
            ps.setInt(2, fileID);
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
    
    public boolean isSharePublic(int fileID) {
    	String query = "select * from share_file where FileID = ?";
    	boolean check = false;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, fileID);
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
    
    public boolean isSharePrivate(int fileID) {
    	String query = "select * from share_file where FileID = ?";
    	boolean check = false;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, fileID);
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
    
    public boolean isShareGroup(int fileID) {
    	String query = "select * from share_file where FileID = ?";
    	boolean check = false;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, fileID);
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
    
    
    public List<String> getListShareID(int fileID){
    	List<String> list = new ArrayList<>();
        String query = "select distinct Mssv from user inner join share_file on user.UserID = share_file.UserID where share_file.FileID = ?";
        try {
        	Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, fileID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mssv = rs.getString(1);
                list.add(mssv);
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
        	
        }
        return list;
    }
    
    public void insertOne(int fileID, int userID) {
    	String query = "insert into share_file (ShareFileID, FileID, UserID) values(NULL,?,?)";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, fileID);
            ps.setInt(2, userID);
            ps.execute();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void deleteFileID(int fileID) {
    	String query = "delete from share_file where FileID = ? ";
    	try {
    		conn = DBContext.getConnection();
    		ps = conn.prepareStatement(query);
    		ps.setInt(1, fileID);
            ps.executeUpdate();
            conn.close();
            ps.close();
		} catch (Exception e) {
			System.out.println(e);
		}
    }
}