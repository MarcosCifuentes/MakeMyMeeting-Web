package services;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entities.Meeting;
import entities.Site;

public class DAOSite {

	private static DAOSite daoSite;

	private DAOSite(){
	}

	public static DAOSite getInstance() {
		if(daoSite == null)
			daoSite = new DAOSite();
		return daoSite;
	}

	public List<Site> getSites() {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT s FROM Site s "; 
		Query query = em.createQuery(jpql); 
		List<Site> results = query.getResultList(); 
		em.close();
		return results;
	}

	public Site getSite(Integer id) {
		EntityManager em=EMF.createEntityManager();
		Site site=em.find(Site.class, id);
		em.close();
		return site;
	}

	public Site createSite(String name, String address) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction( ).begin( );
		Site newSite = new Site (name, address);
		em.persist(newSite);
		em.getTransaction().commit();
		em.close();
		return newSite;
	}

	public Site update(Integer id, Site newSite) {
		EntityManager em=EMF.createEntityManager();

		Site site = em.find(Site.class, id);

		if(site!=null) {
			em.getTransaction().begin();
			site.setName(newSite.getName());
			site.setAddress(newSite.getAddress());
			em.persist(site);
			em.getTransaction().commit();
			em.close();
			return site;
		}
		return null;
	}

	public boolean delete(Integer id) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Site.class, id));
		em.getTransaction().commit();
		Site site = em.find(Site.class, id);
		em.close();

		if(site==null)return true;
		return false;
	}

	public boolean overlap (int idSite, Date start, Date end) {
		boolean overlap = true;

		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT m FROM Meeting m WHERE m.site.id = ?1"
				+ " AND m.dateStart <= ?2"
				+ " AND ?2 <= m.dateEnd"
				+ " OR m.dateStart <= ?3"
				+ " AND ?2 <= m.dateStart)";
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idSite);
		query.setParameter(2, start);
		query.setParameter(3, end);
		List<Meeting> results =	query.getResultList();
		if (results.isEmpty()){ 
			overlap=false;
		}			
		return overlap;
	}
}
