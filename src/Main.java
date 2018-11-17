import java.awt.print.Book;

public class Main {
	public static void  main(String[] args) {
		ExerciseDao dao = new ExerciseDao();
//		Exercise exercise = dao.getById(4);
//		exercise.setTitle("nowynowy");
//		exercise.setDescription("kolejne trudne zadanie");
//
//		dao.update(exercise);


		for (Exercise ex : dao.findAll()) {
			System.out.println(ex);
		}

	}
}
