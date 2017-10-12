package services;

import javax.persistence.EntityManager;

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
		
		public void createInvitation (Meeting meeting, User user) {
			EntityManager em=EMF.createEntityManager();
			em.getTransaction( ).begin( );
			Invitation newInvitation = new Invitation (meeting,user);
			em.persist(newInvitation);
			em.getTransaction().commit();
			user.addInvitation(newInvitation);
			meeting.addInvitation(newInvitation);
		}
}
