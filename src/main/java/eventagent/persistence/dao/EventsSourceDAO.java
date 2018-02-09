package eventagent.persistence.dao;

import java.util.List;

import eventagent.persistence.entities.EventsSource;

public interface EventsSourceDAO {

	public void addNewEventsSource(EventsSource eventsSource);

	public EventsSource get(EventsSource eventsSource);

	public List<EventsSource> getAllEventsSources();

	public int updateFrequency(String sourceURL, int newFrequency);
	
	public int update(EventsSource eventsSource);

	public int deleteEventsSource(EventsSource eventsSource);
}
