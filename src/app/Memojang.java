package app;

import java.io.FileInputStream;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import domain.Memo;
import static util.TrioUtils.*;

public class Memojang extends App {

	private List<Memo> memos;
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
//		load();
		loadMemo();
		while (true) {
			System.out.println(""
					+ " (1) 메모 추가\n"
					+ " (2) 메모 수정\n"
					+ " (3) 메모 삭제\n"
					+ " (4) 메모 보기\n"
					+ " (0) 돌아가기");
			System.out.println("-".repeat(30));
			int check = nextInt("옵션을 선택하세요:");
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
			case 0:
				saveMemo();
				return;	
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}
		}
	}

	// 메모 추가하기
	private void add() {
		String title = nextLine("제목: ");
		String content = nextLine("내용: ");
		memos.add(new Memo(nextNo++, title, content));
		System.out.println("메모 추가 및 저장 완료");
		System.out.println("-".repeat(30));
		saveMemo();
	}

	// 메모 수정하기
	private void modify() {
		if (memos.isEmpty()) {
			System.out.println("수정할 메모기록이 없습니다");
			return;
		}

		showMemo();
		int no = nextInt("수정할 메모장 번호를 입력하세요");
		Memo memo = findBy(no);
		if (memo == null) {
			System.out.println("해당 번호의 메모장이 없습니다");
			return;
		}

		String title = nextLine("제목 (" + memo.getTitle() + "): ");
		String content = nextLine("내용 (" + memo.getContent() + "): ");

		if (!title.isEmpty()) {
			memo.setTitle(title);
		}
		if (!content.isEmpty()) {
			memo.setContent(content);
		}

		System.out.println("메모 기록이 수정되었습니다.");
		saveMemo();
	}

	// 메모 삭제하기
	private void delete() {
		if (memos.isEmpty()) {
			System.out.println("삭제할 메모기록이 없습니다");
			return;
		}

		showMemo();
		int no = nextInt("삭제할 메모장 번호를 입력하세요: ");
		Memo memo = findBy(no);
		if (memo == null) {
			System.out.println("해당 번호의 메모장이 없습니다");
			return;
		}

		if (nextConfirm("정말 삭제하시겠습니까? ")) {
			memos.remove(memo);
			System.out.println("메모장이 삭제되었습니다");
			saveMemo();
		} else {
			System.out.println("메모장 삭제가 취소되었습니다");
		}
	}

	private void saveMemo() {
		saveData(memos, "storage/memos/memos_log.ser");
	}

	private void loadMemo() {
		loadData("storage/memos/memos_log.ser");
		List<Memo> loadMemos = loadData("storage/memos/memos_log.ser");

		if (loadMemos != null) {
			this.memos = loadMemos;
			if (!memos.isEmpty()) {
				nextNo = memos.get(memos.size() - 1).getNo() + 1;
			}
			System.out.println("저장된 메모 (" + memos.size() + "개)를 성공적으로 불러왔습니다.");
		}else {
			this.memos = new ArrayList<>(); // 파일이 없거나 실패한 경우에도 빈 리스트로 초기화
			System.out.println("불러올 메모가 없어 새 메모장을 시작합니다.");
		}

	}
	
	public void showMemo() {
		if (memos.isEmpty()) {
			System.out.println("메모가 비어있습니다");
			return;
		}

		for (int i = 0; i < memos.size(); i++) {
			Memo m = memos.get(i);
			System.out.printf(" %d번 메모장 | <제목>  %s | <내용>  %s\n <작성 날짜> %s\n", m.getNo(), m.getTitle(), m.getContent(),
					m.getDate());
		}
	}

	private Memo findBy(int no) {
		for (int i = 1; i < memos.size(); i++) {
			if (memos.get(i).getNo() == no) {
				return memos.get(i);
			}
		}
		return null;
	}
}
