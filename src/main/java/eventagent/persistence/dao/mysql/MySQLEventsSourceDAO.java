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
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.persist(eventsSource);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}

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
		Session session = null;
		try {
			session = sessionFactory.openSession();
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return foundEventsSource;
	}

	/**
	 * Gets all events source from DB
	 * 
	 * @return list of all EventsSource from DB
	 */
	@SuppressWarnings("unchecked")
	public List<EventsSource> getAllEventsSources() {
		Session session = null;
		List<EventsSource> eventsSources = null;
		try {
			session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			eventsSources = session.createQuery("from eventagent.persistence.entities.EventsSource").list();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}

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
		Session session = null;
		int returnValue = 0;
		try {
			session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			List<EventsSource> all = getAllEventsSources();
			for (EventsSource eventsSource : all) {
				if (eventsSource.getSourceURL().equals(sourceURL)) {
					eventsSource.setDownloadFrequencyInHours(newFrequency);
					session.update(eventsSource);
					tr.commit();
					returnValue = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return returnValue;

	}

	/**
	 * Updates an EventsSource, the methods checks the @param eventsSource url
	 * an set all attributes in the DB as the attributes of @param eventsSource
	 * 
	 * @param eventsSource
	 *            the eventSource you want to update with modified attributes
	 * 
	 * @return 0 if EventsSource is not found otherwise returns 1
	 */
	@Override
	public int update(EventsSource eventsSource) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			if (eventsSource != null && get(eventsSource) != null) {
				session.update(eventsSource);
				tr.commit();
				session.close();
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return 0;
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
		Session session = null;
		try {
			session = sessionFactory.openSession();
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return returnValue;
	}
}
