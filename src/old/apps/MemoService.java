package old.apps;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import old.domain.App;
import old.domain.MemoApp;
import old.utils.TrioUtils;

@SuppressWarnings("unchecked")
public class MemoService extends App {

	private static final Path MEMO_PATH = Path.of("storage", "memos", "memos.ser");

	private final List<MemoApp> memos = new ArrayList<>();

	private int nextNo = 0;

	public MemoService(int no) {
		super(no, "매모");
	}

	public void run() {
		System.out.println("메모 앱을 실행합니다");

		while (true) {
			int check = TrioUtils.nextInt("1. 메모 추가  \n2. 메모 수정  \n3. 메모 삭제  \n4. 작성한 메모보기  \n5. 돌아가기");

			switch (check) {
				case 1:
					add();
					break;
				case 2:
					modify();
					break;
				case 3:
					delete();
					break;
				case 4:
					showMemo();
					break;
				case 5:
					save();
					return;
				default:
					System.out.println("잘못된 입력입니다.");
					break;
			}
		}
	}

	private void add() {
		String title = TrioUtils.nextLine("제목: ");
		String content = TrioUtils.nextLine("내용: ");
		memos.add(new MemoApp(nextNo++, title, content));
		System.out.println("메모 추가 및 저장 완료");
		save();
	}

	private void modify() {
		if (memos.isEmpty()) {
			System.out.println("수정할 메모기록이 없습니다");
			return;
		}

		showMemo();
		int no = TrioUtils.nextInt("수정할 메모장 번호를 입력하세요");
		MemoApp memo = findBy(no);
		if (memo == null) {
			System.out.println("해당 번호의 메모가 없습니다");
			return;
		}

		String title = TrioUtils.nextLine("제목 (" + memo.getTitle() + "): ");
		String content = TrioUtils.nextLine("내용 (" + memo.getContent() + "): ");

		if (!title.isEmpty()) {
			memo.setTitle(title);
		}
		if (!content.isEmpty()) {
			memo.setContent(content);
		}

		System.out.println("메모가 수정되었습니다.");
		save();
	}

	private void delete() {
		if (memos.isEmpty()) {
			System.out.println("삭제할 메모가 없습니다");
			return;
		}

		showMemo();
		int no = TrioUtils.nextInt("삭제할 메모장 번호를 입력하세요: ");
		MemoApp memo = findBy(no);
		if (memo == null) {
			System.out.println("해당 번호의 메모가 없습니다");
			return;
		}

		if (TrioUtils.nextConfirm("정말 삭제하시겠습니까? (y/n): ")) {
			memos.remove(memo);
			System.out.println("메모장이 삭제되었습니다");
			save();
		} else {
			System.out.println("메모장 삭제가 취소되었습니다");
		}
	}

	private void save() {
		try {
			Files.createDirectories(MEMO_PATH.getParent());
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEMO_PATH.toFile()));
			oos.writeObject(memos);
			oos.close();
		} catch (IOException e) {
			System.err.println("메모장 저장 실패: " + e.getMessage());
		}
	}

	private void load() {
		if (!Files.exists(MEMO_PATH)) {
			System.out.println("저장된 메모 파일이 없습니다: " + MEMO_PATH);
			return;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MEMO_PATH.toFile()));
			List<MemoApp> loaded = (List<MemoApp>) ois.readObject();
			ois.close();

			for (int i = 0; i < loaded.size(); i++) {
				MemoApp m = loaded.get(i);
				memos.add(m);
				if (m.getNo() >= nextNo) {
					nextNo = m.getNo() + 1;
				}
			}
			System.out.println("메모 로드 완료 (" + memos.size() + "개)");
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("메모장 로드 실패: " + e.getMessage());
		}
	}

	public void showMemo() {
		if (memos.isEmpty()) {
			System.out.println("메모가 비어있습니다");
			return;
		}

		for (int i = 0; i < memos.size(); i++) {
			MemoApp m = memos.get(i);
			System.out.printf("번호: %d | 제목: %s | 내용: %s\n", m.getNo(), m.getTitle(), m.getContent());
		}
	}

	private MemoApp findBy(int no) {
		for (int i = 0; i < memos.size(); i++) {
			if (memos.get(i).getNo() == no) {
				return memos.get(i);
			}
		}
		return null;
	}
}
