package domain;

public class ContactsList {
	
	private int listNo;
	private String name;
	private String email;
	private String phoneNum;
	private String nickName;
	
	public int getListNo() {
		return listNo;
	}
	public void setListNo(int listNo) {
		this.listNo = listNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Override
	public String toString() {
		return "ContactsList [listNo=" + listNo + ", name=" + name + ", email=" + email + ", phoneNum=" + phoneNum
				+ ", nickName=" + nickName + "]";
	}
}
