package app;

import static util.TrioUtils.nextInt;
import static util.TrioUtils.nextLine;

import domain.DeviceInfo;

public class Info {
	
	private boolean loginInfo = false;
	private boolean RegistInfo = false;
	
	
	private static DeviceInfo Info = new DeviceInfo();
	private static final Info INSTANCE = new Info();
	private Info() {} // 외부에서 생성 불가

	public static Info getInstance() {
		return INSTANCE;
	}
	
	
	public void accountMenu() {
		while (true) {
			int no = nextInt("사용할기능 선택 1. 사용자 확인 2.사용자 정보 수정 3.로그아웃 4.사용자 제거");
			switch (no) {
			case 1:
				checkInfo();
				break;
			case 2:
				editInfo();
				break;
			case 3:
				logOut();
				return;
			case 4:
				deleteInfo();
				return;
			case 0:
				System.out.println("1 번");
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + no);
			}
		}
	}

	public void checkInfo() {
		System.out.println(Info.toString());
	}

	public void editInfo() {
		int no = nextInt("수정할 정보를 선택해주세요 1.이름 2. 비밀번호 3. 이메일 4.휴대폰 번호 0취소");
		switch (no) {
		case 1: 
			String name = nextLine("사용자 이름을 입력해주세요");
			Info.setUserName(name);
			break;
		case 2: 
			String pw = nextLine("비밀번호를 입력해주세요");
			checkPw(pw);
			Info.setDeivcePw(pw);
			break;
		case 3: 
			String email = nextLine("이메일을 입력해주세요");
			checkEmail(email);
			Info.setUserEmail(email);
			break;
		case 4: 
			String tel = nextLine("휴대폰 번호를 입력해주세요");
			checkTel(tel);
			Info.setTel(tel);
			break;
		case 0: 
			System.out.println("이전메뉴로 되돌아갑니다");
			return;
		default:
			throw new IllegalArgumentException("Unexpected value: " + no);
		}
	}

	public void register() { // 가입기능, 최초싱행시 가입이 안되어있으면 실행
		if(isRegistInfo()) {
			System.out.println("이미 등록된 계정이 있습니다");
			return;
		}
		
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
		RegistInfo = true;
	}

	public void logIn() {
		String pw = nextLine("비밀번호를 입력해주세요");
		checkPw(pw); //null 예외처리
		
		if(Info.getDeivcePw().equals(pw)) {
			setLoginInfo(true);
			return;
		}
	}

	public void logOut() {
		if(!isLoginInfo()) {
			System.out.println("이미 로그아웃중입니다");
			return;
		}
		setLoginInfo(false);
		
	}

	public void deleteInfo() {
		if(!isRegistInfo()) {
			System.out.println("가입된 계정이 없습니다");
			return;
		}
		
		Info.setUserName(null);
		Info.setDeivcePw(null);
		Info.setTel(null);
		System.out.println("사용자 정보가 제거 되었습니다");
		setLoginInfo(false);
		setRegistInfo(false);
		return;	
	}

	private void checkTel(String tel) {
		String s = "01[0-9]-[0-9]{3,4}-[0-9]{4}";
		if (!tel.matches(s)) {
			throw new IllegalArgumentException("옳바른 전화번호를 입력해주세요");
		}
	}

	private void checkEmail(String email) {
		String s = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
		if (!email.matches(s)) {
			throw new IllegalArgumentException("옳바른 형식의 이메일을 입력해주세요");
		}
	}

	private void checkPw(String pw) {
		String s = "^\\S{4,}$";
		if (!pw.matches(s)) {
			throw new IllegalArgumentException("비밀번호는 공백을 포함하지 않은 4글자 이상이어야합니다");
		}
	}
	
	public boolean isLoginInfo() {
		return loginInfo;
	}
	public boolean isRegistInfo() {
		return RegistInfo;
	}
	public void setLoginInfo(boolean loginInfo) {
		this.loginInfo = loginInfo;
	}
	public void setRegistInfo(boolean registInfo) {
		RegistInfo = registInfo;
	}
	
	
	
}
