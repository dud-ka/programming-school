package Manage;

import Model.User;
import Model.UserDao;
import Model.UserGroup;
import Model.UserGroupDao;

import java.util.Scanner;

public class UsersManage {
	public static void main(String[] args) {
		UserGroupDao userGroupDao= new UserGroupDao();
		UserGroup[] userGroups = userGroupDao.getAll();
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
					System.out.println("Podaj id grupy użytkownika. Dostępne grupy:");

					for (UserGroup group: userGroups){
						System.out.println(group);
					}

					int addGroupId = Integer.parseInt(scan.nextLine());
					userDao.create(new User(0, addName, addPassword, addEmail, addGroupId));
					break;
				case "edit":
					System.out.println("Podaj id użytkownika");
					int editId = Integer.parseInt(scan.nextLine());
					System.out.println("Podaj nazwę użytkownika");
					String editName = scan.nextLine();
					System.out.println("Podaj hasło użytkownika");
					String editPassword = scan.nextLine();
					System.out.println("Podaj email użytkownika");
					String editEmail = scan.nextLine();
					System.out.println("Podaj id grupy użytkownika. Dostępne grupy:");

					for (UserGroup group: userGroups){
						System.out.println(group);
					}
					int editGroupId = Integer.parseInt(scan.nextLine());
					userDao.update(new User(editId, editName, editPassword, editEmail, editGroupId));
					break;
				case "delete":
					System.out.println("Podaj id użytkownika");
					int deleteId = Integer.parseInt(scan.nextLine());
					userDao.delete(deleteId);
					break;
				default:
					System.out.println("Nie ma takiej opcji");
			}
		}
	}
}

