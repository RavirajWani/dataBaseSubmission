package edu.neu.vcare.controller;

import java.util.List;

import edu.neu.vcare.bean.UserBean;
import edu.neu.vcare.dao.UserDao;
import edu.neu.vcare.entity.User;

public class UserController {
	
	public UserBean fetchUserDetails(int userId){
		UserDao uDao = new UserDao();
		User userInfo = new User();
		userInfo = uDao.fetchUserById(userId);
		UserBean user = new UserBean();
		user.setFirstName(userInfo.getFirstName());
		user.setLastName(userInfo.getLastName());
		user.setCountry(userInfo.getCountryid());
		user.setState(userInfo.getStateid());
		user.setUsername(userInfo.getUsername());
		user.setPassword(userInfo.getPassword());
		user.setId(userInfo.getId());
		user.setEmailId(userInfo.getEmailId());
		return user;
	}
	
	public UserBean updateUserDetails(UserBean user){
		
		UserDao uDao = new UserDao();
		
		User userInfo = new User();
		userInfo.setCountryid(user.getCountry());
		userInfo.setFirstName(user.getFirstName());
		userInfo.setId(user.getId());
		userInfo.setLastName(user.getLastName());
		userInfo.setPassword(user.getPassword());
		userInfo.setStateid(user.getState());
		userInfo.setUsername(user.getUsername());
		userInfo.setEmailId(user.getEmailId());
		User updatedUser = uDao.updateUser(userInfo);
		UserBean userBean = new UserBean();
		userBean.setFirstName(updatedUser.getFirstName());
		userBean.setLastName(updatedUser.getLastName());
		userBean.setCountry(updatedUser.getCountryid());
		userBean.setState(updatedUser.getStateid());
		userBean.setUsername(updatedUser.getUsername());
		userBean.setPassword(updatedUser.getPassword());
		userBean.setId(updatedUser.getId());
		return userBean;
	}
	
	public Boolean fetchUserName(String userName){
		System.out.println("UserName in Controller" + userName);
		Boolean isUserPresent = false;
		UserDao uDao = new UserDao();
		List<String> userNames = uDao.fetchUserName();
		for(String user : userNames){
			if(userName.equals(user))
				isUserPresent = true;
		}
		return isUserPresent;
		
	}
	
}