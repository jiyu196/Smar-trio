package service.apps;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import domain.apps.MemoApp;
import utils.TrioUtils;

public class MemoService {
	public static void main(String[] args) {
		MemoService.getInstance().run();
	}

	private static final Path MEMO_PATH = Path.of("storage", "memos", "memos.txt");

	private final List<MemoApp> memos = new ArrayList<>();
	private static final MemoService memoService = new MemoService();
	private int nextNo = 0;

	public MemoService() {
		load();
	}

	public static MemoService getInstance() {
		return memoService;
	}

	// 추가, 수정, 삭제, 저장, 기록
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

	// 메모 추가
	private void add() {
		String title = TrioUtils.nextLine("제목: ");
		String content = TrioUtils.nextLine("내용: ");
		memos.add(new MemoApp(nextNo++, title, content));
		System.out.println("메모 추가 및 저장 완료");
		save();

	}

	// 메모 수정
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

		if (!title.isEmpty())
			memo.setTitle(title);
		if (!content.isEmpty())
			memo.setContent(content);

		System.out.println("연락처가 수정되었습니다.");
		save();
	}

	// 메모 삭제
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
		} else {
			System.out.println("메모장 삭제가 취소되었습니다");
		}

	}

	// 메모 저장
	private void save() {
		try {
			Files.createDirectories(MEMO_PATH.getParent());
			List<String> lines = new ArrayList<>();
			for (MemoApp m : memos) {
				lines.add(m.toString());
			}
			Files.write(MEMO_PATH, lines);
		} catch (IOException e) {
			System.err.println("메모장 저장 실패: " + e.getMessage());
		}
	}

	private void load() {
	    memos.clear();
	    if (!Files.exists(MEMO_PATH)) {
	        System.out.println("메모 파일이 존재하지 않습니다: " + MEMO_PATH);
	        return;
	    }

	    try {
	        List<String> lines = Files.readAllLines(MEMO_PATH);
	        System.out.println("로드한 메모 수: " + lines.size());
	        for (String line : lines) {
	            MemoApp m = MemoApp.fromString(line);
	            if (m != null) {
	                memos.add(m);
	                nextNo = Math.max(nextNo, m.getNo() + 1);
	            }
	        }
	    } catch (IOException e) {
	        System.err.println("메모장 로드 실패: " + e.getMessage());
	    }
	}

	// 메모 기록 보기
	public void showMemo() {
		if (memos.isEmpty()) {
			System.out.println("메모가 비어있습니다");
			return;
		}
		
		for (MemoApp m : memos) {
			System.out.printf("번호: %d | 제목: %s | 내용: %s\n",
					m.getNo(), m.getTitle(), m.getContent());
		}
	}

	private MemoApp findBy(int no) {
		for (MemoApp m : memos) {
			if (m.getNo() == no) {
				return m;
			}
		}
		return null;
	}
}
