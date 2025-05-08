package domain.apps;

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
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return no + ";" + title + ";" + content;
	}

	public static MemoApp fromString(String line) {
		String[] parts = line.split(";");
		if (parts.length == 3) {
			try {
			int no = Integer.parseInt(parts[0]);
			return new MemoApp(no, parts[1], parts[2]);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}
}
