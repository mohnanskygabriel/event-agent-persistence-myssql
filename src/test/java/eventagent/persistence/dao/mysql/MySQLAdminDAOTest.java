package eventagent.persistence.dao.mysql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eventagent.persistence.entities.Admin;

public class MySQLAdminDAOTest {

	private Admin testAdmin;
	private MySQLAdminDAO dao;

	@Before
	public void before() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/MySQLPersistenceBeans.xml");
		setDao((MySQLAdminDAO) context.getBean("adminDAO"));
		Admin admin = new Admin();
		admin.setAdminFbId("testAdmin");
		setTestAdmin(admin);
	}

	@Test
	public void testAddNewAdmin() {
		int adminCountBefore = getDao().getAllAdmins().size();
		getDao().addNewAdmin(getTestAdmin());
		assertSame(adminCountBefore + 1, getDao().getAllAdmins().size());
		getDao().delete(getTestAdmin());
		assertSame(adminCountBefore, getDao().getAllAdmins().size());
	}

	@Test
	public void testDelete() {
		getDao().addNewAdmin(getTestAdmin());
		assertNotNull(getDao().get(getTestAdmin()));
		getDao().delete(getTestAdmin());
		assertNull(getDao().get(getTestAdmin()));
	}

	@Test
	public void testGet() {
		getDao().addNewAdmin(getTestAdmin());
		Admin adminFromDb = getDao().get(getTestAdmin());
		assertEquals(adminFromDb, getTestAdmin());
		getDao().delete(getTestAdmin());
	}

	@Test
	public void testGetAllAdmins() {
		int adminCountBefore = getDao().getAllAdmins().size();
		getDao().addNewAdmin(getTestAdmin());
		assertSame(adminCountBefore + 1, getDao().getAllAdmins().size());
		getDao().delete(getTestAdmin());
		assertSame(adminCountBefore, getDao().getAllAdmins().size());
	}

	public Admin getTestAdmin() {
		return this.testAdmin;
	}

	public void setTestAdmin(Admin admin) {
		this.testAdmin = admin;
	}

	public MySQLAdminDAO getDao() {
		return dao;
	}

	public void setDao(MySQLAdminDAO dao) {
		this.dao = dao;
	}
}
