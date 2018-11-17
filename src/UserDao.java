import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

	private static final String QUERY_SELECT = "SELECT * from users where id=?;";
	private static final String CREATE_QUERY = "INSERT INTO users(username, email, password, userGroupId) VALUES (?,?,?,?);";
	private static final String ALL_EXERCISES_QUERY = "SELECT * FROM users";
	private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?;";
	private static final String UPDATE_QUERY = "UPDATE users SET username = ?, email = ?, password = ?, userGroupId = ? WHERE id = ?;";
	private static final String SELECT_ALL_BY_GROUP_ID = "SELECT * from users where user_group_id = ?;";


	public User create(User user) {
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement insertStm = connection.prepareStatement(CREATE_QUERY,
				     PreparedStatement.RETURN_GENERATED_KEYS)) {
			insertStm.setString(1, user.getUsername());
			insertStm.setString(2, user.getEmail());
			insertStm.setString(3, user.getPassword());
			insertStm.setInt(4, user.getUserGroupId());
			int result = insertStm.executeUpdate();

			if (result != 1) {
				throw new RuntimeException("Execute update returned " + result);
			}

			try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
				if (generatedKeys.first()) {
					user.setId(generatedKeys.getInt(1));
					return user;
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


	public User[] getAll() {
		List<User> userList = new ArrayList<>();
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement statement = connection.prepareStatement(ALL_EXERCISES_QUERY);
		     ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				User toAdd = new User();
				toAdd.setId(resultSet.getInt("id"));
				toAdd.setUsername(resultSet.getString("username"));
				toAdd.setEmail(resultSet.getString("email"));
				toAdd.setPassword(resultSet.getString("password"));
				toAdd.setUserGroupId(resultSet.getInt("user_group_id"));
				userList.add(toAdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

		User[] array = new User[userList.size()];
		array = userList.toArray(array);
		return array;
	}

	public User getById(int searchId) {
		User user = null;
		try (Connection conn = DbUtil.getConnection("school");
		     PreparedStatement stat = conn.prepareStatement(QUERY_SELECT);
		) {
			stat.setInt(1, searchId);
			try (ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String username = rs.getString("username");
					String email = rs.getString("email");
					String password = rs.getString("password");
					int userGroupId = rs.getInt("user_group_id");
					user = new User(id, username, email, password, userGroupId);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User[] getByGroupId(int searchId) {
		List<User> userList = new ArrayList<>();
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_GROUP_ID);)
		{
			statement.setInt(1, searchId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				User toAdd = new User();
				toAdd.setId(resultSet.getInt("id"));
				toAdd.setUsername(resultSet.getString("username"));
				toAdd.setEmail(resultSet.getString("email"));
				toAdd.setPassword(resultSet.getString("password"));
				toAdd.setUserGroupId(resultSet.getInt("user_group_id"));
				userList.add(toAdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

		User[] array = new User[userList.size()];
		array = userList.toArray(array);
		return array;
	}


	public void update(User user) {
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {
			statement.setInt(5, user.getId());
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
			statement.setInt(4, user.getUserGroupId());

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
