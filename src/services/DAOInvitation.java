package services;

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
			Invitation newInvitation = new Invitation (meeting,user);
			user.addInvitation(newInvitation);
			meeting.addInvitation(newInvitation);
		}
}
