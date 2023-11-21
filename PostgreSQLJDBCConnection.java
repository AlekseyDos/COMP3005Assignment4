import java.util.Scanner;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLJDBCConnection {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		boolean programState = true;
		
		// JDBC & Database credentials
		String url = "jdbc:postgresql://<HOST>:<PORT>/<DATABASE_NAME>";
        String user = "<USERNAME>";
        String password = "<PASSWORD>";
		
		try { 
			
			// Load PostgreSQL JDBC Driver
			Class.forName("org.postgresql.Driver" );
			
			// Connect to the database
			Connection conn = DriverManager.getConnection(url, user, password);
			
			if (conn != null) {
				
				System.out.println("Connected to PostgreSQL successfully!\n");
				
				//Presents user with command list and waits for user input
				System.out.println("Please input one of the following commands:\n"
						+ "Get All Students\n"
						+ "Add Student\n"
						+ "Update Student Email\n"
						+ "Delete Student\n"
						+ "Exit");
				String userCommand = keyboard.nextLine();
				System.out.println();
				
				while(programState) {
					
					switch(userCommand) {
						
						//Retrieves and displays all records from the students table
						case "Get All Students":
							
							getAllStudents(conn);
							
							//Presents user with command list and waits for user input
							System.out.println("Please enter another command:\n"
									+ "Get All Students\n"
									+ "Add Student\n"
									+ "Update Student Email\n"
									+ "Delete Student\n"
									+ "Exit");
							userCommand = keyboard.nextLine();
							System.out.println();
							
							break;
						
						//Inserts a new student record into the students table
						case "Add Student":
							
							//Collects the necessary information from the user
							System.out.println("Please enter the student's first name:");
							String stdFN = keyboard.nextLine();
							System.out.println();
							
							System.out.println("Please enter the student's last name:");
							String stdLN = keyboard.nextLine();
							System.out.println();
							
							System.out.println("Please enter the student's email:");
							String stdEM = keyboard.nextLine();
							System.out.println();
							
							//Ensures that the value provided by the user is in an appropriate Date format
							Date stdED = null;
							boolean invalidDate;
							do {
								
								invalidDate = false;
								try {
									
									System.out.println("Please enter the student's enrollment date (example: YYYY-MM-DD):");
									stdED = Date.valueOf(keyboard.nextLine());
							    	System.out.println();
							    }
							    
							    catch (Exception e) {
							      
							    	invalidDate = true;
							    	System.out.println("\nINVALID DATE");
							    }
							} while (invalidDate);
							
							addStudnet(conn, stdFN, stdLN, stdEM, stdED);
							
							//Presents user with command list and waits for user input
							System.out.println("Please enter another command:\n"
									+ "Get All Students\n"
									+ "Add Student\n"
									+ "Update Student Email\n"
									+ "Delete Student\n"
									+ "Exit");
							userCommand = keyboard.nextLine();
							System.out.println();
							
							break;
						//Updates the email address for a student with the specified student_id
						case "Update Student Email":
							
							//Collects the necessary information from the user
							//Ensures that the value provided by the user is an integer
							int stdUpdateID = 0;
							boolean invalidUpdateID;
							do {
								
								invalidUpdateID = false;
								try {
									
									
									System.out.println("Please enter the student's ID:");
									stdUpdateID = Integer.parseInt(keyboard.nextLine()); 
									System.out.println();	
							    }
							    
							    catch (Exception e) {

							    	invalidUpdateID = true;
							    	System.out.println("\nINVALID ID");
							    }
							} while (invalidUpdateID);
							
							System.out.println("Please enter the student's new email:");
							String stdNewEM = keyboard.nextLine();
							System.out.println();
							
							updateStudentEmail(conn, stdUpdateID, stdNewEM);
							
							//Presents user with command list and waits for user input
							System.out.println("Please enter another command:\n"
									+ "Get All Students\n"
									+ "Add Student\n"
									+ "Update Student Email\n"
									+ "Delete Student\n"
									+ "Exit");
							userCommand = keyboard.nextLine();
							System.out.println();
							
							break;
							
						//Deletes the record of the student with the specified student_id
						case "Delete Student":
							
							//Collects the necessary information from the user
							//Ensures that the value provided by the user is an integer
							int stdDeleteID = 0;
							boolean invalidDeleteID;
							do {
								
								invalidDeleteID = false;
								try {
									
									System.out.println("Please enter the student's ID:");
									stdDeleteID = Integer.parseInt(keyboard.nextLine()); 
									System.out.println();	
							    }
							    
							    catch (Exception e) {

							    	invalidDeleteID = true;
							    	System.out.println("\nINVALID ID");
							    }
							} while (invalidDeleteID);
							
							deleteStudent(conn, stdDeleteID);
							
							//Presents user with command list and waits for user input
							System.out.println("Please enter another command:\n"
									+ "Get All Students\n"
									+ "Add Student\n"
									+ "Update Student Email\n"
									+ "Delete Student\n"
									+ "Exit");
							userCommand = keyboard.nextLine();
							System.out.println();
							
							break;
						//Ends the while loop and allows the program to close resources
						case "Exit":
							
							programState = false;
							
							break;
							
						default:
							
							//Presents user with command list and waits for user input
							System.out.println("Please enter a valid command:\n"
									+ "Get All Students\n"
									+ "Add Student\n"
									+ "Update Student Email\n"
									+ "Delete Student\n"
									+ "Exit");
							userCommand = keyboard.nextLine();
							System.out.println();
					}
				}
			} 
			
			else {
				
				System.out.println( "Failed to establish connection." );
			} 
			
			// Close the connection
			conn.close();
			keyboard.close();
		}
		
		catch (ClassNotFoundException | SQLException e) { 
				
			System.out.println(e.getMessage());
			System.out.println("\nPROGRAM SHUTTING DOWN\n"); 
		}
	}

	//Retrieves and displays all records from the students table
	static void getAllStudents(Connection conn) {
		
		try {
			
			//Create the statement
			Statement stmt = conn.createStatement();
			String SQL = "SELECT * FROM Students";
			
			//Executes the statement and collects results
			ResultSet rs = stmt.executeQuery(SQL);
			
			//Iterates through the results and presents them to the user
			while(rs.next()) {
			
				int student_id = rs.getInt("student_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String enrollment_date = rs.getString("enrollment_date");
				System.out.println("Student ID: " + student_id + ", Name: " + first_name + " " + last_name + ", Email: " + email + ", Enrollment Date: " + enrollment_date);
			}
			
			System.out.println();
		
			// Close resources
			rs.close();
			stmt.close();
			
			System.out.println("Query Executed Successfully\n");
		}
		
		catch (SQLException e) {
			
			//Posts the SQL Error message
			System.out.println(e.getMessage());
			System.out.println("\nQuery Not Executed\n");
		}
	}
	
	//Inserts a new student record into the students table
	static void addStudnet(Connection conn, String first_name, String last_name, String email, Date enrollment_date) {
		
		//Creates the SQL INSERT operation string
		String insertSQL = "INSERT INTO Students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";
		
		//Adds the user's input to the string and executes the operation
		try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
		
			pstmt.setString(1, first_name);
			pstmt.setString(2, last_name);
			pstmt.setString(3, email);
			pstmt.setDate(4, enrollment_date);
			pstmt.executeUpdate();
			
			System.out.println("Query Executed Successfully\n");
		}
		
		catch (SQLException e) {
			
			//Posts the SQL Error message
			System.out.println(e.getMessage());
			System.out.println("\nQuery Not Executed\n");
		}
	}
	
	//Updates the email address for a student with the specified student_id
	static void updateStudentEmail(Connection conn, int student_id, String new_email) {
		
		//Creates the SQL UPDATE operation string
		String updateSQL = "UPDATE Students SET email = ? WHERE student_id = ?";

		//Adds the user's input to the string and executes the operation
		try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
		
			pstmt.setString(1, new_email);
			pstmt.setInt(2, student_id);
			pstmt.executeUpdate();
			
			System.out.println("Query Executed Successfully\n");
		}
		
		catch (SQLException e) { 
			
			//Posts the SQL Error message
			System.out.println(e.getMessage());
			System.out.println("\nQuery Not Executed\n"); 
		}
	}
	
	//Deletes the record of the student with the specified student_id
	static void deleteStudent(Connection conn, int student_id) {
		
		//Creates the SQL DELETE operation string
		String deleteSQL = "DELETE FROM Students WHERE student_id = ?";

		//Adds the user's input to the string and executes the operation
		try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
		
			pstmt.setInt(1, student_id);
			pstmt.executeUpdate();
			
			System.out.println("Query Executed Successfully\n");
		}
		
		catch (SQLException e) {
			
			//Posts the SQL Error message
			System.out.println(e.getMessage());
			System.out.println("\nQuery Not Executed\n");
		}
	}
}
