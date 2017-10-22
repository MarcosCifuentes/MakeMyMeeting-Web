package services;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import entities.Invitation;
import entities.Meeting;
import entities.User;

public class DAOInvitation {

		private static DAOInvitation daoInvitation;

		private DAOInvitation(){
		}

		public static DAOInvitation getInstance() {
			if(daoInvitation == null)
				daoInvitation = new DAOInvitation();
			return daoInvitation;
		}
		
		public Invitation createInvitation (Meeting meeting, User user) {
			EntityManager em=EMF.createEntityManager();
			em.getTransaction( ).begin( );
			Invitation newInvitation = new Invitation (meeting,user);
			em.persist(newInvitation);
			em.getTransaction().commit();
			em.close();
			user.addInvitation(newInvitation);
			meeting.addInvitation(user);
			
			return newInvitation;
		}
		
		public Invitation getInvitation(int idInvitation) {
			EntityManager em=EMF.createEntityManager();
			String jpql = "SELECT i FROM Invitation i WHERE i.id = ?1"; 
			Query query = em.createQuery(jpql); 
			query.setParameter(1, idInvitation);
			return (Invitation) query.getSingleResult();
		}

		public Invitation update(int id, Meeting meeting, User user) {
			EntityManager em=EMF.createEntityManager();
			em.getTransaction().begin();		
			String jpql = "UPDATE Invitation SET meeting=?2, user=?3, WHERE Invitation.id = ?1"; 
			Query query = em.createQuery(jpql);
			query.setParameter(1, id);
			query.setParameter(2, meeting);
			query.setParameter(3, user);
			query.executeUpdate();
			em.getTransaction().commit();
			em.close();
			Invitation invitation = getInvitation(id);

			return invitation;
		}

		public boolean delete(Integer id) {
			boolean deleted = false;

			EntityManager em=EMF.createEntityManager();
			em.getTransaction().begin();
			String jpql = "DELETE FROM Invitation i WHERE i.id = ?1"; 
			Query query = em.createQuery(jpql);
			query.setParameter(1, id);
			query.executeUpdate();
			em.getTransaction().commit();
			em.close();
			Invitation invitation =getInvitation(id);
			if (invitation == null) {
				deleted = true;
			}	
			return deleted;
		}
}
