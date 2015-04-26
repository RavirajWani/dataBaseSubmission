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
//			    "SELECT OBJECT(state) FROM State state WHERE state.countryId = :countryid"
				"select state From State state where state.countryId = :countryid"
			);
		queryUserByUserName.setParameter("countryid", countryid);
		states = (List<State>) queryUserByUserName.getResultList();
		return states;
	}
	
	public static void main(String[] args) {
		StateDao dao = new StateDao();
		List<State> states = new ArrayList<State>();
		Integer i = new Integer(1);
		states = dao.fetchStateList(i);
		System.out.println(states.size());	
	}
}