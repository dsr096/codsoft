import java.util.*;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolled;

    public Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0; // initially no students are enrolled
    }

    public boolean isAvailable() {
        return enrolled < capacity;
    }

    public void registerStudent() {
        if (isAvailable()) {
            enrolled++;
            System.out.println("Successfully registered for " + title);
        } else {
            System.out.println("Sorry, the course " + title + " is full.");
        }
    }

    public void dropStudent() {
        if (enrolled > 0) {
            enrolled--;
            System.out.println("Successfully dropped from " + title);
        } else {
            System.out.println("You are not enrolled in " + title);
        }
    }

    public void displayCourseInfo() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Capacity: " + capacity);
        System.out.println("Enrolled: " + enrolled);
        System.out.println("Available Slots: " + (capacity - enrolled));
    }
}

class Student {
    String studentId;
    String name;
    Set<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new HashSet<>();
    }

    public void registerCourse(Course course) {
        if (course.isAvailable()) {
            course.registerStudent();
            registeredCourses.add(course);
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.dropStudent();
            registeredCourses.remove(course);
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    public void viewRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("You are not registered for any courses.");
        } else {
            System.out.println("You are registered for the following courses:");
            for (Course course : registeredCourses) {
                System.out.println(course.title + " (Code: " + course.courseCode + ")");
            }
        }
    }
}

public class CourseRegistrationSystem {
    static Map<String, Course> courseDatabase = new HashMap<>();
    static Map<String, Student> studentDatabase = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating some sample courses
        Course course1 = new Course("CS101", "Introduction to Computer Science", "Learn the basics of computer science", 3);
        Course course2 = new Course("MATH101", "Calculus I", "Introduction to calculus", 2);
        Course course3 = new Course("ENG101", "English Literature", "Study classic English literature", 5);
        Course course4 = new Course("BIO101", "Biology Basics", "Learn about basic biology concepts", 4);

        courseDatabase.put(course1.courseCode, course1);
        courseDatabase.put(course2.courseCode, course2);
        courseDatabase.put(course3.courseCode, course3);
        courseDatabase.put(course4.courseCode, course4);

        // Sample Students
        Student student1 = new Student("S101", "John Doe");
        Student student2 = new Student("S102", "Jane Smith");

        studentDatabase.put(student1.studentId, student1);
        studentDatabase.put(student2.studentId, student2);

        // Main Menu for the User
        while (true) {
            System.out.println("\n--- Welcome to the Course Registration System ---");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View My Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    viewAvailableCourses();
                    break;

                case 2:
                    System.out.print("Enter your Student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = studentDatabase.get(studentId);
                    if (student != null) {
                        registerCourse(scanner, student);
                    } else {
                        System.out.println("Invalid Student ID.");
                    }
                    break;

                case 3:
                    System.out.print("Enter your Student ID: ");
                    studentId = scanner.nextLine();
                    student = studentDatabase.get(studentId);
                    if (student != null) {
                        dropCourse(scanner, student);
                    } else {
                        System.out.println("Invalid Student ID.");
                    }
                    break;

                case 4:
                    System.out.print("Enter your Student ID: ");
                    studentId = scanner.nextLine();
                    student = studentDatabase.get(studentId);
                    if (student != null) {
                        student.viewRegisteredCourses();
                    } else {
                        System.out.println("Invalid Student ID.");
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using the Course Registration System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void viewAvailableCourses() {
        System.out.println("\n--- Available Courses ---");
        for (Course course : courseDatabase.values()) {
            course.displayCourseInfo();
            System.out.println();
        }
    }

    private static void registerCourse(Scanner scanner, Student student) {
        System.out.println("\n--- Register for a Course ---");
        System.out.print("Enter the course code: ");
        String courseCode = scanner.nextLine();

        Course course = courseDatabase.get(courseCode);
        if (course != null) {
            student.registerCourse(course);
        } else {
            System.out.println("Invalid course code.");
        }
    }

    private static void dropCourse(Scanner scanner, Student student) {
        System.out.println("\n--- Drop a Course ---");
        System.out.print("Enter the course code: ");
        String courseCode = scanner.nextLine();

        Course course = courseDatabase.get(courseCode);
        if (course != null) {
            student.dropCourse(course);
        } else {
            System.out.println("Invalid course code.");
        }
    }
}
