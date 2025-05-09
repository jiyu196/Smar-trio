package service.apps;

import utils.TrioUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.*;
import java.util.*;

import domain.apps.ContactApp;

@SuppressWarnings("unchecked")
public class ContactAppService {

//  ContactAppService service = ContactAppService.getInstance();
	private static final Path CONTACT_PATH = Path.of("storage", "contacts", "contacts.ser");

	private final List<ContactApp> contacts = new ArrayList<>();
	private static final ContactAppService contactAppService = new ContactAppService();
	private int nextNo = 0;

	public ContactAppService() {
		loadContacts();
	}

	public static ContactAppService getInstance() {
		return contactAppService;
	}

	public void run() {

		while (true) {
			System.out.println("--- 연락처 메뉴 ---");
			System.out.println("" + "1. 연락처 추가\n" + "2. 연락처 수정\n" + "3. 연락처 삭제\n" + "4. 연락처 보기\n" + "5. 돌아가기");
			int choice = TrioUtils.nextInt("옵션을 선택하세요:");
			switch (choice) {
			case 1:
				addContact();
				break;
			case 2:
				modifyContact();
				break;
			case 3:
				deleteContact();
				break;
			case 4:
				showContacts();
				break;
			case 5:
				saveContacts();
				return;
			default:
				System.out.println("잘못된 선택입니다.");
				break;
			}
		}
	}

	private void addContact() {
		String name = TrioUtils.nextLine("이름: ");
		String phone = TrioUtils.nextLine("전화번호 (010-1234-5678): ");
		if (!phone.matches("^01[0-9]-\\d{3,4}-\\d{4}$")) {
			System.out.println("전화번호 형식이 올바르지 않습니다.");
			return;
		}
		String email = TrioUtils.nextLine("이메일: ");
		if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
			System.out.println("이메일 형식이 올바르지 않습니다.");
			return;
		}
		String nickname = TrioUtils.nextLine("별명: ");

		contacts.add(new ContactApp(nextNo++, name, phone, email, nickname));
		System.out.println("연락처가 추가되었습니다.");
		saveContacts();
	}

	private void modifyContact() {
		if (contacts.isEmpty()) {
			System.out.println("수정할 연락처가 없습니다.");
			return;
		}

		showContacts();
		int no = TrioUtils.nextInt("수정할 연락처 번호: ");
		ContactApp contact = findByNo(no);
		if (contact == null) {
			System.out.println("해당 번호의 연락처가 없습니다.");
			return;
		}

		String name = TrioUtils.nextLine("이름 (" + contact.getName() + "): ");
		String phone = TrioUtils.nextLine("전화번호 (" + contact.getPhone() + "): ");
		String email = TrioUtils.nextLine("이메일 (" + contact.getEmail() + "): ");
		String nickname = TrioUtils.nextLine("별명 (" + contact.getNickname() + "): ");

		if (!name.isEmpty())
			contact.setName(name);
		if (!phone.isEmpty())
			contact.setPhone(phone);
		if (!email.isEmpty())
			contact.setEmail(email);
		if (!nickname.isEmpty())
			contact.setNickname(nickname);

		System.out.println("연락처가 수정되었습니다.");
		saveContacts();
	}

	private void showContacts() {
		if (contacts.isEmpty()) {
			System.out.println("등록된 연락처가 없습니다.");
			return;
		}

		for (ContactApp c : contacts) {
			System.out.printf("번호: %d | 이름: %s | 전화: %s | 이메일: %s | 별명: %s\n", 
					c.getNo(), c.getName(), c.getPhone(),
					c.getEmail(), c.getNickname());
		}
	}

	private void loadContacts() {
		contacts.clear();
		if (!Files.exists(CONTACT_PATH)) {
			System.out.println("파일이 존재하지 않습니다: " + CONTACT_PATH);
			return;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CONTACT_PATH.toFile()));
			List<ContactApp> loaded = (List<ContactApp>) ois.readObject();
			ois.close();
			
			for (int i = 0; i < loaded.size(); i++) {
				ContactApp m = loaded.get(i);
				contacts.add(m);
				if (m.getNo() >= nextNo) {
					nextNo = m.getNo() + 1;
				}
			}
			System.out.println("메모 로드 완료 (" + contacts.size() + "개)");
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("연락처 로드 실패: " + e.getMessage());
		}
	}

	private void saveContacts() {
		try {
			Files.createDirectories(CONTACT_PATH.getParent());
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONTACT_PATH.toFile()));
			oos.writeObject(contacts);
			oos.close();
		} catch (IOException e) {
			System.err.println("연락처 저장 실패: " + e.getMessage());
		}
	}
	
	private void deleteContact() {
		if (contacts.isEmpty()) {
			System.out.println("삭제할 연락처가 없습니다.");
			return;
		}
		
		showContacts();
		int no = TrioUtils.nextInt("삭제할 연락처 번호: ");
		ContactApp contact = findByNo(no);
		if (contact == null) {
			System.out.println("해당 번호의 연락처가 없습니다.");
			return;
		}
		
		if (TrioUtils.nextConfirm("정말 삭제하시겠습니까? (y/n): ")) {
			contacts.remove(contact);
			System.out.println("연락처가 삭제되었습니다.");
		} else {
			System.out.println("삭제가 취소되었습니다.");
		}
	}

	private ContactApp findByNo(int no) {
		for (ContactApp c : contacts) {
			if (c.getNo() == no)
				return c;
		}
		return null;
	}
}