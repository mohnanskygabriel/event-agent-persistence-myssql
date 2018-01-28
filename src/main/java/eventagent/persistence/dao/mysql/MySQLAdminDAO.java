package eventagent.persistence.dao.mysql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import eventagent.persistence.dao.AdminDAO;
import eventagent.persistence.entities.Admin;

public class MySQLAdminDAO implements AdminDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Adds new admin
	 * 
	 * @param admin
	 *            the new admin
	 */
	public void addNewAdmin(Admin admin) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(admin);
		tx.commit();
		session.close();
	}

	/**
	 * Deletes an admin
	 * 
	 * @param admin
	 *            the admin you want to delete
	 * @return 1 if the admin was found, else returns 0
	 */
	public int delete(Admin admin) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int returnValue = 0;
		session.remove(admin);
		tx.commit();
		session.close();
		return returnValue;
	}

	/**
	 * @param admin
	 *            searched administrator
	 * @return null if admin is not found in DB
	 */
	@SuppressWarnings("unchecked")
	public Admin get(Admin admin) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Admin> admins = session.createQuery("from eventagent.persistence.entities.Admin").list();
		Admin foundAdmin = null;
		for (Admin adminFromDB : admins) {
			if (adminFromDB.equals(admin)) {
				foundAdmin = adminFromDB;
				break;
			}
		}

		tx.commit();
		session.close();
		return foundAdmin;
	}

	/**
	 * @return List of Admins from DB
	 */
	@SuppressWarnings("unchecked")
	public List<Admin> getAllAdmins() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Admin> admins = session.createQuery("from eventagent.persistence.entities.Admin").list();
		tx.commit();
		session.close();
		return admins;
	}

}
