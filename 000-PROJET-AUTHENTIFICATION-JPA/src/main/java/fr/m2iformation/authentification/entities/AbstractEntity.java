package fr.m2iformation.authentification.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Version
	private int version;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date datePersistence;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getDatePersistence() {
		return datePersistence;
	}

	public void setDatePersistence(Date datePersistence) {
		this.datePersistence = datePersistence;
	}

	@PrePersist
	protected void initDatePersistence() {
		datePersistence = new Date();
	}

}
