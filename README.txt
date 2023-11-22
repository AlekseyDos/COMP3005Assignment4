Name: Aleksey Dosov

Operating System: Windows 11

Youtube Link: https://youtu.be/zQ_Z0aJ9ick

Table Creation Instructions: 
Create a new database inside postgres using pgAdmin4 and name it "Student Enrollment"
Open the SQL Tool in Student Enrollment and execute the SQL script provided in "StudentEnrollmentCreationScript.txt"

Application Launching Instructions:
Inside PostgreSQLJDBCConnection.java, input your host, port, and DB name in String url = "jdbc:postgresql://<HOST>:<PORT>/<DATABASE_NAME>";
Inside PostgreSQLJDBCConnection.java, input your postgres database username in String user = "<USERNAME>";
Inside PostgreSQLJDBCConnection.java, input your postgres database password in String password = "<PASSWORD>";
You may now run PostgreSQLJDBCConnection.java and connect to your database

main:
Launches the application and handles user input

getAllStudents: 
Retrieves and displays all records from the students table

addStudent:
Inserts a new student record into the students table

updateStudentEmail: 
Updates the email address for a student with the specified student_id

deleteStudent:
Deletes the record of the student with the specified student_id.

Notes:
The commands are case-sensitive
You'll need the JDBC for postgreSQL which can be found here: https://jdbc.postgresql.org/download/
