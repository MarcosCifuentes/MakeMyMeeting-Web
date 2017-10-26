package services;

public class CalendarRest {

		private String name;
		private int idUser;
		
		public CalendarRest(String name, int idUser) {
			super();
			this.name = name;
			this.idUser = idUser;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIdUser() {
			return idUser;
		}

		public void setIdUser(int idUser) {
			this.idUser = idUser;
		}
}
