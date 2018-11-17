import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Solution {

	int id;
	Timestamp created;
	Timestamp updated;
	String description;

	public Solution(int id, Timestamp created, Timestamp updated, String description) {
		this.id = id;
		this.created = created;
		this.updated = updated;
		this.description = description;
	}

	public Solution() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
