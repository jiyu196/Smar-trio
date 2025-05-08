package domain.Apps;

public class MemoApp {

	private final int no;
	private String title;
	private String content;

	public MemoApp(int no, String title, String content) {
		this.no = no;
		this.title = title;
		this.content = content;
	}
	
	public int getNo() {
		return no;
	}

	public Object getTitle() {

		return getTitle();
	}

	public Object getContent() {

		return getContent();
	}

	public void setTitle(String title) {

	}

	public void setContent(String content) {

	}

	@Override
	public String toString() {
		return "메모 [no=" + no + ", title=" + title + ", content=" + content + "]";
	}

	public static MemoApp fromString(String line) {
		String[] parts = line.split(";");
		if (parts.length == 5) {
			int no = Integer.parseInt(parts[0]);
			return new MemoApp(no, parts[1], parts[2]);
		}
		return null;
	}
}
