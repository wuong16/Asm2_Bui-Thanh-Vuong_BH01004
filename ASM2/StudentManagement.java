import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

public class StudentManagement {
    private ArrayList<Student> studentList;

    public StudentManagement() {
        this.studentList = new ArrayList<>();
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public ArrayList<Student> findStudentsByLastName(String lastName) {
        ArrayList<Student> foundStudents = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getLastName().equalsIgnoreCase(lastName)) {
                foundStudents.add(student);
            }
        }
        return foundStudents;
    }

    public Student findStudentByFullName(String fullName) {
        for (Student student : studentList) {
            if (student.getFullName().equalsIgnoreCase(fullName)) {
                return student;
            }
        }
        return null;
    }

    public void editStudent(String fullName, String newLastName) {
        Student student = findStudentByFullName(fullName);
        if (student != null) {
            student.setLastName(newLastName);
            System.out.println("Student information updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement management = new StudentManagement();

        boolean quit = false;
        while (!quit) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Find Students by Last Name");
            System.out.println("3. Find and Edit Student by Full Name");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter student information (first name last name):");
                    String firstName = scanner.next();
                    String lastName = scanner.next();
                    management.addStudent(new Student(firstName, lastName));
                    break;
                case 2:
                    System.out.print("Enter last name to find students: ");
                    String searchLastName = scanner.next();
                    ArrayList<Student> foundStudents = management.findStudentsByLastName(searchLastName);
                    if (!foundStudents.isEmpty()) {
                        System.out.println("Students found with last name '" + searchLastName + "':");
                        for (Student student : foundStudents) {
                            System.out.println(student.getFullName());
                        }
                    } else {
                        System.out.println("No students found with last name '" + searchLastName + "'.");
                    }
                    break;
                case 3:
                    System.out.print("Enter full name of student to edit: ");
                    String fullNameToEdit = scanner.nextLine();
                    System.out.print("Enter new last name: ");
                    String newLastName = scanner.nextLine();
                    management.editStudent(fullNameToEdit, newLastName);
                    break;
                case 4:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }

        scanner.close();
    }
}
