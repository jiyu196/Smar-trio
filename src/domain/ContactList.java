package domain;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import utils.TrioUtils;

public class ContactList {
	public static void main(String[] args) {
		ContactList app = new ContactList();
		app.run();
	}

	private static final Path CONTACT_PATH = Path.of("storage", "contacts", "contacts.txt");
    private List<Contact> contacts = new ArrayList<>();

    public ContactList() {
        loadContacts();
    }

    public void run() {
        while (true) {
            System.out.println("--- 연락처 메뉴 ---");
            System.out.println(""
            		+ "1. 연락처 추가\n"
            		+ "2. 연락처 수정\n"
            		+ "3. 연락처 삭제\n"
            		+ "4. 연락처 보기\n"
            		+ "5. 돌아가기");
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
        String phone = TrioUtils.nextLine("전화번호: ");
        String email = TrioUtils.nextLine("이메일: ");
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
			System.err.println("\nIncorrect Format > ex. (JohnSmith@Hotmail.com)");
			return;
        }
        String nickname = TrioUtils.nextLine("별명: ");

        contacts.add(new Contact(name, phone, email, nickname));
        saveContacts();
        System.out.println("연락처가 추가되고 저장되었습니다.");
    }

    private void modifyContact() {
        if (contacts.isEmpty()) {
            System.out.println("수정할 연락처가 없습니다.");
            return;
        }

        showContacts();
        int index = TrioUtils.nextInt("수정할 번호를 입력하세요:");

        if (index < 0 || index >= contacts.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }

        Contact old = contacts.get(index);
        String name = updateField("이름", old.name);
        String phone = updateField("전화번호", old.phone);
        String email = updateField("이메일", old.email);
        String nickname = updateField("별명", old.nickname);

        contacts.set(index, new Contact(name, phone, email, nickname));
        saveContacts();
        System.out.println("연락처가 수정되고 저장되었습니다.");
    }

    private void deleteContact() {
        if (contacts.isEmpty()) {
            System.out.println("삭제할 연락처가 없습니다.");
            return;
        }

        showContacts();
        int index = TrioUtils.nextInt("삭제할 번호를 입력하세요:");

        if (index < 0 || index >= contacts.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }

        boolean confirm = TrioUtils.nextConfirm("이 연락처를 삭제하시겠습니까? (y/n)");
        if (confirm) {
            contacts.remove(index);
            saveContacts();
            System.out.println("연락처가 삭제되고 저장되었습니다.");
        } else {
            System.out.println("삭제가 취소되었습니다.");
        }
    }

    private void showContacts() {
        if (contacts.isEmpty()) {
            System.out.println("연락처가 없습니다.");
            return;
        }

        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            System.out.printf("%d: %s\n", i, c.name);
//            System.out.printf("%d: %s, %s, %s, %s%n", i, c.name, c.phone, c.email, c.nickname);
        }
    }

    private void saveContacts() {
        List<String> saveContact = new ArrayList<>();
        for (Contact c : contacts) {
        	saveContact.add(c.toString());
        }

        try {
        	Files.createDirectories(CONTACT_PATH.getParent());
        	Files.write(CONTACT_PATH, saveContact);
            System.out.println("연락처가 저장되었습니다: " + CONTACT_PATH.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("연락처 저장에 실패했습니다: " + e.getMessage());
        }
    }

    private void loadContacts() {
        if (!Files.exists(CONTACT_PATH)) return;

        try {
            List<String> saveContact = Files.readAllLines(CONTACT_PATH);
            for (String saveContacts : saveContact) {
                Contact c = Contact.fromString(saveContacts);
                if (c != null) contacts.add(c);
            }
        } catch (IOException e) {
            System.err.println("연락처를 불러올 수 없습니다: " + e.getMessage());
        }
    }

    private String updateField(String field, String current) {
        String input = TrioUtils.nextLine("새로운 " + field + " (" + current + "): ");
        return input.isEmpty() ? current : input;
    }

    private static class Contact {
        String name;
        String phone;
        String email;
        String nickname;

        Contact(String name, String phone, String email, String nickname) {
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.nickname = nickname;
        }

        public String toString() {
            return String.join(";", name, phone, email, nickname);
        }

        public static Contact fromString(String line) {
            String[] parts = line.split(";");
            if (parts.length == 4) {
                return new Contact(parts[0], parts[1], parts[2], parts[3]);
            }
            return null;
        }
    }

	public static Object getInstance() {
		return null;
	}
}
