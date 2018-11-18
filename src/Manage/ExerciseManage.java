package Manage;

import Model.Exercise;
import Model.ExerciseDao;

import java.util.Scanner;

public class ExerciseManage {
	public static void main(String[] args) {


		while (true) {
			ExerciseDao exerciseDao = new ExerciseDao();
			Exercise[] exercises = exerciseDao.getAll();
			for (Exercise exercise : exercises) {
				System.out.println(exercise);
			}

			System.out.println("");
			System.out.println("Wybierz jedną z opcji:");
			System.out.println("add - dodanie zadania");
			System.out.println("edit - edycja zadania");
			System.out.println("delete - usunięcie zadania:");
			System.out.println("quit - zakończenie programu");

			Scanner scan = new Scanner(System.in);
			String userAsk = scan.nextLine();

			if (userAsk.equals("quit")) break;

			switch (userAsk) {
				case "add":
					System.out.println("Podaj tytuł zadania");
					String addTitle = scan.nextLine();
					System.out.println("Podaj opis zadania");
					String addDesc = scan.nextLine();
					exerciseDao.create(new Exercise(0,addTitle,addDesc));
					break;
				case "edit":
					System.out.println("Podaj id zadania");
					int editId = Integer.parseInt(scan.nextLine());
					System.out.println("Podaj tytuł zadania");
					String editTitle = scan.nextLine();
					System.out.println("Podaj opis zadania");
					String editDesc = scan.nextLine();
					exerciseDao.update(new Exercise(editId,editTitle,editDesc));
					break;
				case "delete":
					System.out.println("Podaj id zadania");
					int deleteId = Integer.parseInt(scan.nextLine());
					exerciseDao.delete(deleteId);
					break;
				default:
					System.out.println("Nie ma takiej opcji");
			}
		}
	}
}
