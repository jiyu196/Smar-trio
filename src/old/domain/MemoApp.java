package old.domain;

import java.io.Serializable;

public class MemoApp implements Serializable {

	private int no;
	private String title;
	private String content;

	public MemoApp() {
	// TODO Auto-generated constructor stub
	}

	public MemoApp(int no, String title, String content) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
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

	@Override
	public String toString() {
		return "MemoApp [no=" + no + ", title=" + title + ", content=" + content + "]";
	}
	
	
	
	
	
//	public MemoApp(int no, String title, String content) {
//		this.no = no;
//		this.title = title;
//		this.content = content;
//	}
//
//	public int getNo() {
//		return no;
//	}
//
//	public Object getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public Object getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	@Override
//	public String toString() {
//		return no + ";" + title + ";" + content;
//	}
//
//	public static MemoApp fromString(String line) {
//		String[] parts = line.split(";");
//		if (parts.length == 3) {
//			try {
//			int no = Integer.parseInt(parts[0]);
//			return new MemoApp(no, parts[1], parts[2]);
//			} catch (NumberFormatException e) {
//				return null;
//			}
//		}
//		return null;
//	}
}
