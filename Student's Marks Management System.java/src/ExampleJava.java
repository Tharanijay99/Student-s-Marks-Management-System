import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Example {
    //-----------------clearConsole------------------------------------------------

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }

    //----------To check the current ID whether it is existing or not---------

    public static boolean isDuplicateID(List<String[]> studentList, String id) {
        for (String[] studentDetails : studentList) {
            if (studentDetails[0].equals(id)) {
                return true;
            }
        }
        return false;
    }

    //-------------Add a New student ---------------------------------------------------

    public static void addNewStudent(List<String[]> studentList) {
        System.out.println("---------------------------------------------------------");
        System.out.println("\t\t  ADD NEW STUDENT");
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println();

        Scanner input = new Scanner(System.in);

        System.out.print("Enter Student ID   :");
        String studentId = input.nextLine();

        boolean isDuplicateID = isDuplicateID(studentList, studentId);
        while (isDuplicateID) {
            System.out.println("The Student ID already exists");
            System.out.print("Enter Student ID   :");
            studentId = input.nextLine();
            isDuplicateID = isDuplicateID(studentList, studentId);
        }

        System.out.print("Enter Student Name :");
        String studentName = input.nextLine();

        String[] studentDetails = new String[]{studentId, studentName};
        studentList.add(studentDetails);

        System.out.print("Student has been added successfully. Do you want to add a new student (Y/n)? ");
        String choice = input.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            clearConsole();
            addNewStudent(studentList);

        } else if (choice.equalsIgnoreCase("n")) {
            clearConsole();
            printHomePage(studentList);
        }
    }

    public static void printHomePage(List<String[]> studentList) {
        System.out.println("---------------------------------------------------------");
        System.out.println("\tWELCOME TO GDSE MARKS MANAGEMENT SYSTEM");
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("[1]  Add New Student\t\t\t [2]  Add New Student With Marks");
        System.out.println("[3]  Add Marks\t\t\t\t [4]  Update Student Details");
        System.out.println("[5]  Update Marks\t\t\t [6]  Delete Student");
        System.out.println("[7]  Print Student Details\t\t [8]  Print Student Ranks");
        System.out.println("[9]  Best in Programming Fundamentals\t [10] Best in Database Management System");

        System.out.println();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an option to continue >");
        final int N = input.nextInt();

        if (N == 1) {
            clearConsole();
            addNewStudent(studentList);
        }else if (N == 2) {
        clearConsole();
        addNewStudentWithMarks(studentList);
    }else if (N == 3) {
    addMarks(studentList);

    }else if (N == 4) {
    updateStudentDetails(studentList);
    }else if (N == 5) {
        updateStudentMarks(studentList);
    }else if ( N == 6){
        deleteStudent(studentList);
    }else if (N == 7) {
        printStudentDetails(studentList);
    }else if ( N == 8){
        printStudentRanks(studentList);
    }else if (N == 9) {
    clearConsole();
    printBestInProgramming(studentList);
    }else if (N == 10) {
    clearConsole();
    printBestInDatabase(studentList);
    }
    }
   public static void addNewStudentWithMarks(List<String[]> studentList) {
    System.out.println("---------------------------------------------------------");
    System.out.println("\t\t  ADD NEW STUDENT WITH MARKS");
    System.out.println("---------------------------------------------------------");
    System.out.println();
    System.out.println();

    Scanner input = new Scanner(System.in);

    System.out.print("Enter Student ID   :");
    String studentId = input.nextLine();

    boolean isDuplicateID = isDuplicateID(studentList, studentId);
    while (isDuplicateID) {
        System.out.println("The Student ID already exists");
        System.out.print("Enter Student ID   :");
        studentId = input.nextLine();
        isDuplicateID = isDuplicateID(studentList, studentId);
    }

    System.out.print("Enter Student Name :");
    String studentName = input.nextLine();

    int marks1 = 0;
    int marks2 = 0;
    boolean isInvalidMarks1 = true;
    boolean isInvalidMarks2 = true;

    while (isInvalidMarks1) {
        System.out.print("Programming fundamentals mark :");
        try {
            marks1 = input.nextInt();
            if (marks1 < 0 || marks1 > 100) {
                throw new Exception("Invalid marks!");
            }
            isInvalidMarks1 = false;
        } catch (Exception e) {
            System.out.println("Invalid marks! Please enter marks between 0 and 100.");
            input.nextLine(); // consume the remaining new line character
        }
    }

    input.nextLine(); // consume the remaining new line character

    while (isInvalidMarks2) {
        System.out.print("Database Management Systems mark :");
        try {
            marks2 = input.nextInt();
            if (marks2 < 0 || marks2 > 100) {
                throw new Exception("Invalid marks!");
            }
            isInvalidMarks2 = false;
        } catch (Exception e) {
            System.out.println("Invalid marks! Please enter marks between 0 and 100.");
            input.nextLine(); // consume the remaining new line character
        }
    }

    input.nextLine(); // consume the remaining new line character

    String[] studentDetails = new String[]{studentId, studentName, String.valueOf(marks1), String.valueOf(marks2)};
    studentList.add(studentDetails);

    System.out.print("Student has been added successfully. Do you want to add a new student (Y/n)? ");
    String choice = input.nextLine();

    if (choice.equalsIgnoreCase("Y")) {
        clearConsole();
        addNewStudent(studentList);

    } else if (choice.equalsIgnoreCase("n")) {
        clearConsole();
        printHomePage(studentList);
    }
}

    
    public static void addMarks(List<String[]> studentList) {
    System.out.println("---------------------------------------------------------");
    System.out.println("\t\t  ADD MARKS");
    System.out.println("---------------------------------------------------------");
    System.out.println();
    System.out.println();

    Scanner input = new Scanner(System.in);

    System.out.print("Enter Student ID   :");
    String studentId = input.nextLine();

    boolean studentFound = false;
    String[] studentDetails = null;

    while (!studentFound) {
        boolean isDuplicateID = false;

        for (String[] s : studentList) {
            if (s[0].equals(studentId)) {
                isDuplicateID = true;
                studentDetails = s;
                break;
            }
        }

        if (!isDuplicateID) {
            System.out.println("Student ID not found!");
            System.out.print("Do you want to continue searching? (Y/n) ");
            String choice = input.nextLine();

            if (choice.equalsIgnoreCase("n")) {
                clearConsole();
                printHomePage(studentList);
            } else {
                System.out.print("Enter Student ID   :");
                studentId = input.nextLine();
            }

        } else if (studentDetails.length == 5) {
            System.out.println("Student marks for two subjects already added!");
            System.out.print("If you want to update the marks, please use [4] update marks option.");
            System.out.println("Do you want to add marks for another student? (Y/n)");
            String choice = input.nextLine();

            if (choice.equalsIgnoreCase("n")) {
                clearConsole();
                printHomePage(studentList);
            } else {
                System.out.print("Enter Student ID   :");
                studentId = input.nextLine();
            }

        } else {
            studentFound = true;
            System.out.println("Student found: " + studentDetails[1]);
        }
    }

    int marksSubject1 = 0;
    int marksSubject2 = 0;
    boolean isInvalidMarks = true;

    while (isInvalidMarks) {
        System.out.print("Programming fundamentals mark :");
        try {
            marksSubject1 = Integer.parseInt(input.nextLine());

            if (marksSubject1 < 0 || marksSubject1 > 100) {
                System.out.println("Invalid marks! Marks should be between 0 and 100.");
                continue;
            }

            System.out.print("Database Management Systems mark :");
            marksSubject2 = Integer.parseInt(input.nextLine());

            if (marksSubject2 < 0 || marksSubject2 > 100) {
                System.out.println("Invalid marks! Marks should be between 0 and 100.");
                continue;
            }

            isInvalidMarks = false;

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number between 0 and 100.");
        }
    }

    studentDetails = Arrays.copyOf(studentDetails, 5);
    studentDetails[2] = String.valueOf(marksSubject1);
    studentDetails[3] = String.valueOf(marksSubject2);
    System.out.println("Marks added for student " + studentDetails[1]);

    System.out.print("Do you want to add marks for another student? (Y/n)");
    String choice = input.nextLine();

    if (choice.equalsIgnoreCase("Y")) {
        clearConsole();
        addMarks(studentList);

    } else if (choice.equalsIgnoreCase("n")) {
        clearConsole();
        printHomePage(studentList);
    }
}

    public static void updateStudentDetails(List<String[]> studentList) {
    System.out.println("---------------------------------------------------------");
    System.out.println("\t\t  UPDATE STUDENT DETAILS");
    System.out.println("---------------------------------------------------------");
    System.out.println();
    System.out.println();

    Scanner input = new Scanner(System.in);

    System.out.print("Enter Student ID: ");
    String studentId = input.nextLine();

    int index = -1;

    for (int i = 0; i < studentList.size(); i++) {
        String[] studentDetails = studentList.get(i);

        if (studentDetails[0].equals(studentId)) {
            index = i;
            break;
        }
    }

    if (index == -1) {
        System.out.println("Student ID not found!");
        return;
    }

    String[] studentDetails = studentList.get(index);
    String currentName = studentDetails[1];

    System.out.println("Current Student Name: " + currentName);

    System.out.print("Enter New Student Name: ");
    String newName = input.nextLine();

    studentDetails[1] = newName;

    System.out.println("Student details have been updated successfully.");

    System.out.print("Do you want to update another student's details (Y/n)? ");
    String choice = input.nextLine();

    if (choice.equalsIgnoreCase("Y")) {
        clearConsole();
        updateStudentDetails(studentList);

    } else if (choice.equalsIgnoreCase("n")) {
        clearConsole();
        printHomePage(studentList);
    }
}
public static void updateStudentMarks(List<String[]> studentList) {
    Scanner input = new Scanner(System.in);

    System.out.println("---------------------------------------------------------");
    System.out.println("\t\t  UPDATE STUDENT MARKS");
    System.out.println("---------------------------------------------------------");
    System.out.println();
    System.out.println();
    
    System.out.print("Enter Student ID   :");
    String studentId = input.nextLine();

    boolean isStudentFound = false;
    for (String[] studentDetails : studentList) {

        if (studentDetails[0].equals(studentId)) {
            isStudentFound = true;
             if (studentDetails.length < 3) {
                System.out.println("This student's marks yet to be added");
                 System.out.print("Do you want to update marks for another student (Y/n)? ");
                String choice = input.nextLine();

    if (choice.equalsIgnoreCase("Y")) {
        clearConsole();
        updateStudentMarks(studentList);

    } else if (choice.equalsIgnoreCase("n")) {
        clearConsole();
        printHomePage(studentList);
    }
                return;
             }
             int marks1 = Integer.parseInt(studentDetails[2]);
            int marks2 = Integer.parseInt(studentDetails[3]);

            System.out.print("Programming Fundamentals Mark (" + marks1 + ") : ");
            int newMarks1 = input.nextInt();
            System.out.print("Database Management System Mark (" + marks2 + ") : ");
            int newMarks2 = input.nextInt();


            input.nextLine(); // consume the newline character

            // Check if the index is within the bounds of the array before accessing it
            if (studentDetails.length > 3) {
                studentDetails[2] = String.valueOf(marks1);
                studentDetails[3] = String.valueOf(marks2);
            } else {
                studentDetails = Arrays.copyOf(studentDetails, 4);
                studentDetails[2] = String.valueOf(marks1);
                studentDetails[3] = String.valueOf(marks2);
            }

            System.out.println("Marks updated successfully");
            break;
        }
    }

    if (!isStudentFound) {
        System.out.println("Student not found!");
    }

    System.out.print("Do you want to update marks for another student (Y/n)? ");
    String choice = input.nextLine();

    if (choice.equalsIgnoreCase("Y")) {
        clearConsole();
        updateStudentMarks(studentList);

    } else if (choice.equalsIgnoreCase("n")) {
        clearConsole();
        printHomePage(studentList);
    }
}

 
   public static void deleteStudent(List<String[]> studentList) {
        System.out.println("---------------------------------------------------------");
        System.out.println("\t\t  DELETE STUDENT");
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println();

        Scanner input = new Scanner(System.in);

        System.out.print("Enter Student ID to delete :");
        String studentId = input.nextLine();

        boolean isFound = false;
        for (String[] studentDetails : studentList) {
            if (studentDetails[0].equals(studentId)) {
                studentList.remove(studentDetails);
                isFound = true;
                System.out.println("Student with ID " + studentId + " has been deleted successfully.");
                break;
            }
        }

        if (!isFound) {
            System.out.println("Student ID not found!");
            System.out.print("Do you want to continue searching? (Y/n) ");
             String choice = input.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            clearConsole();
            deleteStudent(studentList);

        } else if (choice.equalsIgnoreCase("n")) {
            clearConsole();
            printHomePage(studentList);
        }
        }

        System.out.print("Do you want to delete another student (Y/n)? ");
        String choice = input.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            clearConsole();
            deleteStudent(studentList);

        } else if (choice.equalsIgnoreCase("n")) {
            clearConsole();
            printHomePage(studentList);
        }
    }
   
public static void printStudentDetails(List<String[]> studentList) {
    System.out.println("---------------------------------------------------------");
    System.out.println("\t\t  STUDENT DETAILS");
    System.out.println("---------------------------------------------------------");
    System.out.println();
    System.out.println();

    Scanner input = new Scanner(System.in);

    System.out.print("Enter Student ID   :");
    String studentId = input.nextLine();

    boolean isStudentFound = false;
    for (String[] studentDetails : studentList) {
        if (studentDetails[0].equals(studentId)) {
            System.out.println("Student ID     : " + studentDetails[0]);
            System.out.println("Student Name   : " + studentDetails[1]);
            System.out.println("Subject 1 Marks: " + studentDetails[2]);
            System.out.println("Subject 2 Marks: " + studentDetails[3]);
            isStudentFound = true;
            break;
        }
    }

    if (!isStudentFound) {
        System.out.println("Invalid student ID.");
    }

    System.out.print("Press enter to continue...");
    input.nextLine();

    clearConsole();
    printHomePage(studentList);
}



 public static void printStudentRanks(List<String[]> studentList) {
    System.out.println("---------------------------------------------------------");
    System.out.println("\t\t  STUDENT RANKS");
    System.out.println("---------------------------------------------------------");
    System.out.println();
    System.out.printf("%-15s %-25s %-15s %-15s %-15s\n",
            "Student ID", "Student Name", "Total Marks", "Average Marks", "Rank");
    System.out.println("------------------------------------------------------------------");

    List<Integer> totalMarksList = new ArrayList<>();
    for (String[] studentDetails : studentList) {
        int totalMarks = 0;
        int numOfSubjects = 0;
        for (int i = 2; i < studentDetails.length; i++) {
            try {
                int marks = Integer.parseInt(studentDetails[i]);
                totalMarks += marks;
                numOfSubjects++;
            } catch (NumberFormatException e) {
                // Ignore non-numeric values
            }
        }
        if (numOfSubjects > 0) {
            totalMarksList.add(totalMarks);
        }
    }

    Collections.sort(totalMarksList, Collections.reverseOrder());
    int rank = 1;
    for (int totalMarks : totalMarksList) {
        for (String[] studentDetails : studentList) {
            int totalMarksOfStudent = 0;
            int numOfSubjects = 0;
            for (int i = 2; i < studentDetails.length; i++) {
                try {
                    int marks = Integer.parseInt(studentDetails[i]);
                    totalMarksOfStudent += marks;
                    numOfSubjects++;
                } catch (NumberFormatException e) {
                    // Ignore non-numeric values
                }
            }
            if (numOfSubjects > 0 && totalMarksOfStudent == totalMarks) {
                double avgMarks = (double) totalMarksOfStudent / numOfSubjects;
                String rankStr = String.valueOf(rank);
                if (rank == 1) {
                    rankStr += "st";
                } else if (rank == 2) {
                    rankStr += "nd";
                } else if (rank == 3) {
                    rankStr += "rd";
                } else {
                    rankStr += "th";
                }
                System.out.printf("%-15s %-25s %-15d %-15.2f %-15s\n",
                        studentDetails[0], studentDetails[1], totalMarksOfStudent, avgMarks, rankStr);
                rank++;
            }
        }
    }

    Scanner input = new Scanner(System.in);
     System.out.print("Do you want to go back to main menu (Y/n)? ");
        String choice = input.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            clearConsole();
            printHomePage(studentList);

        } else if (choice.equalsIgnoreCase("n")) {
             clearConsole();
            printStudentRanks(studentList);
        }

}
 
public static void printBestInProgramming(List<String[]> studentList) {
    System.out.println("---------------------------------------------------------");
    System.out.println("\t\tBEST STUDENTS (ORDERED BY PROGRAMMING FUNDAMENTALS MARK)");
    System.out.println("---------------------------------------------------------\n\n");
    
     Scanner input = new Scanner(System.in);

    List<Student> students = new ArrayList<>();
    for (String[] student : studentList) {
        String id = student[0];
        String name = student[1];
        int programmingMark = Integer.parseInt(student[2]);
        int databaseMark = Integer.parseInt(student[3]);

        Student s = new Student(id, name, programmingMark, databaseMark);
        students.add(s);
    }

    // Sort the students based on their Programming Fundamentals mark
    students.sort(Comparator.comparing(Student::getProgrammingMark).reversed());

    // Display the students and their marks
    System.out.println("ID\tName\tProgramming Fundamentals\tDatabase Management");
    int rank = 1;
    for (Student student : students) {
        System.out.println(rank + ". " + student.getId() + "\t" + student.getName() + "\t" +
                student.getProgrammingMark() + "\t\t\t" + student.getDatabaseMark());
        rank++;
    }
    
    System.out.print("Do you want to go back to main menu (Y/n)? ");
    String choice = input.nextLine();

    if (choice.equalsIgnoreCase("Y")) {
        clearConsole();
        printHomePage(studentList);

    } else if (choice.equalsIgnoreCase("n")) {
        clearConsole();
        printBestInProgramming(studentList);
    }
}


// A class to represent a student
public static class Student {
    private String id;
    private String name;
    private int programmingMark;
    private int databaseMark;

    public Student(String id, String name, int programmingMark, int databaseMark) {
        this.id = id;
        this.name = name;
        this.programmingMark = programmingMark;
        this.databaseMark = databaseMark;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProgrammingMark() {
        return programmingMark;
    }

    public int getDatabaseMark() {
        return databaseMark;
    }
}

public static void printBestInDatabase(List<String[]> studentList) {
    System.out.println("---------------------------------------------------------");
    System.out.println("\t\tBEST STUDENTS (ORDERED BY DATABASE MANAGEMENT MARK)");
    System.out.println("---------------------------------------------------------\n\n");
    
     Scanner input = new Scanner(System.in);

    List<Student> students = new ArrayList<>();
    for (String[] student : studentList) {
        String id = student[0];
        String name = student[1];
        int programmingMark = Integer.parseInt(student[2]);
        int databaseMark = Integer.parseInt(student[3]);

        Student s = new Student(id, name, programmingMark, databaseMark);
        students.add(s);
    }

    // Sort the students based on their Database Management mark
    students.sort(Comparator.comparing(Student::getDatabaseMark).reversed());

    // Display the students and their marks
    System.out.println("ID\tName\tDatabase Management\tProgramming Fundamentals");
    int rank = 1;
    for (Student student : students) {
        System.out.println(rank + ". " + student.getId() + "\t" + student.getName() + "\t" +
                student.getDatabaseMark() + "\t\t\t" + student.getProgrammingMark());
        rank++;
    }
    
     System.out.print("Do you want to go back to main menu (Y/n)? ");
    String choice = input.nextLine();

    if (choice.equalsIgnoreCase("Y")) {
        clearConsole();
        printHomePage(studentList);

    } else if (choice.equalsIgnoreCase("n")) {
        clearConsole();
        printBestInDatabase(studentList);
    }
}

// A class to represent a student
public static class Student1 {
    private String id;
    private String name;
    private int programmingMark;
    private int databaseMark;

    public Student1(String id, String name, int programmingMark, int databaseMark) {
        this.id = id;
        this.name = name;
        this.programmingMark = programmingMark;
        this.databaseMark = databaseMark;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProgrammingMark() {
        return programmingMark;
    }

    public int getDatabaseMark() {
        return databaseMark;
    }
}

    public static void main(String[] args) {
        List<String[]> studentList = new ArrayList<String[]>();
        printHomePage(studentList);
        clearConsole();
    }
}
