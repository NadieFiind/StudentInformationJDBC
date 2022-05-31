import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Database db = new Database("student_information.db");
		Scanner scanner = new Scanner(System.in);
		db.connect();
		
		System.out.println("=== Student Information System ===");
		System.out.println("1. Add Record");
		System.out.println("2. View Records");
		System.out.println("3. Edit Record");
		System.out.println("4. Delete Record");
		System.out.println("5. Quit");
		
		while (true) {
			System.out.print("\nSelect Option > ");
			String input = scanner.nextLine();
			String id;
			String name;
			String course;
			String school;
			String address;
			String email;
			String mobile;
			
			switch (input) {
				case "1":
					System.out.println("You selected option 1: Add Record\n");
					
					System.out.print("Student ID: ");
					id = scanner.nextLine();
					
					System.out.print("Name: ");
					name = scanner.nextLine();
					
					System.out.print("Course: ");
					course = scanner.nextLine();
					
					System.out.print("School name: ");
					school = scanner.nextLine();
					
					System.out.print("Address: ");
					address = scanner.nextLine();
					
					System.out.print("Email address: ");
					email = scanner.nextLine();
					
					System.out.print("Mobile number: ");
					mobile = scanner.nextLine();
					
					db.addRecord(id, name, course, school, address, email, mobile);
					break;
				case "2":
					System.out.println("You selected option 2: View Records");
					ArrayList<HashMap<String, String>> records = db.getRecords();
					
					System.out.println(String.format(
						"%16s%16s%16s%16s%16s%16s%16s",
						"Student ID",
						"Name",
						"Course",
						"School Name",
						"Address",
						"Email Addresss",
						"Mobile Number"
					));
					
					for (int i = 0; i < records.size(); i++) {
						System.out.println(String.format(
							"%16s%16s%16s%16s%16s%16s%16s",
							records.get(i).get("id"),
							records.get(i).get("name"),
							records.get(i).get("course"),
							records.get(i).get("school"),
							records.get(i).get("address"),
							records.get(i).get("email"),
							records.get(i).get("mobile")
						));
					}
					
					break;
				case "3":
					System.out.println("You selected option 3: Edit Record\n");
					
					System.out.print("Student ID: ");
					id = scanner.nextLine();
					
					System.out.print("Name: ");
					name = scanner.nextLine();
					
					System.out.print("Course: ");
					course = scanner.nextLine();
					
					System.out.print("School name: ");
					school = scanner.nextLine();
					
					System.out.print("Address: ");
					address = scanner.nextLine();
					
					System.out.print("Email address: ");
					email = scanner.nextLine();
					
					System.out.print("Mobile number: ");
					mobile = scanner.nextLine();
					
					db.updateRecord(id, name, course, school, address, email, mobile);
					break;
				case "4":
					System.out.println("You selected option 4: Delete Record\n");
					
					System.out.print("Student ID: ");
					id = scanner.nextLine();
					
					db.deleteRecord(id);
					break;
				case "5":
					System.out.println("You selected option 5: Quit");
					db.close();
					System.exit(1);
					break;
				default:
					System.out.println("Invalid input. Select only 1-5.");
			}
		}
	}
}
