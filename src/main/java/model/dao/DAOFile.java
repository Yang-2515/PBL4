package model.dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.bean.File;

public class DAOFile {
	
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<File> getAllFile() {
        List<File> list = new ArrayList<>();
        String query = "select * from file where status <> 'deleted'";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	File file = new File(rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(10), rs.getInt(9));
                file.setFileID(rs.getInt(1));
                list.add(file);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<File> getListFilesByName(String name, int folderID) {
        String query = "select * from file where FolderID = ? AND Name LIKE '%?%' AND Status <> 'deleted'";
        List<File> list = new ArrayList<>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, folderID);
            ps.setString(2, name);
            rs = ps.executeQuery();
            while (rs.next()) {
            	File file = new File(rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(10), rs.getInt(9));
                file.setFileID(rs.getInt(1));
                list.add(file);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<File> getListFilesByName(String name) {
        String query = "select * from file where Name LIKE '%"+name+"%' AND Status <> 'deleted'";
        List<File> list = new ArrayList<>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	File file = new File(rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(10), rs.getInt(9));
                file.setFileID(rs.getInt(1));
                list.add(file);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<File> getFilesByCategory(int cateID) {
        String query = "SELECT * FROM `file` inner JOIN file_cate ON file.FileID = file_cate.FileID WHERE file_cate.CateID = ? AND file.Status <> 'deleted'";
        List<File> list = new ArrayList<>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, cateID);
            rs = ps.executeQuery();
            while (rs.next()) {
            	File file = new File(rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(10), rs.getInt(9));
                file.setFileID(rs.getInt(1));
                list.add(file);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<File> sortFile(int folderID, String sort) {
        String query = "SELECT * FROM file WHERE Status <> 'deleted' AND FolderID = ? ORDER BY CreateAt ?;";
        List<File> list = new ArrayList<>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, folderID);
            ps.setString(2, sort);
            rs = ps.executeQuery();
            while (rs.next()) {
            	File file = new File(rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(10), rs.getInt(9));
                file.setFileID(rs.getInt(1));
                list.add(file);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public File getFileByName(String name, int folderID) {
        String query = "select * from file where FolderID = ? AND Name = ? AND Status <> 'deleted'";
        File file = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, folderID);
            ps.setString(2, name);
            rs = ps.executeQuery();
            if (rs.next()) {
            	file = new File(rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(10), rs.getInt(9));
                file.setFileID(rs.getInt(1));
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return file;
    }
    
    public List<File> getFilesByUserID(int UserID) {
        String query = "select * from file where UserID = ? and status <> 'deleted'";
        List<File> list = new ArrayList<File>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, UserID);
            rs = ps.executeQuery();
            while (rs.next()) {
            	File file = new File(rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(10), rs.getInt(9));
                file.setFileID(rs.getInt(1));
                System.out.println();
                list.add(file);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(UserID + e.getMessage());
        }
        return list;
    }
    
    public void addFile(File file) {
    	String query = "insert into file (Name, Status, Description, Size, UserID, Content, FolderID) values(?,?,?,?,?,?,?)";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, file.getName());
            ps.setString(2, file.getStatus());
            ps.setString(3, file.getDescription());
            ps.setString(4, file.getSize());
            ps.setInt(5, file.getUserID());
            ps.setBinaryStream(6, file.getContent());
            ps.setInt(7, file.getFolderID());
            ps.execute();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void AddContentFile(FileInputStream content, String name) {
    	String query = "update file set Content = ? where FileID = 3";
    	System.out.println(query);
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setBinaryStream(1, content);
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public File download(int id) {
    	String query = "select * from file where FileID = ? and status <> 'deleted'";
    	File fileOutput = null;
    	Blob data = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet kq = ps.executeQuery();
            
            if (kq.next()) {
            	data =   kq.getBlob(11);
            	InputStream content = data.getBinaryStream();
            	fileOutput = new File(kq.getString(2), kq.getString(3), content, kq.getString(7), kq.getString(8), kq.getInt(9), kq.getInt(12));
            	content.close();
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        return fileOutput;
    }
    
    public File getFileByID(int id) {
    	String query = "select * from file where FileID = ? and status <> 'deleted'";
    	File file = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
            	file = new File(rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(10), rs.getInt(9));
                file.setFileID(rs.getInt(1));
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
        return file;
    }
    
    public void deleteFiles(StringBuilder idFile) {
    	SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss"); 
		Date dNow = new Date();
    	String query = "update file set Status = 'deleted' , DeleteAt = ? where FileID in ( "+ idFile.toString() + " ) ";
    	try {
    		conn = DBContext.getConnection();
    		ps = conn.prepareStatement(query);
    		ps.setString(1, String.valueOf(ft.format(dNow)));
            ps.executeUpdate();
            conn.close();
            ps.close();
		} catch (Exception e) {
			System.out.println(e);
		}
    }
    
    public void updateFile(String field, String value, int idFile) {
    	SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss"); 
		Date dNow = new Date();
    	String query = "update file set Status = 'updated',"+ field + " = '" + value + "' , ModifyAt = ? where FileID =  ? ";
    	try {
    		conn = DBContext.getConnection();
    		ps = conn.prepareStatement(query);
    		ps.setString(1, String.valueOf(ft.format(dNow)));
    		ps.setInt(2, idFile);
            ps.executeUpdate();
            conn.close();
            ps.close();
		} catch (Exception e) {
			System.out.println(e);
		}
    }
    
    public List<File> getFileByFolderID(int fid) {
    	String query = "select * from file where FolderID = ? and status <> 'deleted'";
    	List<File> list = new ArrayList<File>();
    	try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, fid);
            rs = ps.executeQuery();
            while (rs.next()) {
            	
            	File file = new File(rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(10), rs.getInt(9));
                file.setFileID(rs.getInt(1));
                list.add(file);
                
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }
}
