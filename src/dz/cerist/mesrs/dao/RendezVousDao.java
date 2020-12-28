package dz.cerist.mesrs.dao;

import java.util.List;

import dz.cerist.mesrs.entite.RendezVous;
import dz.cerist.mesrs.entite.User;




public interface RendezVousDao {

	public void persist(RendezVous rendezVous);

	public RendezVous merge(RendezVous rendezVous);

	public void remove(RendezVous rendezVous);

	public void removeById(Integer id);
	
	public List<RendezVous> findAll();

	public List<RendezVous> findByUser(User user);
	
	public List<RendezVous> findTerminatedRDVByUser(User user);
	
	public List<RendezVous> findTodayRDV();
	
	public RendezVous getById(Integer id);


}
