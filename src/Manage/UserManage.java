package Manage;

import Model.User;
import Model.UserDao;

import java.util.Scanner;

public class UserManage {
	public static void main(String[] args) {

		while (true) {
			UserDao userDao = new UserDao();
			User[] users = userDao.getAll();
			for (User user : users) {
				System.out.println(user);
			}

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
					System.out.println("Podaj nazwę użytkownika");
					String addName = scan.nextLine();
					System.out.println("Podaj hasło użytkownika");
					String addPassword = scan.nextLine();
					System.out.println("Podaj email użytkownika");
					String addEmail = scan.nextLine();
					break;
				case "edit":
					System.out.println("Podaj id użytkownika");
					String editId = scan.nextLine();
					System.out.println("Podaj nazwę użytkownika");
					String editName = scan.nextLine();
					System.out.println("Podaj hasło użytkownika");
					String editPassword = scan.nextLine();
					System.out.println("Podaj email użytkownika");
					String editEmail = scan.nextLine();
					break;
				case "delete":
					System.out.println("Podaj id użytkownika");
					String deleteId = scan.nextLine();
					break;
				default:
					System.out.println("Nie ma takiej opcji");
			}
		}
	}
}

