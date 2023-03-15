package ua.foxminded.springbootjdbc.school.console;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.foxminded.springbootjdbc.school.testdata.dao.TestDataService;

@Component
public class ConsoleMenuRunner {
  private ConsoleFacade facade;

  public ConsoleMenuRunner(ConsoleFacade facade) {
    this.facade = facade;
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
        .println("Welcome to School console application. Please choose options below:\n" + ConsoleMenuConstants.MENU);

    while (!command.equalsIgnoreCase("q")) {
      command = scan.nextLine();

      if (command.equalsIgnoreCase("a")) {
        facade.findGroupsWithLessOrEqualsStudents(scan);
      } else if (command.equalsIgnoreCase("b")) {
        facade.findStudentsRelatedToCourse(scan);
      } else if (command.equalsIgnoreCase("c")) {
        facade.addNewStudent(scan);
      } else if (command.equalsIgnoreCase("d")) {
        facade.deleteStudentByID(scan);
      } else if (command.equalsIgnoreCase("e")) {
        facade.addStudentToTheCourse(scan);
      } else if (command.equalsIgnoreCase("f")) {
        facade.removeStudentFromCourse(scan);
      } else {
        System.out.println(ConsoleMenuConstants.MENU);
      }
    }
    scan.close();
  }

}
