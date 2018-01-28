package eventagent.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations, Hibernate provides JPA implementation
 */
@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@Column(name = "id", columnDefinition = "int(6) NOT NULL AUTO_INCREMENT", insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "admin", columnDefinition = "nvarchar(50) NOT NULL", unique = true)
	private String adminFbId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdminFbId() {
		return adminFbId;
	}

	public void setAdminFbId(String adminFbId) {
		this.adminFbId = adminFbId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adminFbId == null) ? 0 : adminFbId.hashCode());
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
		Admin other = (Admin) obj;
		if (adminFbId == null) {
			if (other.adminFbId != null)
				return false;
		} else if (!adminFbId.equals(other.adminFbId))
			return false;
		return true;
	}

}
