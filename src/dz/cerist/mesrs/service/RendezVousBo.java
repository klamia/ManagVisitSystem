package dz.cerist.mesrs.service;


import java.util.List;

import dz.cerist.mesrs.entite.RendezVous;
import dz.cerist.mesrs.entite.User;




public interface RendezVousBo {
	
	public void persist(RendezVous rendezVous);

	public RendezVous merge(RendezVous rendezVous);

	public void remove(RendezVous rendezVous);

	public List<RendezVous> findAll();

	public List<RendezVous> findByUser(User user);
	
	public List<RendezVous> findTerminatedRDVByUser(User user);
	
	public List<RendezVous> findTodayRDV();
	
	public RendezVous getById(Integer id);
	
	public void RendreVisible(RendezVous rendezVous);


}
