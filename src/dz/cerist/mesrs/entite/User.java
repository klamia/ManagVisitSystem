package dz.cerist.mesrs.entite;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user",  uniqueConstraints={@UniqueConstraint(name="nom_prenom", columnNames={"NOM", "PRENOM"}), @UniqueConstraint(name="login", columnNames={"LOGIN"}) })
@NamedQueries({
@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
@NamedQuery(name="User.getByLogin", query="select u from User u where u.login=:login"),
@NamedQuery(name="User.getActiveUsersByRole", query="select u.role, count(u) from User u where u.role not like 'ROLE_ADMIN' and u.enabled=true GROUP BY u.role"),
@NamedQuery(name="User.getdesabledUsersByRole", query="select u.role, count(u) from User u where u.role not like 'ROLE_ADMIN' and u.enabled=false GROUP BY u.role"),
@NamedQuery(name="User.getConnectedUsersByRole", query="select u.role, count(u) from User u where u.role not like 'ROLE_ADMIN' and u.connecte=true GROUP BY u.role"),
@NamedQuery(name="User.findUsersByLogin", query="select u from User u where u.login like :pattern"),
@NamedQuery(name="User.getUserByActionToken", query="SELECT u FROM User u WHERE u.actionToken = :actionToken"),
@NamedQuery(name="User.getUserByIdentifier", query="SELECT u FROM User u WHERE u.login = :userIdentifier OR u.email = :userIdentifier"),
@NamedQuery(name="User.getActiveUsers", query="select u from User u where u.enabled= true "),
@NamedQuery(name="User.getdisabledUsers", query="select u from User u where u.enabled=false "),
@NamedQuery(name="User.getConnectedUsers", query="select u from User u where u.connecte=true "),
@NamedQuery(name="User.getNewUsers", query="select u from User u where u.dateCreation=current_date"),
@NamedQuery(name="User.numberOfUserByUsername", query="SELECT COUNT(u) FROM User u WHERE u.login = :desiredUsername"),
@NamedQuery(name="User.numberOfUserByEmail", query="SELECT COUNT(u) FROM User u WHERE u.email = :email")

})

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private boolean connecte;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_CREATION")
	private Date dateCreation;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_DERN_CONNEX")
	private Date dateDernConnex;

	private String departement;

	@Column(unique = true)
	private String email;

	private boolean enabled;

	private String fonction;

	@Column(unique = true)
	private String login;

	private String nom;

	private String password;

	private String prenom;

	private String role;

	private int version;
	
	private String actionToken;

	//bi-directional many-to-one association to DemandeVisite
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<DemandeVisite> demandeVisites;

	//bi-directional many-to-one association to RendezVous
	@OneToMany(mappedBy="user", cascade={CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH }, fetch=FetchType.LAZY)
	private List<RendezVous> rendezVouses;

	//bi-directional many-to-one association to ValidationDemande
	@OneToMany(mappedBy="user", cascade={CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH }, fetch=FetchType.LAZY)
	private List<ValidationDemande> validationDemandes;

	//bi-directional many-to-one association to Visite
	@OneToMany(mappedBy="user", cascade={CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH }, fetch=FetchType.LAZY)
	private List<Visite> visites;

	//bi-directional many-to-one association to ValidationVisite
	@OneToMany(mappedBy="user", cascade={CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH }, fetch=FetchType.LAZY)
	private List<ValidationVisite> validationVisites;

	public User() {
	
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getConnecte() {
		return this.connecte;
	}

	public void setConnecte(boolean connecte) {
		this.connecte = connecte;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateDernConnex() {
		return this.dateDernConnex;
	}

	public void setDateDernConnex(Date dateDernConnex) {
		this.dateDernConnex = dateDernConnex;
	}

	public String getDepartement() {
		return this.departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFonction() {
		return this.fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	
	public String getActionToken() {
		return actionToken;
	}

	public void setActionToken(String actionToken) {
		this.actionToken = actionToken;
	}

	public List<DemandeVisite> getDemandeVisites() {
		return this.demandeVisites;
	}

	public void setDemandeVisites(List<DemandeVisite> demandeVisites) {
		this.demandeVisites = demandeVisites;
	}

	public DemandeVisite addDemandeVisite(DemandeVisite demandeVisite) {
		getDemandeVisites().add(demandeVisite);
		demandeVisite.setUser(this);

		return demandeVisite;
	}

	public DemandeVisite removeDemandeVisite(DemandeVisite demandeVisite) {
		getDemandeVisites().remove(demandeVisite);
		demandeVisite.setUser(null);

		return demandeVisite;
	}

	public List<RendezVous> getRendezVouses() {
		return this.rendezVouses;
	}

	public void setRendezVouses(List<RendezVous> rendezVouses) {
		this.rendezVouses = rendezVouses;
	}

	public RendezVous addRendezVous(RendezVous rendezVous) {
		getRendezVouses().add(rendezVous);
		rendezVous.setUser(this);

		return rendezVous;
	}

	public RendezVous removeRendezVous(RendezVous rendezVous) {
		getRendezVouses().remove(rendezVous);
		rendezVous.setUser(null);

		return rendezVous;
	}

	public List<ValidationDemande> getValidationDemandes() {
		return this.validationDemandes;
	}

	public void setValidationDemandes(List<ValidationDemande> validationDemandes) {
		this.validationDemandes = validationDemandes;
	}

	public ValidationDemande addValidationDemande(ValidationDemande validationDemande) {
		getValidationDemandes().add(validationDemande);
		validationDemande.setUser(this);

		return validationDemande;
	}

	public ValidationDemande removeValidationDemande(ValidationDemande validationDemande) {
		getValidationDemandes().remove(validationDemande);
		validationDemande.setUser(null);

		return validationDemande;
	}

	public List<Visite> getVisites() {
		return this.visites;
	}

	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}

	public Visite addVisite(Visite visite) {
		getVisites().add(visite);
		visite.setUser(this);

		return visite;
	}

	public Visite removeVisite(Visite visite) {
		getVisites().remove(visite);
		visite.setUser(null);

		return visite;
	}

	public List<ValidationVisite> getValidationVisites() {
		return this.validationVisites;
	}

	public void setValidationVisites(List<ValidationVisite> validationVisites) {
		this.validationVisites = validationVisites;
	}

	public ValidationVisite addValidationVisite(ValidationVisite validationVisite) {
		getValidationVisites().add(validationVisite);
		validationVisite.setUser(this);

		return validationVisite;
	}

	public ValidationVisite removeValidationVisite(ValidationVisite validationVisite) {
		getValidationVisites().remove(validationVisite);
		validationVisite.setUser(null);

		return validationVisite;
	}

}