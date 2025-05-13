package domain;

import java.io.Serializable;

public class DeviceInfo implements Serializable{
	
	private static final String DEVICENAME = "SmarTrio-0001";
	private String userName;
	private String deivcePw;
	private String userEmail;
	private String tel;
	
	public DeviceInfo() {}
	
	public DeviceInfo(String userName, String userEmail, String tel) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.tel = tel;
	}
	
	public DeviceInfo(String userName, String deivcePw, String userEmail ,String tel) {
		super();
		this.userName = userName;
		this.deivcePw = deivcePw;
		this.userEmail = userEmail;
		this.tel = tel;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "DeviceInfo [ 기기 이름 = "+DEVICENAME+"사용자 이름 :" + userName + ", 사용자 이메일=" + userEmail + ", 사용자 전화번호="
				+ tel + "]";
	}
	
	
	
	

	

	
	
	
}
