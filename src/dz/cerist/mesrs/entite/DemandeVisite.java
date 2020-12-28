package dz.cerist.mesrs.entite;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the demande_visite database table.
 * 
 */
@Entity
@Table(name="demande_visite")
@NamedQueries({
@NamedQuery(name="DemandeVisite.findAll", query="SELECT d FROM DemandeVisite d"),
@NamedQuery(name="DemandeVisite.findByHote", query="SELECT d FROM DemandeVisite d WHERE d.hote = :hote and d.etat<> 'Terminé' and d.dateDemande = current_date "),
@NamedQuery(name="DemandeVisite.findTerminatedRequestByHote", query="select d from DemandeVisite d where d.hote = :hote and d.etat= 'Terminé' "),
@NamedQuery(name="DemandeVisite.findTodayRequests", query="select d from DemandeVisite d where d.dateDemande = current_date and d.etat <> 'Terminé' ")
})
public class DemandeVisite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String accompagnants;

	private String contact;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_DEMANDE")
	private Date dateDemande;

	@Column(name="DE_LA_PART")
	private String deLaPart;

	private String etat;

	private String fonction;

	private String hote;

	private String motif;

	@Column(name="NOM_VISITEUR")
	private String nomVisiteur;

	@Column(name="NUM_PIECE")
	private String numPiece;

	private String organisme;

	private String phone;

	@Column(name="PRENOM_VISITEUR")
	private String prenomVisiteur;

	
	@Column(name="TYPE_PIECE")
	private String typePiece;

	private int version;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CREATED_BY")
	private User user;

	//bi-directional one-to-one association to ValidationDemande
	@OneToOne(mappedBy="demandeVisite", fetch=FetchType.LAZY)
	private ValidationDemande validationDemande;

	//bi-directional one-to-one association to Visite
	
	@OneToOne(mappedBy="demandeVisite", fetch=FetchType.LAZY)
	private Visite visite;

	public DemandeVisite() {
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

	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
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

	public String getNomVisiteur() {
		return this.nomVisiteur;
	}

	public void setNomVisiteur(String nomVisiteur) {
		this.nomVisiteur = nomVisiteur;
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

	public ValidationDemande getValidationDemande() {
		return this.validationDemande;
	}

	public void setValidationDemande(ValidationDemande validationDemande) {
		this.validationDemande = validationDemande;
	}

	public Visite getVisite() {
		return this.visite;
	}

	public void setVisite(Visite visite) {
		this.visite = visite;
	}

}