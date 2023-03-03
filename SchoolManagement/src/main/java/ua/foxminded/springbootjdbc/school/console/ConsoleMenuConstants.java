package ua.foxminded.springbootjdbc.school.console;

public final class ConsoleMenuConstants {
  static final String DIGITS_REQUIRED = "Digits required!";
  static final String STUDENT_ID = "Enter Student's ID: ";
  static final String WRONG_COURSE = "Wrong course name!";
  static final String NUMBER_OF_STUDENTS = "Enter number of students: ";
  static final String COURSE_NAME = "Enter course name: ";
  static final String STUDENT_NAME = "Enter student name: ";
  static final String STUDENT_LAST_NAME = "Enter student last name: ";
  static final String GROUP_ID = "Enter group id (if student don't have group enter 0): ";
  static final String STUDENT_ID_NOT_EXIST = "Student ID not exist!";
  static final String COURSE_LIST = "Please choose the course from List\n";
  static final String GROUP_ID_NOTE = "Group ID should be from 0 to 10.";
  static final String GROUP_ID_NOTE2 = "Wrong id format, digits required!";
  static final String EMPTY_NOTE = "Empty entrance!";
  static final String MENU = """
      a. Find all groups with less or equal students’ number
      b. Find all students related to the course with the given name
      c. Add a new student
      d. Delete a student by the ID
      e. Add a student to the course (from a list)
      f. Remove the student from one of their courses
      q. Quit
          """;

  private ConsoleMenuConstants() {

  }

}
