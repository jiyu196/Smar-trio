package domain;


public class DeviceInfo{
	
	String deviceName = "SmarTrio-0001";
	String userName;
	String deivcePw;
	
	
	
	public DeviceInfo(String userName, String deivcePw) {
		super();

		this.userName = userName;
		this.deivcePw = deivcePw;
	}
	
	
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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
	
	
	
}
