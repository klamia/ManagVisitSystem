package dz.cerist.mesrs.entite;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the visite database table.
 * 
 */
@Entity
@Table(name="visite")
@NamedQueries({
@NamedQuery(name="Visite.findAll", query="SELECT v FROM Visite v"), 
@NamedQuery(name="Visite.findVisiteByHote", query="select v from Visite v where v.hote = :hote and v.dateVisite = current_date and v.etat <> 'Terminée'"),
@NamedQuery(name="Visite.findTerminatedVisitsByHote", query="select v from Visite v where v.hote = :hote and v.etat= 'Terminée'"),
@NamedQuery(name="Visite.findTodayVisits", query="select v from Visite v where v.dateVisite = current_date and v.etat <> 'Terminée'")
})
public class Visite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String accompagnants;

	private String contact;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_VISITE")
	private Date dateVisite;

	@Column(name="DE_LA_PART")
	private String deLaPart;

	private String etat;

	@Column(name="FIRST_CHECKING")
	
	private Time firstChecking;

	@Column(name="FIRST_CHECKOUT")
	
	private Time firstCheckout;

	private String fonction;

	private String hote;

	private String motif;

	@Column(name="NATURE_VISITE")
	private String natureVisite;

	@Column(name="NOM_VISITEUR")
	private String nomVisiteur;

	@Column(name="NUM_BADGE")
	private int numBadge;

	@Column(name="NUM_PIECE")
	private String numPiece;

	private String organisme;

	private String phone;

	@Column(name="PRENOM_VISITEUR")
	private String prenomVisiteur;

	@Column(name="SECOND_CHECKING")
	
	private Time secondChecking;

	@Column(name="SECOND_CHECKOUT")
	
	private Time secondCheckout;

	@Column(name="TYPE_PIECE")
	
	private String typePiece;

	private int version;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CREATED_BY")
	private User user;

	//bi-directional one-to-one association to DemandeVisite
	@OneToOne(cascade={CascadeType.MERGE,CascadeType.REMOVE }, fetch=FetchType.EAGER)
	@JoinColumn(name="DEMANDE_ID")
	private DemandeVisite demandeVisite;

	//bi-directional one-to-one association to RendezVous
	
	@OneToOne(cascade={CascadeType.MERGE, CascadeType.REMOVE }, fetch=FetchType.EAGER)
	@JoinColumn(name="RDV_ID")
	private RendezVous rendezVous;

	//bi-directional one-to-one association to ValidationVisite
	@OneToOne(mappedBy="visite", fetch=FetchType.LAZY)
	private ValidationVisite validationVisite;

	public Visite() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccompagnants() {
		return this.accompagnants;
	}

	public void setAccompagnants(String accompagnants) {
		this.accompagnants = accompagnants;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getDateVisite() {
		return this.dateVisite;
	}

	public void setDateVisite(Date dateVisite) {
		this.dateVisite = dateVisite;
	}

	public String getDeLaPart() {
		return this.deLaPart;
	}

	public void setDeLaPart(String deLaPart) {
		this.deLaPart = deLaPart;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Time getFirstChecking() {
		return this.firstChecking;
	}

	public void setFirstChecking(Time firstChecking) {
		this.firstChecking = firstChecking;
	}

	public Time getFirstCheckout() {
		return this.firstCheckout;
	}

	public void setFirstCheckout(Time firstCheckout) {
		this.firstCheckout = firstCheckout;
	}

	public String getFonction() {
		return this.fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getHote() {
		return this.hote;
	}

	public void setHote(String hote) {
		this.hote = hote;
	}

	public String getMotif() {
		return this.motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getNatureVisite() {
		return this.natureVisite;
	}

	public void setNatureVisite(String natureVisite) {
		this.natureVisite = natureVisite;
	}

	public String getNomVisiteur() {
		return this.nomVisiteur;
	}

	public void setNomVisiteur(String nomVisiteur) {
		this.nomVisiteur = nomVisiteur;
	}

	public int getNumBadge() {
		return this.numBadge;
	}

	public void setNumBadge(int numBadge) {
		this.numBadge = numBadge;
	}

	public String getNumPiece() {
		return this.numPiece;
	}

	public void setNumPiece(String numPiece) {
		this.numPiece = numPiece;
	}

	public String getOrganisme() {
		return this.organisme;
	}

	public void setOrganisme(String organisme) {
		this.organisme = organisme;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPrenomVisiteur() {
		return this.prenomVisiteur;
	}

	public void setPrenomVisiteur(String prenomVisiteur) {
		this.prenomVisiteur = prenomVisiteur;
	}

	public Time getSecondChecking() {
		return this.secondChecking;
	}

	public void setSecondChecking(Time secondChecking) {
		this.secondChecking = secondChecking;
	}

	public Time getSecondCheckout() {
		return this.secondCheckout;
	}

	public void setSecondCheckout(Time secondCheckout) {
		this.secondCheckout = secondCheckout;
	}

	public String getTypePiece() {
		return this.typePiece;
	}

	public void setTypePiece(String typePiece) {
		this.typePiece = typePiece;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DemandeVisite getDemandeVisite() {
		return this.demandeVisite;
	}

	public void setDemandeVisite(DemandeVisite demandeVisite) {
		this.demandeVisite = demandeVisite;
	}

	public RendezVous getRendezVous() {
		return this.rendezVous;
	}

	public void setRendezVous(RendezVous rendezVous) {
		this.rendezVous = rendezVous;
	}

	public ValidationVisite getValidationVisite() {
		return this.validationVisite;
	}

	public void setValidationVisite(ValidationVisite validationVisite) {
		this.validationVisite = validationVisite;
	}

}