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
	 * Save an events source into DB
	 * 
	 * @param eventsSource
	 *            the EventsSource object
	 */
	public void addNewSource(EventsSource eventsSource) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(eventsSource);
		tx.commit();
		session.close();
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
	 * Gets URL of all sources from DB
	 * 
	 * @return list of all source urls from DB
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllsourceURLs() {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		List<String> urls = session
				.createQuery("SELECT ES.sourceURL from eventagent.persistence.entities.EventsSource ES").list();
		tr.commit();
		session.close();
		return urls;
	}
	
	public int updateFrequency(String sourceURL, int newFrequency){
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		List<EventsSource> all = getAllEventsSources();
		int returnValue = 0;
		for (EventsSource eventsSource : all) {
			if(eventsSource.getSourceURL().equals(sourceURL)){
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
	 * Deletes an events source from DB by URL
	 * 
	 * @param sourceURL
	 *            URL of the source
	 */
	@SuppressWarnings("unchecked")
	public void deleteEventsSource(String sourceURL) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		List<EventsSource> eventsSources = session.createQuery("from eventagent.persistence.entities.EventsSource")
				.list();
		for (EventsSource eventsSource : eventsSources) {
			if (eventsSource.getSourceURL().equals(sourceURL)) {
				session.delete(eventsSource);
				break;
			}
		}
		tr.commit();
		session.close();
	}

}
