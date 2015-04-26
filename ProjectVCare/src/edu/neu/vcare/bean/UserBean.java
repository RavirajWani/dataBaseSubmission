package edu.neu.vcare.bean;

public class UserBean {
	
	private int id;
	private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int country;
    private int state;
	public boolean valid;
	private String emailId;
	
    public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
       return firstName;
	}

    public void setFirstName(String newFirstName) {
       firstName = newFirstName;
	}

    public String getLastName() {
       return lastName;
			}

    public void setLastName(String newLastName) {
       lastName = newLastName;
			}
			

    public String getPassword() {
       return password;
	}

    public void setPassword(String newPassword) {
       password = newPassword;
	}
	
			
    public String getUsername() {
       return username;
			}

    public void setUserName(String newUsername) {
       username = newUsername;
			}
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}
				
    public boolean isValid() {
       return valid;
	}

    public void setValid(boolean newValid) {
       valid = newValid;
	}	
    public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}