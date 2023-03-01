package ua.foxminded.springbootjdbc.school.console;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.foxminded.springbootjdbc.school.dao.SchoolService;
import ua.foxminded.springbootjdbc.school.dao.TestDataService;

@Component
public class ConsoleMenuRunner {

  private final SchoolService schoolService;

  public ConsoleMenuRunner(SchoolService schoolService) {
    this.schoolService = schoolService;
  }

  @Autowired
  public void runner(TestDataService testData) {
    testData.createGroup();
    testData.createStudent();
    testData.createCourse();
    testData.createCourseStudentRelation();

    Scanner scan = new Scanner(System.in);

    String command = "";
    String menu = "a. Find all groups with less or equal students’ number\n"
        + "b. Find all students related to the course with the given name\n" + "c. Add a new student\n"
        + "d. Delete a student by the STUDENT_ID\n" + "e. Add a student to the course (from a list)\n"
        + "f. Remove the student from one of their courses\n" + "q. Quit\n";
    System.out.println("Welcome to School console application. Please choose options below:\n\n" + menu);

    while (!command.equalsIgnoreCase("q")) {
      command = scan.nextLine();

      if (command.equalsIgnoreCase("a")) {
        System.out.println(ConsoleMenuConstants.NUMBER_OF_STUDENTS);

        if (scan.hasNextInt()) {
          int quant = scan.nextInt();
          schoolService.findGroupsWithLessOrEqualsStudents(quant).forEach(System.out::println);
        } else {
          System.out.println(ConsoleMenuConstants.DIGITS_REQUIRED);
        }
      }

//        else if (command.equalsIgnoreCase("b")) {
//            cmf.findStudentsRelatedToCourseFacade(scan, menu);
//        } else if (command.equalsIgnoreCase("c")) {
//            cmf.addNewStudentFacade(scan);
//        } else if (command.equalsIgnoreCase("d")) {
//            cmf.deleteStudentByIdFacade(scan);
//        } else if (command.equalsIgnoreCase("e")) {
//            cmf.addStudentToTheCourseFacade(scan);
//        } else if (command.equalsIgnoreCase("f")) {
//            cmf.removeStudentFromCourseFacade(scan);
//        } else if (command.equalsIgnoreCase("q")) {
//            System.out.println("exit - OK!");
//        } else {
//            System.out.println("\n" + menu);
//        }
    }
    scan.close();
  }

}
