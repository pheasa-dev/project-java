import java.io.*;
import java.util.*;

public class StudentManagement {
    private static final int MIN_ID = 5000;
    private static final int MAX_ID = 6000;
    private static List<Student> students = new ArrayList<>();
    private static final String DATA_FILE = "students.dat";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();
        int choice;
        int option = 0;
        System.out.println(" \t\t\t\t\t\t\t\t\t\t___ ____ _____  _    ____  \n" +
                "\t\t\t\t\t\t\t\t\t\t|_ _/ ___|_   _|/ \\  |  _ \\ \n" +
                "\t\t\t\t\t\t\t\t\t\t | |\\___ \\ | | / _ \\ | | | |\n" +
                "\t\t\t\t\t\t\t\t\t\t | | ___) || |/ ___ \\| |_| |\n" +
                "\t\t\t\t\t\t\t\t\t\t|___|____/ |_/_/   \\_\\____/ \n");
        System.out.println("[*] SPENT TIME TO READING DATA: ");
        System.out.println("[*] NUMBER OF REACORD IN DATA SCORE FILE: ");
        do {
            System.out.println("======================================================================================================================");
            System.out.println("\t  1. Add New Student             2. LIST ALL STUDENT                        3. COMMIT DATA TO FILE    ");
            System.out.println("\t  4. SERACH FOR STUDENT          5. UPDATE STUDETN'S INFO BY ID             6. DELETE STUDENT DATA    ");
            System.out.println("\t  7. GENERATE DATA TO FILE       8.DELETE/CLEAR ALL DATA FROM DATA STORE    0, 99. EXIT");
            System.out.println();
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t @Copyright by student ISTAD!!");
            System.out.println("======================================================================================================================");
            System.out.print("> Insert Option: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> listAllStudents();
                case 3 -> commitDataToFile();
                case 4 -> searchStudent();
                case 5 -> updateStudentById();
                case 6 -> deleteStudent();
                case 7 -> generateDataToFile();
                case 8 -> clearAllData();
                case 0, 99 -> exitProgram();
                default -> System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0 && choice != 99);
    }

    private static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing data found. Starting fresh.");
        }
    }

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        Scanner scanner = new Scanner(System.in);
        System.out.println("> INSERT STUDENT'S INFO");
        int randomId = generateRandomId();
        String concatenatedId = randomId + "CSTAD";
        System.out.print("[+] Insert Student's Name: ");
        String name = scanner.nextLine();
        System.out.println("[+] STUDENT'S DATE OF BIRTH:");
        System.out.print("> Year (number): ");
        int year = scanner.nextInt();
        System.out.print("> Month (number): ");
        int month = scanner.nextInt();
        while (month < 1 || month > 12) {
            System.out.println("[!] Month should be between(1-12)");
            System.out.print("Enter again: ");
            month = scanner.nextInt();
        }
        System.out.print("> Day (number): ");
        int day = scanner.nextInt();
        while (day < 1 || day > 31) {
            System.out.println(" Enter day   : ");
            day = scanner.nextInt();
        }
        scanner.nextLine(); // Consume newline
        System.out.print("[!] You can insert multi classes by splitting [,] symbol (c1,c2)\n[+] Student's Class: ");
        String classes[] = new String[]{scanner.nextLine()};
        System.out.print("[!] You can insert multi subjects by splitting [,] symbol (s1,s2)\n[+] Student's Studied: ");
        String subjects[] = new String[]{scanner.nextLine()};
        students.add(new Student(randomId, name, year, month, day, classes, subjects));
        System.out.println("Student added successfully!!");
    }

    private static int generateRandomId() {
        Random random = new Random();
        return random.nextInt(MAX_ID - MIN_ID + 1) + MIN_ID;
    }

    private static void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static void commitDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(students);
            System.out.println("Data committed to file successfully.");
        } catch (IOException e) {
            System.out.println("Error committing data to file: " + e.getMessage());
        }
    }

    private static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        Student foundStudent = findStudentById(id);
        if (foundStudent != null) {
            System.out.println(foundStudent);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void updateStudentById() {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        Student foundStudent = findStudentById(id);
        if (foundStudent != null) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new email: ");
            String email = scanner.nextLine();
            foundStudent.setName(name);
            System.out.println("Student information updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        Student foundStudent = findStudentById(id);
        if (foundStudent != null) {
            students.remove(foundStudent);
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void generateDataToFile() {
        System.out.print("Enter file name to generate data: ");
        String fileName = scanner.nextLine();
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            for (Student student : students) {
                writer.println(student);
            }
            System.out.println("Data generated to file successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error generating data to file: " + e.getMessage());
        }
    }

    private static void clearAllData() {
        students.clear();
        System.out.println("All student data cleared.");
    }

    private static Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private static void exitProgram() {
        commitDataToFile();
        System.out.println("Exiting program. Goodbye!");
        System.exit(0);
    }
}
