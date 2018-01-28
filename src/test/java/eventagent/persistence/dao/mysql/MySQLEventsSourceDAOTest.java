package eventagent.persistence.dao.mysql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eventagent.persistence.dao.EventsSourceDAO;
import eventagent.persistence.entities.EventsSource;
import eventagent.persistence.entities.SourceType;

public class MySQLEventsSourceDAOTest {

	private EventsSource testEventsSource;
	private EventsSourceDAO dao;

	@Before
	public void before() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/MySQLPersistenceBeans.xml");
		setDao((MySQLEventsSourceDAO) context.getBean("eventsSourceDAO"));
		EventsSource eventSource = new EventsSource();
		eventSource.setSourceURL("testURL");
		eventSource.setSourceType(SourceType.page);
		setTestEventsSource(eventSource);
	}

	@Test
	public void testAddNewEventsSource() {
		getDao().addNewEventsSource(getTestEventsSource());
		assertEquals(getTestEventsSource(), getDao().get(getTestEventsSource()));
		getDao().deleteEventsSource(getTestEventsSource());
		assertNull(getDao().get(getTestEventsSource()));
	}

	@Test
	public void testGetAllEventsSources() {
		int allEventsSourcesCountBefore = getDao().getAllEventsSources().size();
		getDao().addNewEventsSource(getTestEventsSource());
		assertSame(allEventsSourcesCountBefore + 1, getDao().getAllEventsSources().size());
		getDao().deleteEventsSource(getTestEventsSource());
		assertSame(allEventsSourcesCountBefore, getDao().getAllEventsSources().size());

	}

	@Test
	public void testUpdateFrequency() {
		getDao().addNewEventsSource(getTestEventsSource());
		int frequencyBeforeUpdate = getDao().get(getTestEventsSource()).getDownloadFrequencyInHours();
		getDao().updateFrequency(getTestEventsSource().getSourceURL(), frequencyBeforeUpdate + 1);
		assertSame(getDao().get(getTestEventsSource()).getDownloadFrequencyInHours(), frequencyBeforeUpdate + 1);
		getDao().deleteEventsSource(getTestEventsSource());
	}

	@Test
	public void testDeleteEventsSource() {
		getDao().addNewEventsSource(getTestEventsSource());
		assertNotNull(getDao().get(getTestEventsSource()));
		getDao().deleteEventsSource(getTestEventsSource());
		assertNull(getDao().get(getTestEventsSource()));
	}

	public EventsSource getTestEventsSource() {
		return testEventsSource;
	}

	public void setTestEventsSource(EventsSource testEventsSource) {
		this.testEventsSource = testEventsSource;
	}

	public EventsSourceDAO getDao() {
		return dao;
	}

	public void setDao(EventsSourceDAO dao) {
		this.dao = dao;
	}

}
