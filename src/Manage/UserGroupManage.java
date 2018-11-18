package Manage;

import Model.UserGroup;
import Model.UserGroupDao;

import java.util.Scanner;

public class UserGroupManage {
	public static void main(String[] args) {

		while (true) {
			UserGroupDao userGroupDao = new UserGroupDao();
			UserGroup[] userGroups = userGroupDao.getAll();
			for (UserGroup userGroup : userGroups) {
				System.out.println(userGroup);
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
					System.out.println("Podaj nazwę grupy");
					String addName = scan.nextLine();
					userGroupDao.create(new UserGroup(0, addName));
					break;
				case "edit":
					System.out.println("Podaj id grupy");
					int editId = Integer.parseInt(scan.nextLine());
					System.out.println("Podaj nazwę grupy");
					String editName = scan.nextLine();
					userGroupDao.update(new UserGroup(editId,editName));
					break;
				case "delete":
					System.out.println("Podaj id grupy");
					int deleteId = Integer.parseInt(scan.nextLine());
					userGroupDao.delete(deleteId);
					break;
				default:
					System.out.println("Nie ma takiej opcji");
			}
		}
	}
}
