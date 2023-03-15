package ua.foxminded.springbootjdbc.school.console;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class MenuComponents {

  private MenuFacade facade;

  public MenuComponents(MenuFacade facade) {
    this.facade = facade;
  }

  public String studentMenu(Scanner scan) {
    String command = "";
    System.out.println(MenuConstants.STUDENT_MENU);

    while (!command.equals("m")) {
      command = scan.nextLine();
      if (command.equalsIgnoreCase("a")) {
        facade.findStudentsRelatedToCourse(scan);
      } else if (command.equalsIgnoreCase("b")) {
        facade.addNewStudent(scan);
      } else if (command.equalsIgnoreCase("c")) {
        facade.deleteStudentByID(scan);
      } else if (command.equalsIgnoreCase("d")) {
        facade.addStudentToTheCourse(scan);
      } else if (command.equalsIgnoreCase("e")) {
        facade.removeStudentFromCourse(scan);
      } else if (command.equalsIgnoreCase("m")) {
        System.out.println(MenuConstants.MAIN_MENU);
      } else {
        System.out.println(MenuConstants.STUDENT_MENU);
      }
    }
    return command;
  }

  public String groupMenu(Scanner scan) {
    String command = "";
    System.out.println(MenuConstants.GROUP_MENU);
    while (!command.equals("m")) {
      command = scan.nextLine();
      if (command.equalsIgnoreCase("a")) {
        facade.findGroupsWithLessOrEqualsStudents(scan);
      } else if (command.equalsIgnoreCase("m")) {
        System.out.println(MenuConstants.MAIN_MENU);
      } else {
        System.out.println(MenuConstants.GROUP_MENU);
      }
    }
    return command;
  }
}
