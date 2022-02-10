package model.bo;

import java.util.List;

import model.bean.Role;
import model.dao.DAORole;

public class RoleBO {
	DAORole roleDAO = new DAORole();
	
	public List<Role> getAllRole() {
		return roleDAO.getAllRole();
	}
	
	public Role getRoleByRoleID(int id) {
		return roleDAO.getRoleByRoleID(id);
	}
	
	public void AddRole(Role u) {
		roleDAO.AddRole(u);
	}
	
	public void UpdateRole(Role u) {
		roleDAO.UpdateRole(u);
	}
	
	public void DelRole(int id) {
		roleDAO.DelRole(id);
	}
}
