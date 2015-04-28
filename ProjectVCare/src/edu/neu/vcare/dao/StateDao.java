package edu.neu.vcare.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.vcare.entity.State;

public class StateDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjectVCare");
	EntityManager em = null;

	@SuppressWarnings("unchecked")
	public List<State> fetchStateList(Integer countryid){
		List<State> states = new ArrayList<State>();
		em = factory.createEntityManager();
		Query queryUserByUserName = em.createQuery(
				"select state From State state where state.countryId = :countryid"
			);
		queryUserByUserName.setParameter("countryid", countryid);
		states = (List<State>) queryUserByUserName.getResultList();
		return states;
	}
	
}