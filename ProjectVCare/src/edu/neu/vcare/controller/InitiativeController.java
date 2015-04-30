package edu.neu.vcare.controller;

import java.util.ArrayList;
import java.util.List;

import edu.neu.vcare.dao.InitiativeDao;
import edu.neu.vcare.entity.Initiative;

public class InitiativeController {
	
	
	public List<Initiative> fetchAllBlog(int userId){
		InitiativeDao iDao = new InitiativeDao();
		List<Initiative> initiatives = new ArrayList<Initiative>();
		initiatives = iDao.fetchAllInitiative();
		return initiatives;
	}
	
	public List<Initiative> fetchUserInitiative(int userId){
		InitiativeDao iDao = new InitiativeDao();
		List<Initiative> initiatives = new ArrayList<Initiative>();
		initiatives = iDao.fetchAllInitiative();
		List<Initiative> usersInitiatives = new ArrayList<Initiative>();
		for(Initiative i : initiatives){
			if(i.getUserId() == userId){
				usersInitiatives.add(i);
			}
		}
		initiatives.removeAll(usersInitiatives);
		return usersInitiatives;
	}

}
