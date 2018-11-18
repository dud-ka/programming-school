package Manage;

import Model.Exercise;
import Model.ExerciseDao;
import Model.Solution;
import Model.SolutionDao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class UserIdManage {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Witaj! Wprowadź swój identyfikator");
		int userId = Integer.parseInt(scan.nextLine());

		SolutionDao solutionDao = new SolutionDao();
		Solution[] solutions = solutionDao.getAll();

		while (true) {

			System.out.println("");
			System.out.println("Wybierz jedną z opcji:");
			System.out.println("add - dodawanie rozwiązania");
			System.out.println("view - przeglądanie swoich rozwiązań");
			System.out.println("quit - wyjście z programu");

			String userAsk = scan.nextLine();

			if (userAsk.equals("quit")) break;

			switch (userAsk) {
				case "add":
					ExerciseDao exerciseDao = new ExerciseDao();
					Exercise[] exercises = exerciseDao.getExercisesWithoutSolutionByUserId(userId);

					for (Exercise exercise : exercises) {
						System.out.println(exercise);
					}

					System.out.println("Podaj id zadania do którego chcesz dodać rozwiązanie");
					int exerciseId = Integer.parseInt(scan.nextLine());

					System.out.println("Podaj opis rozwiązania");
					String description = scan.nextLine();
					Timestamp timeNow = Timestamp.valueOf(LocalDateTime.now());

					solutionDao.create(new Solution(0, timeNow, null, description, exerciseId, userId));

					break;
				case "view":

					Solution[] solutions1 = solutionDao.getByUserId(userId);
					for (Solution solution : solutions1) {
						System.out.println(solution);
					}
					break;

				default:
					System.out.println("Nie ma takiej opcji");
			}
		}
	}
}
