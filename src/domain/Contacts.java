package domain;

import java.io.Serializable;

public class Contacts implements Serializable {
	private final int no;
	private String name;
	private String phone;
	private String email;
	private String nickname;

	public Contacts(int no, String name, String phone, String email, String nickname) {
		this.no = no;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.nickname = nickname;
	}

	public int getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return no + ";" + name + ";" + phone + ";" + email + ";" + nickname;
	}
}
