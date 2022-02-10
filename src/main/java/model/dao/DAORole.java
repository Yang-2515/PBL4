package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bean.Role;

public class DAORole {
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Role> getAllRole() {
        List<Role> list = new ArrayList<>();
        String query = "select * from role";
        try {
            conn = DBContext.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Role role = new Role(rs.getString(2));
                role.setRoleID(rs.getInt(1));
                list.add(role);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public Role getRoleByRoleID(int id) {
        String query = "select * from role where RoleID = ?";
        Role role = null;
        try {
            conn = DBContext.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                role = new Role(rs.getString(2));
                role.setRoleID(id);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return role;
    }
    
    public void AddRole(Role u) {
    	String query = "insert into role (Name) values(?)";
        try {
            conn = DBContext.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, u.getName());
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void UpdateRole(Role u) {
    	String query = "update role set Name = ? where RoleID =?";
        try {
            conn = DBContext.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, u.getName());
            ps.setInt(2, u.getRoleID());
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void DelRole(int id) {
    	String query = "delete from role where RoleID = ?";
        try {
            conn = DBContext.getConnection();//mo ket noi voi sql
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
    	DAORole dbContext =new DAORole();
    	//Role role = new Role("ad");
		dbContext.DelRole(3);
		//dbContext.DelUser(3);
		System.out.println(dbContext.getAllRole());
    	//User user = new User("anh", "anh@gmail", "B308", "102190345", 2);
    	//user.setUserID(4);
    	//dbContext.UpdateUser(user);
		//System.out.println(dbContext.getUserByUserID(4));
	}

}
