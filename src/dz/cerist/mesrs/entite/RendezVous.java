package dz.cerist.mesrs.entite;

import java.io.Serializable;

import javax.persistence.*;



import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the rendez_vous database table.
 * 
 */
@Entity
@Table(name="rendez_vous")
@NamedQueries({
@NamedQuery(name="RendezVous.findAll", query="SELECT r FROM RendezVous r"),
@NamedQuery(name="RendezVous.findByUser", query="select r from RendezVous r where r.user = :user and r.etat<> 'Terminé' "),
@NamedQuery(name="RendezVous.findTerminatedRDVByUser", query="select r from RendezVous r where r.user = :user and r.etat= 'Terminé' "),
@NamedQuery(name="RendezVous.findTodayRDV", query="select r from RendezVous r where r.dateRdv = current_date and r.visibilite=true and r.etat= 'En Attente' ")
})
public class RendezVous implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	
	@Column(name="DATE_RDV")
	@Temporal(TemporalType.DATE )
	
	private Date dateRdv;

	@Column(name="ETAT")
	private String etat;

	@Column(name="HEURE_RDV")
	private Time heureRdv;

	@Column(name="NOM_VISITEUR")
	private String nomVisiteur;

	@Column(name="PRENOM_VISITEUR")
	private String prenomVisiteur;

	@Column(name="VISIBILITE")
	private boolean visibilite;
	
	@Column(name="HAS_VISIT")
	private boolean hasVisit;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CREATED_BY")
	private User user;

	//bi-directional one-to-one association to Visite
	@OneToOne(mappedBy="rendezVous", fetch=FetchType.LAZY)
	private Visite visite;

	public RendezVous() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateRdv() {
		return this.dateRdv;
	}

	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Time getHeureRdv() {
		return this.heureRdv;
	}

	public void setHeureRdv(Time heureRdv) {
		this.heureRdv = heureRdv;
	}

	public String getNomVisiteur() {
		return this.nomVisiteur;
	}

	public void setNomVisiteur(String nomVisiteur) {
		this.nomVisiteur = nomVisiteur;
	}

	public String getPrenomVisiteur() {
		return this.prenomVisiteur;
	}

	public void setPrenomVisiteur(String prenomVisiteur) {
		this.prenomVisiteur = prenomVisiteur;
	}

	public boolean getVisibilite() {
		return this.visibilite;
	}

	public void setVisibilite(boolean visibilite) {
		this.visibilite = visibilite;
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

	public boolean isHasVisit() {
		return hasVisit;
	}

	public void setHasVisit(boolean hasVisit) {
		this.hasVisit = hasVisit;
	}



}