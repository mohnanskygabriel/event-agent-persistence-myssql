package eventagent.persistence.dao.mysql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import eventagent.persistence.dao.EventsSourceDAO;
import eventagent.persistence.entities.EventsSource;

public class MySQLEventsSourceDAO implements EventsSourceDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Save an events source
	 * 
	 * @param eventsSource
	 *            the EventsSource object
	 */
	public void addNewEventsSource(EventsSource eventsSource) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(eventsSource);
		tx.commit();
		session.close();
	}

	/**
	 * The method search for an EventsSource
	 * 
	 * @param eventsSource
	 *            the EventsSource you searching for
	 * @return null if the EventsSource was not found, otherwise the method
	 *         returns the founded event
	 */
	@SuppressWarnings("unchecked")
	public EventsSource get(EventsSource eventsSource) {
		EventsSource foundEventsSource = null;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<EventsSource> eventsSources = session.createQuery("from eventagent.persistence.entities.EventsSource")
				.list();
		for (EventsSource eventsSourceFromDb : eventsSources) {
			if (eventsSourceFromDb.equals(eventsSource)) {
				foundEventsSource = eventsSourceFromDb;
				break;
			}
		}
		tx.commit();
		session.close();
		return foundEventsSource;
	}

	/**
	 * Gets all events source from DB
	 * 
	 * @return list of all EventsSource from DB
	 */
	@SuppressWarnings("unchecked")
	public List<EventsSource> getAllEventsSources() {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		List<EventsSource> eventsSources = session.createQuery("from eventagent.persistence.entities.EventsSource")
				.list();
		tr.commit();
		session.close();
		return eventsSources;

	}

	/**
	 * Updates an EventsSource defined by URL with new frequency
	 * 
	 * @param sourceURL
	 *            URL of the source
	 * @param newFrequency
	 *            the new frequency
	 * 
	 * @return 0 if EventsSource with sourceURL is not found otherwise returns 1
	 */
	public int updateFrequency(String sourceURL, int newFrequency) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		List<EventsSource> all = getAllEventsSources();
		int returnValue = 0;
		for (EventsSource eventsSource : all) {
			if (eventsSource.getSourceURL().equals(sourceURL)) {
				eventsSource.setDownloadFrequencyInHours(newFrequency);
				session.update(eventsSource);
				tr.commit();
				returnValue = 1;
			}
		}
		session.close();
		return returnValue;
	}

	/**
	 * Deletes an EventsSource by URL
	 * 
	 * @param sourceURL
	 *            URL of the source you want to delete
	 * 
	 * @return 0 if eventsSource was not found, otherwise returns 1
	 */
	@SuppressWarnings("unchecked")
	public int deleteEventsSource(EventsSource eventsSource) {
		int returnValue = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		List<EventsSource> eventsSources = session.createQuery("from eventagent.persistence.entities.EventsSource")
				.list();
		for (EventsSource eventsSourceFromDB : eventsSources) {
			if (eventsSourceFromDB.equals(eventsSource)) {
				session.delete(eventsSourceFromDB);
				returnValue = 1;
				break;
			}
		}
		tr.commit();
		session.close();
		return returnValue;
	}
}
