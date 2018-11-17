import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao {

	private static final String QUERY_SELECT = "SELECT * from solution where id=?;";
	private static final String CREATE_QUERY = "INSERT INTO solution(created, updated, description, exercise_id, user_id) VALUES (?,?,?,?,?);";
	private static final String ALL_EXERCISES_QUERY = "SELECT * FROM solution";
	private static final String DELETE_QUERY = "DELETE FROM solution WHERE id = ?;";
	private static final String UPDATE_QUERY = "UPDATE solution SET created = ?, updated = ?, description = ?, exercise_id = ?, user_id = ? WHERE id = ?;";
	private static final String SELECT_ALL_BY_EXERCISE_ID = "SELECT * FROM solution WHERE exercise_id = ?;";
	private static final String SELECT_ALL_BY_USER_ID = "SELECT * FROM solution WHERE user_id = ?;";



	public Solution create(Solution solution) {
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement insertStm = connection.prepareStatement(CREATE_QUERY,
				     PreparedStatement.RETURN_GENERATED_KEYS)) {
			insertStm.setTimestamp(1, solution.getCreated());
			insertStm.setTimestamp(2, solution.getUpdated());
			insertStm.setString(3, solution.getDescription());
			insertStm.setInt(4, solution.getExerciseId());
			insertStm.setInt(5, solution.getUserId());
			int result = insertStm.executeUpdate();

			if (result != 1) {
				throw new RuntimeException("Execute update returned " + result);
			}

			try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
				if (generatedKeys.first()) {
					solution.setId(generatedKeys.getInt(1));
					return solution;
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


	public Solution[] getAll() {
		List<Solution> solutionList = new ArrayList<>();
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement statement = connection.prepareStatement(ALL_EXERCISES_QUERY);
		     ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				Solution toAdd = new Solution();
				toAdd.setId(resultSet.getInt("id"));
				toAdd.setCreated(resultSet.getTimestamp("created"));
				toAdd.setUpdated(resultSet.getTimestamp("updated"));
				toAdd.setDescription(resultSet.getString("description"));
				toAdd.setExerciseId(resultSet.getInt("exercise_id"));
				toAdd.setUserId(resultSet.getInt("user_id"));
				solutionList.add(toAdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

		Solution[] array = new Solution[solutionList.size()];
		array = solutionList.toArray(array);
		return array;

	}

	public Solution getById(int searchId) {
		Solution solution = null;
		try (Connection conn = DbUtil.getConnection("school");
		     PreparedStatement stat = conn.prepareStatement(QUERY_SELECT);
		) {
			stat.setInt(1, searchId);
			try (ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					Timestamp created = rs.getTimestamp("created");
					Timestamp updated = rs.getTimestamp("updated");
					String description = rs.getString("description");
					int userId = rs.getInt("user_id");
					int exerciseId = rs.getInt("exercise_id");
					solution = new Solution(id, created, updated, description, exerciseId, userId);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return solution;
	}

	public Solution[] getByExerciseId(int searchId) {
		List<Solution> solutionList = new ArrayList<>();
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_EXERCISE_ID);
		     ) {
			statement.setInt(1, searchId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Solution toAdd = new Solution();
				toAdd.setId(resultSet.getInt("id"));
				toAdd.setCreated(resultSet.getTimestamp("created"));
				toAdd.setUpdated(resultSet.getTimestamp("updated"));
				toAdd.setDescription(resultSet.getString("description"));
				toAdd.setExerciseId(resultSet.getInt("exercise_id"));
				toAdd.setUserId(resultSet.getInt("user_id"));
				solutionList.add(toAdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

		Solution[] array = new Solution[solutionList.size()];
		array = solutionList.toArray(array);
		return array;
	}


	public Solution[] getByUserId(int searchId) {
		List<Solution> solutionList = new ArrayList<>();
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_USER_ID);
		) {
			statement.setInt(1, searchId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Solution toAdd = new Solution();
				toAdd.setId(resultSet.getInt("id"));
				toAdd.setCreated(resultSet.getTimestamp("created"));
				toAdd.setUpdated(resultSet.getTimestamp("updated"));
				toAdd.setDescription(resultSet.getString("description"));
				toAdd.setExerciseId(resultSet.getInt("exercise_id"));
				toAdd.setUserId(resultSet.getInt("user_id"));
				solutionList.add(toAdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

		Solution[] array = new Solution[solutionList.size()];
		array = solutionList.toArray(array);
		return array;
	}




	public void update(Solution solution) {
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {
			statement.setInt(6, solution.getId());
			statement.setTimestamp(1, solution.getCreated());
			statement.setTimestamp(2, solution.getUpdated());
			statement.setString(3, solution.getDescription());
			statement.setInt(4, solution.getExerciseId());
			statement.setInt(5, solution.getUserId());

			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
	}


	public void delete(Integer id) {
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
	}


}
