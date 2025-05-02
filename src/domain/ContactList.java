package domain;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import Utils.TrioUtils;

public class ContactList {
	public static void main(String[] args) {
		ContactList app = new ContactList();
		app.run();
}
    private static final String FILE_NAME = "contacts.txt";
    private List<Contact> contacts = new ArrayList<>();

    public ContactList() {
        loadContacts();
    }

    public void run() {
        while (true) {
            System.out.println("--- 연락처 메뉴 ---");
            System.out.println("1. 연락처 추가\\n2. 연락처 수정\\n3. 연락처 삭제\\n4. 연락처 보기\\n5. 돌아가기");
            int choice = TrioUtils.nextInt("Choose an option:");
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
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private void addContact() {
        String name = TrioUtils.nextLine("Name: ");
        String phone = TrioUtils.nextLine("Phone: ");
        String email = TrioUtils.nextLine("Email: ");
        String nickname = TrioUtils.nextLine("Nickname: ");

        contacts.add(new Contact(name, phone, email, nickname));
        saveContacts();
        System.out.println("Contact added and saved.");
    }


    private void modifyContact() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to modify.");
            return;
        }

        showContacts();
        int index = TrioUtils.nextInt("Enter index to modify:");

        if (index < 0 || index >= contacts.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Contact old = contacts.get(index);
        String name = updateField("name", old.name);
        String phone = updateField("phone", old.phone);
        String email = updateField("email", old.email);
        String nickname = updateField("nickname", old.nickname);

        contacts.set(index, new Contact(name, phone, email, nickname));
        saveContacts();
        System.out.println("Contact modified and saved.");
    }


    private void deleteContact() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to delete.");
            return;
        }

        showContacts();
        int index = TrioUtils.nextInt("Enter index to delete:");

        if (index < 0 || index >= contacts.size()) {
            System.out.println("Invalid index.");
            return;
        }

        boolean confirm = TrioUtils.nextConfirm("Are you sure you want to delete this contact? (y/n)");
        if (confirm) {
            contacts.remove(index);
            saveContacts();
            System.out.println("Contact deleted and saved.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }


    private void showContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            System.out.printf("%d: %s, %s, %s, %s%n", i, c.name, c.phone, c.email, c.nickname);
        }
    }

    private void saveContacts() {
        List<String> lines = new ArrayList<>();
        for (Contact c : contacts) {
            lines.add(c.toString());
        }

        try {
            Path path = Path.of(FILE_NAME);
            Files.write(path, lines);
            System.out.println("Contacts saved to " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save contacts: " + e.getMessage());
        }
    }

    private void loadContacts() {
        try {
            List<String> lines = Files.readAllLines(Path.of(FILE_NAME));
            for (String line : lines) {
                Contact c = Contact.fromString(line);
                if (c != null) contacts.add(c);
            }
        } catch (IOException e) {
            System.err.println("Could not load contacts: " + e.getMessage());
        }
    }

    private String updateField(String field, String current) {
        String input = TrioUtils.nextLine("New " + field + " (" + current + "): ");
        return input.isEmpty() ? current : input;
    }

    // class 내부
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
