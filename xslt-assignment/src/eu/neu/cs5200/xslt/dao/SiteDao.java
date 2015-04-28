package eu.neu.cs5200.xslt.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import edu.neu.cs5200.xslt.entity.Site;
import edu.neu.cs5200.xslt.entity.Sites;

public class SiteDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("xslt-assignment");
	EntityManager em = factory.createEntityManager();
	
	public List<Site> createSite(Site site) {
		em.getTransaction().begin();
		em.persist(site);
		em.getTransaction().commit();
		em.close();
		return findAllSites();
	}
	
	
	public Site findSite(int id){
		return em.find(Site.class, id);
	}
	

	@SuppressWarnings("unchecked")
	public List<Site> findAllSites(){
		List<Site> sites = new ArrayList<Site>();
		Query query = em.createQuery("select site from Site site");
		sites = (List<Site>) query.getResultList();
		return sites;
		
	}
	
	
	@SuppressWarnings("unused")
	private List<Site> updateSite(int id, Site site){
		em.getTransaction().begin();
		
		site.setId(id);
		em.merge(site);
		
		em.getTransaction().commit();
		em.close();
		
		return findAllSites();
	}
	
	
	
	public List<Site> removeSite (int siteId){
		em.getTransaction().begin();
		Site site = em.find(Site.class, siteId);
		em.remove(site);
		em.getTransaction().commit();
		em.close();
		return findAllSites();
	}
	
	public static void main(String[] args) {
		SiteDao dao = new SiteDao();
		
		List<Site> sites = dao.findAllSites();
		for(Site site : sites){
			System.out.println(site.getName());
		}
		
		Sites site = new Sites();
		site.setSite(sites);
		
		dao.exportSitesToXmlFile(site ,"xml/sites.xml");
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites.html", "xml/sites2html.xsl");
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites2tower.html", "xml/sites2tower.xsl");
		
	}
	
	public void exportSitesToXmlFile(Sites sites, String xmlFileName)
	{
		File xml = new File(xmlFileName);
		try {
			JAXBContext context = JAXBContext.newInstance(Sites.class);
			Marshaller marshller =  context.createMarshaller();
			marshller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshller.marshal(sites, xml);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void convertXmlFileToOutputFile(String xmlFileName, String outputFile, String xsltFileName){
		File outFile = new File(outputFile);
		File inFile = new File(xmlFileName);
		File xsltFile = new File(xsltFileName);
		
		StreamSource source = new StreamSource(inFile); 
		StreamSource xslt    = new StreamSource(xsltFile); 
		StreamResult output = new StreamResult(outFile); 
		
		TransformerFactory factory = TransformerFactory.newInstance(); 
		 		try { 
		 			Transformer transformer = factory.newTransformer(xslt); 
		 			transformer.transform(source, output); 
		 		} catch (TransformerConfigurationException e) { 
		 			// TODO Auto-generated catch block 
		 			e.printStackTrace(); 
		 		} catch (TransformerException e) { 
		 			// TODO Auto-generated catch block 
		 			e.printStackTrace(); 
		 		} 		
	}

}
