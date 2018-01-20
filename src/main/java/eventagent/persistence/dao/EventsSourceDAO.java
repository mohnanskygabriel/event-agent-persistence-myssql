package eventagent.persistence.dao;

import java.util.List;

import eventagent.persistence.entities.EventsSource;

public interface EventsSourceDAO {

	public void addNewSource(EventsSource eventsSource);
	
	public List<String> getAllsourceURLs();

	public List<EventsSource> getAllEventsSources();
	
	public void deleteEventsSource(String sourceURL);
}
