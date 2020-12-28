package dz.cerist.mesrs.entite;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the validation_visite database table.
 * 
 */
@Entity
@Table(name="validation_visite")
@NamedQuery(name="ValidationVisite.findAll", query="SELECT v FROM ValidationVisite v")
public class ValidationVisite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="VALIDATION_DATE")
	private Date validationDate;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="VALIDATED_BY")
	private User user;

	//bi-directional one-to-one association to Visite
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH }, fetch=FetchType.EAGER)
	@JoinColumn(name="VISITE_ID")
	private Visite visite;

	public ValidationVisite() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getValidationDate() {
		return this.validationDate;
	}

	public void setValidationDate(Date validationDate) {
		this.validationDate = validationDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Visite getVisite() {
		return this.visite;
	}

	public void setVisite(Visite visite) {
		this.visite = visite;
	}

}