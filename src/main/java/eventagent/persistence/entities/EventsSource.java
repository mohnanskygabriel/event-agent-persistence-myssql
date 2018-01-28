package eventagent.persistence.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity bean with JPA annotations, Hibernate provides JPA implementation
 */
@Entity
@Table(name = "events_source")
public class EventsSource {

	@Id
	@Column(name = "id", columnDefinition = "int(6) NOT NULL AUTO_INCREMENT", insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "url", columnDefinition = "varchar(100) NOT NULL")
	private String sourceURL;

	@Column(name = "event_default_type", columnDefinition = "enum('movie','concert','unspecified') DEFAULT 'unspecified'")
	@Enumerated(EnumType.STRING)
	private EventDefaultType eventDefaultType = EventDefaultType.unspecified;

	@Column(name = "last_check_result", columnDefinition = "enum('not_checked','new_event_found','none_new_event_found','unavailable') DEFAULT 'not_checked'")
	@Enumerated(EnumType.STRING)
	private LastCheckResult lastCheckResult = LastCheckResult.not_checked;

	@Column(name = "last_check_time", columnDefinition = "datetime NOT NULL DEFAULT '1000-01-01 00:00:00'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastCheckTime = new Date();

	@Column(name = "next_check_time", columnDefinition = "datetime NOT NULL DEFAULT '1000-01-01 00:00:00'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date nextCheckTime = new Date();

	@Column(name = "download_frequency_in_hours", columnDefinition = "int(5)  NOT NULL DEFAULT '0'")
	private Integer downloadFrequencyInHours = 0;

	@Column(name = "added", columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP")
	private Date added;

	@Column(name = "source_type", columnDefinition = "enum('page','group','user') NOT NULL")
	@Enumerated(EnumType.STRING)
	private SourceType sourceType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSourceURL() {
		return sourceURL;
	}

	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}

	public EventDefaultType getEventDefaultType() {
		return eventDefaultType;
	}

	public void setEventDefaultType(EventDefaultType eventDefaultType) {
		this.eventDefaultType = eventDefaultType;
	}

	public LastCheckResult getLastCheckResult() {
		return lastCheckResult;
	}

	public void setLastCheckResult(LastCheckResult lastCheckResult) {
		this.lastCheckResult = lastCheckResult;
	}

	public Date getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(Date lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

	public Date getNextCheckTime() {
		return nextCheckTime;
	}

	public void setNextCheckTime(Date nextCheckTime) {
		this.nextCheckTime = nextCheckTime;
	}

	public int getDownloadFrequencyInHours() {
		return downloadFrequencyInHours;
	}

	public void setDownloadFrequencyInHours(int downloadFrequencyInHours) {
		this.downloadFrequencyInHours = downloadFrequencyInHours;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public SourceType getSourceType() {
		return sourceType;
	}

	public void setSourceType(SourceType sourceType) {
		this.sourceType = sourceType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sourceURL == null) ? 0 : sourceURL.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventsSource other = (EventsSource) obj;
		if (sourceURL == null) {
			if (other.sourceURL != null)
				return false;
		} else if (!sourceURL.equals(other.sourceURL))
			return false;
		return true;
	}

}
