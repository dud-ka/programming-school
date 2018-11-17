package Model;

import Service.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserGroupDao {

	private static final String QUERY_SELECT = "SELECT * from user_group where id=?;";
	private static final String CREATE_QUERY = "INSERT INTO user_group(name) VALUES (?);";
	private static final String ALL_EXERCISES_QUERY = "SELECT * FROM user_group";
	private static final String DELETE_QUERY = "DELETE FROM user_group WHERE id = ?;";
	private static final String UPDATE_QUERY = "UPDATE user_group SET name = ? WHERE id = ?;";


	public UserGroup create(UserGroup userGroup) {
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement insertStm = connection.prepareStatement(CREATE_QUERY,
				     PreparedStatement.RETURN_GENERATED_KEYS)) {
			insertStm.setString(1, userGroup.getName());
			int result = insertStm.executeUpdate();

			if (result != 1) {
				throw new RuntimeException("Execute update returned " + result);
			}

			try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
				if (generatedKeys.first()) {
					userGroup.setId(generatedKeys.getInt(1));
					return userGroup;
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


	public UserGroup getById(int searchId) {
		UserGroup userGroup = null;
		try (Connection conn = DbUtil.getConnection("school");
		     PreparedStatement stat = conn.prepareStatement(QUERY_SELECT)
		) {
			stat.setInt(1, searchId);
			try (ResultSet rs = stat.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					userGroup = new UserGroup(id, name);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userGroup;
	}


	public UserGroup[] getAll() {
		List<UserGroup> userGroupList = new ArrayList<>();
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement statement = connection.prepareStatement(ALL_EXERCISES_QUERY);
		     ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				UserGroup toAdd = new UserGroup();
				toAdd.setId(resultSet.getInt("id"));
				toAdd.setName(resultSet.getString("name"));
				userGroupList.add(toAdd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

		UserGroup[] array = new UserGroup[userGroupList.size()];
		array = userGroupList.toArray(array);
		return array;

	}

	public void update(UserGroup userGroup) {
		try (Connection connection = DbUtil.getConnection("school");
		     PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);) {
			statement.setInt(2, userGroup.getId());
			statement.setString(1, userGroup.getName());
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
