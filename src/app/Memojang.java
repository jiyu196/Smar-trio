package app;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Memo;
import util.TrioUtils;

public class Memojang extends App{

	private final List<Memo> memos = new ArrayList<>();
	
	private int nextNo = 0;

	public Memojang(int no) {
		super(no, "메모");
	}

//	public static void main(String[] args) {
//		Memojang app = new Memojang(1);
//		app.run();
//	}
	
	public void run() {
		System.out.println("메모 앱을 실행합니다");
		load();
		while (true) {
			System.out.println("1. 메모 추가  \n2. 메모 수정  \n3. 메모 삭제  \n4. 작성한 메모보기  \n5. 돌아가기");
			System.out.println("==============================================");
			int check = TrioUtils.nextInt("옵션을 선택하세요:");
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
	//메모 추가하기
	private void add() {
		String title = TrioUtils.nextLine("제목: ");
		String content = TrioUtils.nextLine("내용: ");
		memos.add(new Memo(nextNo++, title, content)); 
		System.out.println("메모 추가 및 저장 완료");
		System.out.println("==============================================");
		save();
	}
	//메모 수정하기
	private void modify() {
		if (memos.isEmpty()) {
			System.out.println("수정할 메모기록이 없습니다");
			return;
		}

		showMemo();
		int no = TrioUtils.nextInt("수정할 메모장 번호를 입력하세요");
		Memo memo = findBy(no);
		if (memo == null) {
			System.out.println("해당 번호의 메모장이 없습니다");
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

		System.out.println("메모 기록이 수정되었습니다.");
		save();
	}
	//메모 삭제하기
	private void delete() {
		if (memos.isEmpty()) {
			System.out.println("삭제할 메모기록이 없습니다");
			return;
		}

		showMemo();
		int no = TrioUtils.nextInt("삭제할 메모장 번호를 입력하세요: ");
		Memo memo = findBy(no);
		if (memo == null) {
			System.out.println("해당 번호의 메모장이 없습니다");
			return;
		}

		if (TrioUtils.nextConfirm("정말 삭제하시겠습니까? ")) {
			memos.remove(memo);
			System.out.println("메모장이 삭제되었습니다");
			save();
		} else {
			System.out.println("메모장 삭제가 취소되었습니다");
		}
	}
	//메모 저장하기
	private void save() {
		
		TrioUtils.writeLog("memos", "memos_log.ser", new ArrayList<>(memos));
		System.out.println("메모장 앱이 종료되었습니다");
	}
	
	//메모 로드
	private void load() {
		memos.clear();
		Path logPath = Path.of("storage", "memos", "memos_log.ser");
		if (!Files.exists(logPath)) {
			System.out.println("저장된 메모기록이 없습니다: ");
			return;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(logPath.toFile()));
			List<Memo> loaded = (List<Memo>) ois.readObject();
			ois.close();

			for (int i = 0; i < loaded.size(); i++) {
				Memo m = loaded.get(i);
				memos.add(m);
				if (m.getNo() >= nextNo) {
					nextNo = m.getNo() + 1;
				}
			}
			System.out.println("저장된 메모 (" + memos.size() + "개)");
			System.out.println("==============================================");
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("메모장 로드 실패: " + e.getMessage());
		}
	}

	public void showMemo() {
		if (memos.isEmpty()) {
			System.out.println("메모장이 비어있습니다");
			return;
		}

		for (int i = 1; i < memos.size(); i++) {
			Memo m = memos.get(i);
			System.out.printf("%d번 메모장 | <제목>  %s | <내용>  %s\n  <작성 날짜> %s\n", m.getNo(), m.getTitle(), m.getContent(), m.getDate());
			System.out.println("-------------------------------------------------");
		}
	}

	private Memo findBy(int no) {
		for (int i = 0; i < memos.size(); i++) {
			if (memos.get(i).getNo() == no) {
				return memos.get(i);
			}
		}
		return null;
	}
}
