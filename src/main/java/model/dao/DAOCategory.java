package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bean.Category;

public class DAOCategory {
	
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String query = "select * from category";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Category category = new Category(rs.getString(2));
                category.setCateID(rs.getInt(1));
                list.add(category);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Category> getCategoriesByCateID(String ids) {
    	List<Category> list = new ArrayList<Category>();

        String query = "select * from category where CateID in ( "+ids+" )";
        Category category = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getString(2));
                category.setCateID(rs.getInt(1));
                list.add(category);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e);
        }
        return list;
    }
    
    public Category getCategoryByName(String name) {
        String query = "select * from category where Name = ?";
        Category category = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
            	if (name.toLowerCase().equals(rs.getString(2).toLowerCase())) {
            		category = new Category(rs.getString(2));
                    category.setCateID(rs.getInt(1));
            	}
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e);
        }
        return category;
    }
    
    public void AddCategory(Category u) {
    	String query = "insert into category (Name) values (?)";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, u.getName());
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void UpdateCategory(Category u) {
    	String query = "update category set Name = ? where CateID =?";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, u.getName());
            ps.setInt(2, u.getCateID());
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void DelCategory(int id) {
    	String query = "delete from category where CateID = ?";
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
}