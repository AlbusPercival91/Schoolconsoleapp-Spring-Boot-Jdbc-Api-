package ua.foxminded.springbootjdbc.school.console;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.foxminded.springbootjdbc.school.dao.SchoolService;
import ua.foxminded.springbootjdbc.school.dao.TestDataService;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;

@Component
public class ConsoleMenuRunner {

  private final SchoolService schoolService;
  CourseMaker course = new CourseMaker();

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

    System.out
        .println("Welcome to School console application. Please choose options below:\n\n" + ConsoleMenuConstants.MENU);

    while (!command.equalsIgnoreCase("q")) {
      command = scan.nextLine();

      if (command.equalsIgnoreCase("a")) {
        System.out.println(ConsoleMenuConstants.NUMBER_OF_STUDENTS);

        if (scan.hasNextInt()) {
          int quant = scan.nextInt();
          schoolService.findGroupsWithLessOrEqualsStudents(quant).stream().map(Group::toString)
              .forEach(System.out::println);
        } else {
          System.out.println(ConsoleMenuConstants.DIGITS_REQUIRED);
        }
      } else if (command.equalsIgnoreCase("b")) {
        System.out.println(ConsoleMenuConstants.COURSE_NAME);
        String courseName = scan.nextLine();

        if (course.generateCourses().contains(courseName)) {
          schoolService.findStudentsRelatedToCourse(courseName).forEach(System.out::println);
          System.out.println("\n" + ConsoleMenuConstants.MENU);
        } else {
          System.out.println(ConsoleMenuConstants.WRONG_COURSE);
          System.out.println("\n" + ConsoleMenuConstants.MENU);
        }

      }

//        else if (command.equalsIgnoreCase("c")) {
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
