package domain;

import java.io.Serializable;

public class DeviceInfo implements Serializable{
	
	private static final String DEVICENAME = "SmarTrio-0001";
	private String userName;
	private String deivcePw;
	private String userEmail;
	
	
	public DeviceInfo(String userName, String userEmail) {
		this.userName = userName;
		this.userEmail = userEmail;
	}
	
	public DeviceInfo(String userName, String deivcePw, String userEmail) {
		super();
		this.userName = userName;
		this.deivcePw = deivcePw;
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeivcePw() {
		return deivcePw;
	}

	public void setDeivcePw(String deivcePw) {
		this.deivcePw = deivcePw;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public static String getDevicename() {
		return DEVICENAME;
	}


	
	
	
}
