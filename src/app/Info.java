package app;

import static util.TrioUtils.*;

import java.io.Serializable;

import domain.DeviceInfo;
import main.Main;

public class Info implements Serializable{

	private DeviceInfo loginInfo = null; // 비 로그인시 null

//	private static Main main = new Main();

	private DeviceInfo Info = new DeviceInfo(); // 기기 정보 객체 호출

	private static Info INSTANCE = new Info();

	private Info() {
	} // 외부에서 생성 불가

	public static Info getInstance() { // 유일한 객체
		return INSTANCE;
	}

	public void accountMenu() {
		while (true) {
			int no = nextInt("사용할기능 선택\n"
					+ " (1) 사용자 확인\n"
					+ " (2) 사용자 정보 수정\n"
					+ " (3) 로그아웃\n"
					+ " (4) 사용자 제거\n"
					+ "	(0) 종료");
			switch (no) {
			case 1:
				checkInfo();
				break;
			case 2:
				editInfo(getloginInfo());
				break;
			case 3:
				logOut();
				if(getloginInfo() == null) {return;}
				break;
			case 4:
				deleteUserData();
				if(getloginInfo() == null) {return;}
				break;
			case 0:
				System.out.println("(홈 화면으로 되돌아갑니다)");
				return;
			}
		}
	}

	public DeviceInfo getloginInfo() { // 해당 객체를 호출해서 로그인 여부 확인
		return this.loginInfo;

	}

	public boolean loadUserData() {
		if (loadInfo("storage/system/userData") != null) {
			this.Info = loadInfo("storage/system/userData");
			return true;
		}else {
			return false;
		}

	}
	

	public void checkInfo() {
		System.out.println(Info.toString());
	}

	public void editInfo(DeviceInfo loginInfo) {
		if(loginInfo == null) {
			throw new NullPointerException("로그인 상태가 아닙니다");
		}
		
		int no = nextInt("수정할 정보를 선택해주세요 :\n"
				+ " (1) 이름\n"
				+ " (2) 비밀번호\n"
				+ " (3) 이메일\n"
				+ " (4) 휴대폰 번호\n"
				+ " (0) 취소");
		switch (no) {
		case 1:
			String name = nextLine("사용자 이름을 입력해주세요");
			loginInfo.setUserName(name);
			Info = loginInfo;
			saveInfo(Info, "storage/system/userData");
			break;
		case 2:
			String pw = nextLine("비밀번호를 입력해주세요");
			checkPw(pw);
			loginInfo.setDeivcePw(pw);
			Info = loginInfo;
			saveInfo(Info, "storage/system/userData");
			break;
		case 3:
			String email = nextLine("이메일을 입력해주세요");
			checkEmail(email);
			loginInfo.setUserEmail(email);
			Info = loginInfo;
			saveInfo(Info, "storage/system/userData");
			break;
		case 4:
			String tel = nextLine("휴대폰 번호를 입력해주세요");
			checkTel(tel);
			loginInfo.setTel(tel);
			Info = loginInfo;
			saveInfo(Info, "storage/system/userData");
			break;
		case 0:
			System.out.println("(이전메뉴로 되돌아갑니다)");
			return;
		default:
			throw new IllegalArgumentException("Unexpected value: " + no);
		}
	}

	public void register() { // 가입기능, 최초싱행시 가입이 안되어있으면 실행

		String name = nextLine("사용자 이름을 입력해주세요");
		String pw = nextLine("비밀번호를 입력해주세요");
		checkPw(pw);

		String email = nextLine("이메일을 입력해주세요");
		checkEmail(email);
		String tel = nextLine("휴대폰 번호를 입력해주세요");
		checkTel(tel);

		Info.setUserName(name);
		Info.setDeivcePw(pw);
		Info.setUserEmail(email);
		Info.setTel(tel);
		saveInfo(Info, "storage/system/userData");

	}

	public void logIn() {
		String pw = nextLine("비밀번호를 입력해주세요");
		checkPw(pw); // null 예외처리

		if (Info.getDeivcePw().equals(pw)) {
			this.loginInfo =Info;
			return;
		}
	}

	public void logOut() {

		if (nextConfirm("로그아웃 하시겠습니까 y/n")) {
			this.loginInfo =null;
		}
	}

	public void deleteUserData() {

		if (nextConfirm("사용자를 정말 삭제시겠습니까? y/n")) {
			Info.setUserName(null);
			Info.setDeivcePw(null);
			Info.setTel(null);
			System.out.println("사용자 정보가 제거 되었습니다");
			deleteInfo("storage/system/userData");
			this.loginInfo =null;
			return;
		}
	}

	private void checkTel(String tel) {
		String s = "01[0-9]-[0-9]{3,4}-[0-9]{4}";
		if (!tel.matches(s)) {
			throw new IllegalArgumentException("옳바른 전화번호를 입력해주세요 \n 처음부터 다시 시도해주세요");
		}
	}

	private void checkEmail(String email) {
		String s = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
		if (!email.matches(s)) {
			throw new IllegalArgumentException("옳바른 형식의 이메일을 입력해주세요 \n 처음부터 다시 시도해주세요");
		}
	}

	private void checkPw(String pw) {
		String s = "^\\S{4,}$";
		if (!pw.matches(s)) {
			throw new IllegalArgumentException("비밀번호는 공백을 포함하지 않은 4글자 이상이어야합니다\n 처음부터 다시 시도해주세요");
		}
	}

}
