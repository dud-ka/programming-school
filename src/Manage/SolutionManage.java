package Manage;

import Model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class SolutionManage {
	public static void main(String[] args) {


		UserDao userDao = new UserDao();
		User[] users = userDao.getAll();

		ExerciseDao exerciseDao = new ExerciseDao();
		Exercise[] exercises = exerciseDao.getAll();

		while (true) {
			SolutionDao solutionDao = new SolutionDao();
			Solution[] solutions = solutionDao.getAll();

			System.out.println("Wszystkie zadania");
			for(Solution solution : solutions) {
				System.out.println(solution);
			}
			System.out.println("");
			System.out.println("Wybierz jedną z opcji:");
			System.out.println("add - dodanie rozwiązania");
			System.out.println("view - podgląd rozwiązań");
			System.out.println("quit - zakończenie programu");

			Scanner scan = new Scanner(System.in);
			String userAsk = scan.nextLine();

			if (userAsk.equals("quit")) break;

			switch (userAsk) {
				case "add":
					for(User user : users) {
						System.out.println("ID UŻYTKOWNIKA: " + user.getId() + " NAZWA UŻYTKOWNIKA: " + user.getUsername());
					}
					System.out.println("Podaj id użytkownika");
					int addUserId = Integer.parseInt(scan.nextLine());

					for (Exercise exercise : exercises) {
						System.out.println("ID ZADANIA: " + exercise.getId() + " ZADANIE: " + exercise.getTitle());
					}
					System.out.println("Podaj id zadania");
					int addExerciseId = Integer.parseInt(scan.nextLine());

					Timestamp timeNow = Timestamp.valueOf(LocalDateTime.now());
					solutionDao.create(new Solution(0, timeNow, null, null, addExerciseId, addUserId));
					break;
				case "view":
					for(User user : users) {
						System.out.println(user);
					}
					System.out.println("Podaj id użytkownika, którego rozwiązania chcesz zobaczyć");
					int viewId = Integer.parseInt(scan.nextLine());

					Solution[] solutionByUserId = solutionDao.getByUserId(viewId);
					for (Solution solution : solutionByUserId) {
						System.out.println(solution);
					}
					System.out.println("");
					System.out.println("");

					break;
				default:
					System.out.println("Nie ma takiej opcji");
			}
		}
	}
}
