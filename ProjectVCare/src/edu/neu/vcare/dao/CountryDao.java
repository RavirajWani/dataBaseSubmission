package edu.neu.vcare.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.neu.vcare.entity.Country;

public class CountryDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjectVCare");
	EntityManager em = null;

	@SuppressWarnings("unchecked")
	public List<Country> fetchCountryList(){
		em = factory.createEntityManager();
		List<Country> countires = new ArrayList<Country>();
		Query query = em.createQuery("select country from Country country");
		countires = (List<Country>) query.getResultList();
		return countires;
	}
	
}