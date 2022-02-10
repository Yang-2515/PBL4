package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bean.FileCate;

public class DAOFileCate {
	
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<FileCate> getAllFileCate() {
        List<FileCate> list = new ArrayList<>();
        String query = "select * from file_cate";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	FileCate fc = new FileCate(rs.getInt(2),rs.getInt(3));
            	fc.setFileCateID(rs.getInt(1));
                list.add(fc);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public FileCate getFileCateByFileCateID(int id) {
        String query = "select * from file_cate where FileCateID = ?";
        FileCate fc = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                fc = new FileCate(rs.getInt(2),rs.getInt(3));
                fc.setFileCateID(id);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return fc;
    }
    
    public List<FileCate> getListFileCateByFileID(int id) {
        String query = "select * from file_cate where FileID = ?";
        List<FileCate> list = new ArrayList<FileCate>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                FileCate fc = new FileCate(rs.getInt(2),rs.getInt(3));
                fc.setFileCateID(id);
                list.add(fc);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public void AddFileCate(FileCate u) {
    	String query = "insert into file_cate (CateID, FileID) values(?,?)";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, u.getCateID());
            ps.setInt(2, u.getFileID());
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void UpdateFileCate(FileCate u) {
    	String query = "update file_cate set CateID = ?, FileID=? where FileCateID =?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, u.getCateID());
            ps.setInt(2, u.getFileID());
            ps.setInt(2, u.getFileCateID());
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void DelFileCate(int id) {
    	String query = "delete from file_cate where FileID = ?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public static void main(String[] args) throws Exception {
    	DAOFileCate dbContext =new DAOFileCate();
    	FileCate fc = new FileCate(1,6);
		//dbContext.DelUser(3);
    	dbContext.AddFileCate(fc);
		System.out.println(dbContext.getAllFileCate());
    	//User user = new User("anhhh", "anh@gmail", "B308", "102190345", 2);
    	//user.setUserID(4);
    	//dbContext.UpdateUser(user);
		//System.out.println(dbContext.getUserByUserID(4));
	}

}
