package eventagent.persistence.dao;

import java.util.List;

import eventagent.persistence.entities.Admin;

public interface AdminDAO {

	public void addNewAdmin(Admin admin);

	public int delete(Admin admin);

	public Admin get(Admin admin);

	public List<Admin> getAllAdmins();

}
