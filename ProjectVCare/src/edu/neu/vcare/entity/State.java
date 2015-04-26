package edu.neu.vcare.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class State {
		@Id
		private int id;
		private String name;
		private int countryId;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getCountryId() {
			return countryId;
		}
		public void setCountryId(int countryId) {
			this.countryId = countryId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		

	
}
