import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;

@SuppressWarnings("unchecked")

public class Database {
	String path;
	Connection conn;
	
	Database(String path) {
		this.path = path;
	}
	
	public void connect() {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + path);
		} catch (SQLException error) {
			error.printStackTrace();
		}
		
		executeUpdate("CREATE TABLE IF NOT EXISTS students (" +
			"id TEXT PRIMARY KEY," +
			"name TEXT," +
			"course TEXT," +
			"school TEXT," +
			"address TEXT," +
			"email TEXT," +
			"mobile TEXT" +
		");");
	}
	
	public void close() {
		try {
			if (conn != null) conn.close();
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}
	
	public void executeUpdate(String query) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query) {
		ResultSet rs = null;
		
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException error) {
			error.printStackTrace();
		}
		
		return rs;
	}
	
	public ArrayList<HashMap<String, String>> getRecords() {
		ArrayList<HashMap<String, String>> records = new ArrayList();
		ResultSet rs = executeQuery("SELECT * FROM students");
		
		try {
			while (rs.next()) {
				records.add(new HashMap<String, String>() {{
					put("id", rs.getString("id"));
					put("name", rs.getString("name"));
					put("course", rs.getString("course"));
					put("school", rs.getString("school"));
					put("address", rs.getString("address"));
					put("email", rs.getString("email"));
					put("mobile", rs.getString("mobile"));
				}});
			}
		} catch (SQLException error) {
			error.printStackTrace();
		}
		
		return records;
	}
	
	public void addRecord(String id, String name, String course, String school, String address, String email, String mobile) {
		executeUpdate(
			"INSERT INTO students (id, name, course, school, address, email, mobile)" +
			String.format(
				"VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
				id, name, course, school, address, email, mobile
			)
		);
		
		System.out.println("Record sucessfully added!");
	}
	
	public void updateRecord(String id, String name, String course, String school, String address, String email, String mobile) {
		executeUpdate(
			"UPDATE students SET " +
				String.format("name = '%s',", name) +
				String.format("course = '%s',", course) +
				String.format("school = '%s',", school) +
				String.format("address = '%s',", address) +
				String.format("email = '%s',", email) +
				String.format("mobile = '%s'", mobile) +
			"WHERE id = '" + id + "';"
		);
		
		System.out.println("Record sucessfully updated!");
	}
	
	public void deleteRecord(String id) {
		executeUpdate(String.format("DELETE FROM students WHERE id = '%s';", id));
		System.out.println("Record sucessfully deleted!");
	}
}
