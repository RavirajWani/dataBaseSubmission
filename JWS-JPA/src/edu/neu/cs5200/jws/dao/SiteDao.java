package edu.neu.cs5200.jws.dao;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import edu.neu.cs5200.jws.entity.Site;

@Path("/site")
public class SiteDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("jws-assignment");
	EntityManager em = factory.createEntityManager();
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Site> createSite(Site site) {
		em.getTransaction().begin();
		em.persist(site);
		em.getTransaction().commit();
		return findAllSites();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("id") int id){
		return em.find(Site.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Site> findAllSites(){
		List<Site> sites = new ArrayList<Site>();
		Query query = em.createQuery("select site from Site site");
		sites = (List<Site>) query.getResultList();
		return sites;
		
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	private List<Site> updateSite(Site site){
		em.getTransaction().begin();
		em.merge(site);
		em.getTransaction().commit();
		
		return findAllSites();
	}
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> removeSite (Integer siteId){
		em.getTransaction().begin();
		Site site = em.find(Site.class, siteId);
		em.remove(site);
		em.getTransaction().commit();
		return findAllSites();
	}
	
	public static void main(String[] args) {
		SiteDao dao = new SiteDao();
		
		//Site site = new Site(null, "SilverMine" ,"32.25","34.25");
		//site = dao.createSite(site);
		//System.out.println(site.getId());
		
		//Site goldMine = dao.readSiteById(1);
		//System.out.println(goldMine.getName());
		
		//dao.removeSite(2);
		
		List<Site> sites = dao.findAllSites();
		for(Site site : sites){
			System.out.println(site.getName());
		}
		
		//goldMine.setLongitude("100SW");
		//goldMine = dao.updateSite(goldMine);
		//System.out.println(goldMine.getLongitude());
		
	}
	
	@GET
	@Path("/world")
	public String sayHello(){
		return "Hello world";
	}
	
	@GET
	@Path("/helloworld")
	public String sayHelloWorld(){
		return "Hello !!!!";
	}
}
