import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		UserDao userDao = new UserDao();
		User[] users = userDao.getAll();
		for (User user : users) {
			System.out.println(user);
		}
		while (true) {
			System.out.println("");
			System.out.println("Wybierz jedną z opcji:");
			System.out.println("add - dodanie użytkownika");
			System.out.println("edit - edycja użytkownika");
			System.out.println("delete - usunięcie użytkownika:");
			System.out.println("quit - zakończenie programu");

			Scanner scan = new Scanner(System.in);
			String userAsk = scan.nextLine();

			if (userAsk.equals("quit")) break;

			switch (userAsk) {
				case "add":
					System.out.println("dodaj");
					break;
				case "edit":
					System.out.println("edytuj");
					break;
				case "delete":
					System.out.println("usuń");
					break;
				default:
					System.out.println("Nie ma takiej opcji");
			}
		}
	}
}

