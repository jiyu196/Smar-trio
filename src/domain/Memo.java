package domain;

import java.io.Serializable;
import java.util.Date;

public class Memo implements Serializable{
	private int no;
	private String title;
	private String content;
	private String date ;

	public Memo() {
		// TODO Auto-generated constructor stub
	}

	public Memo(int no, String title, String content, String date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.date = date;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Memo [no=" + no + ", title=" + title + ", content=" + content + ", date=" + date + "]";
	}
	


}
