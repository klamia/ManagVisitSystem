package dz.cerist.mesrs.web.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


import org.primefaces.model.chart.PieChartModel;


import dz.cerist.mesrs.service.UserBo;
import dz.cerist.mesrs.web.util.Statistics;

@ManagedBean
@ViewScoped
public class AdminStatsView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PieChartModel pieModel1, pieModel2;
	
	private int activeUsers, disabledUsers, newUsers, loggedUsers;
	
	private Collection<Statistics> stat1, stat2;
	
	@ManagedProperty(name="userBo", value="#{userBo}")
	private UserBo userBo;
	
	
	public Collection<Statistics> getStat1() {
		return stat1;
	}

	public void setStat1(Collection<Statistics> stat1) {
		this.stat1 = stat1;
	}

	public Collection<Statistics> getStat2() {
		return stat2;
	}

	public void setStat2(Collection<Statistics> stat2) {
		this.stat2 = stat2;
	}

	public UserBo getUserBo() {
		return userBo;
	}

	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}


	
	public AdminStatsView() {
		
	}

	@PostConstruct
	    public void init(){
		
		 activeUsers=userBo.getActiveUsersNumber();
		 disabledUsers=userBo.getdisabledUsersNumber();
		 newUsers=userBo.getNewUsersNumber();
		 loggedUsers=userBo.getConnectedUsersNumber();
		 stat1=new ArrayList <Statistics>();
		 Collection<Object> counter=userBo.getConnectedUsersByRole();
		 Iterator<Object> itr = counter.iterator();
	        while(itr.hasNext()){
	        	Object[] obj = (Object[]) itr.next();
				Statistics st=new Statistics();
				st.setLabel(String.valueOf(obj[0]));
				st.setValue(Integer.parseInt(String.valueOf(obj[1])));
				stat1.add(st);
			}
	        
	        stat2=new ArrayList <Statistics>();
	        Collection<Object> counter2=userBo.getdesabledUsersByRole();
			 itr = counter2.iterator();
		        while(itr.hasNext()){
		        	Object[] obj = (Object[]) itr.next();
					Statistics st=new Statistics();
					st.setLabel(String.valueOf(obj[0]));
					st.setValue(Integer.parseInt(String.valueOf(obj[1])));
					stat2.add(st);
				}
	     // fill pie chart
	    	createPieModel();
	 }
	 
	 private void createPieModel() {
	        pieModel1 = new PieChartModel();
	        for (Statistics st:stat1){
	        	pieModel1.set(st.getLabel(), st.getValue());
			}
	        pieModel1.setLegendPosition("e");
	        pieModel1.setDiameter(250);
	        pieModel1.setSeriesColors("5680e9, 5ab9ea, c1c8e4, 8860d8");
	        
	        pieModel2 = new PieChartModel();
	        for (Statistics st:stat2){
	        	pieModel2.set(st.getLabel(), st.getValue());
			}
	        pieModel2.setLegendPosition("e");
	        pieModel2.setDiameter(250);
	        pieModel2.setSeriesColors("5680e9, 5ab9ea, c1c8e4, 8860d8");
	    }

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

		public int getActiveUsers() {
		return activeUsers;
	}

	public void setActiveUsers(int activeUsers) {
		this.activeUsers = activeUsers;
	}

		public int getDisabledUsers() {
		return disabledUsers;
	}

	public void setDisabledUsers(int disabledUsers) {
		this.disabledUsers = disabledUsers;
	}

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
	}

	public int getNewUsers() {
		return newUsers;
	}

	public void setNewUsers(int newUsers) {
		this.newUsers = newUsers;
	}

	public int getLoggedUsers() {
		return loggedUsers;
	}

	public void setLoggedUsers(int loggedUsers) {
		this.loggedUsers = loggedUsers;
	}



}
