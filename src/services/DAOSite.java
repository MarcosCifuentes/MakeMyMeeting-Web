package services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Meeting;
import entities.Site;
import entities.User;

public class DAOSite {

	private static DAOSite daoSite;

	private DAOSite(){
	}

	public static DAOSite getInstance() {
		if(daoSite == null)
			daoSite = new DAOSite();
		return daoSite;
	}

	public Site createSite(String name, String address) {
		EntityManager em=EMF.createEntityManager();
		em.getTransaction( ).begin( );
		Site newSite = new Site (name, address);
		em.persist(newSite);
		em.getTransaction().commit();
		return newSite;
	}

	public Site getSite(int idSite) {
		EntityManager em=EMF.createEntityManager();
		String jpql = "SELECT s FROM Site s WHERE s.id = ?1"; 
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idSite);
		return (Site) query.getSingleResult();

	}
	
	public Site update(int id,String name, String address) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();		
		String jpql = "UPDATE Site SET name=?2, "
				+ "address=?3 WHERE Site.id = ?1"; 
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1, id);
        query.setParameter(2, name);
        query.setParameter(3, address);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        Site site = getSite(id);
 
		return site;
	}
	
	public boolean delete(Integer id) {
		boolean deleted = false;

		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		String jpql = "DELETE FROM Site s WHERE s.id = ?1"; 
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1, id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        
        Site site =getSite(id);
		if (site == null) {
			deleted = true;
		}	
		return deleted;
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
		System.out.println(results);
		if (results.isEmpty()){ 
			overlap=false;
		}			
		return overlap;
	}
}
