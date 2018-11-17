import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//	loadAll
//	loadById
//	delete
//	saveToDB
public class ExerciseDao {

	private static final String QUERY_SELECT = "SELECT * from exercise where id=?;";
	private static final String CREATE_QUERY = "INSERT INTO exercise(title,description) VALUES (?,?);";
	private static final String ALL_EXERCISES_QUERY = "SELECT * FROM exercise";
	private static final String DELETE_QUERY = "DELETE FROM exercise WHERE id = ?;";
	private static final String UPDATE_QUERY = "UPDATE exercise SET title = ?, description = ? WHERE id = ?;";

//	=============== SELECT ===============

	public Exercise getById(long searchId) {
		Exercise exercise = null;
		try (Connection conn = DbUtil.getConnection("school");
		     PreparedStatement stat = conn.prepareStatement(QUERY_SELECT);
		) {
			stat.setLong(1, searchId);
			try (ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String description = rs.getString("description");
					exercise = new Exercise(id, title, description);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exercise;
	}

//  =============== CREATE ===============

	public Exercise exercise(Exercise exercise) {
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement insertStm = connection.prepareStatement(CREATE_QUERY,
				     PreparedStatement.RETURN_GENERATED_KEYS)) {
			insertStm.setString(1, exercise.getTitle());
			insertStm.setString(2, exercise.getDescription());
			int result = insertStm.executeUpdate();

			if (result != 1) {
				throw new RuntimeException("Execute update returned " + result);
			}

			try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
				if (generatedKeys.first()) {
					exercise.setId(generatedKeys.getInt(1));
					return exercise;
				} else {
					throw new RuntimeException("Generated key was not found");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return null;
	}

//  =============== SHOW ALL ===============

	public Exercise[] findAll() {
		List<Exercise> exerciseList = new ArrayList<>();
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement statement = connection.prepareStatement(ALL_EXERCISES_QUERY);
		     ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				Exercise exerciseToAdd = new Exercise();
				exerciseToAdd.setId(resultSet.getInt("id"));
				exerciseToAdd.setTitle(resultSet.getString("title"));
				exerciseToAdd.setDescription(resultSet.getString("description"));
				exerciseList.add(exerciseToAdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

		Exercise[] eArray = new Exercise[exerciseList.size()]; eArray = exerciseList.toArray(eArray);
		return eArray;

	}


//  =============== DELETE ===============

	public void delete(Integer exerciseID) {
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);) {
			statement.setInt(1, exerciseID);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
	}


//  =============== UPDATE ===============

public void update(Exercise exercise) {
	try (Connection connection = DbUtil.getConnection("school");
	     PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {
		statement.setLong(3, exercise.getId());
		statement.setString(1, exercise.getTitle());
		statement.setString(2, exercise.getDescription());

		statement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Cos sie nie powiodło");
	}
}

}


//	loadAll
//	loadById
//	delete
//	saveToDB