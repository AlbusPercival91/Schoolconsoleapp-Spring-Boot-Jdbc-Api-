package ua.foxminded.springbootjdbc.school.console;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class MainMenuComponents {

  private DaoMenuComponents daoMenu;

  public MainMenuComponents(DaoMenuComponents facade) {
    this.daoMenu = facade;
  }

  public String studentMenu(Scanner scan) {
    String command = "";
    System.out.println(MenuConstants.STUDENT_MENU);

    while (!command.equals("m")) {
      command = scan.nextLine();
      if (command.equalsIgnoreCase("a")) {
        daoMenu.findStudentsRelatedToCourse(scan);
      } else if (command.equalsIgnoreCase("b")) {
        daoMenu.addNewStudent(scan);
      } else if (command.equalsIgnoreCase("c")) {
        daoMenu.deleteStudentByID(scan);
      } else if (command.equalsIgnoreCase("d")) {
        daoMenu.addStudentToTheCourse(scan);
      } else if (command.equalsIgnoreCase("e")) {
        daoMenu.removeStudentFromCourse(scan);
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
        daoMenu.findGroupsWithLessOrEqualsStudents(scan);
      } else if (command.equalsIgnoreCase("m")) {
        System.out.println(MenuConstants.MAIN_MENU);
      } else {
        System.out.println(MenuConstants.GROUP_MENU);
      }
    }
    return command;
  }

  public String courseMenu(Scanner scan) {
    String command = "";
    System.out.println(MenuConstants.COURSE_MENU);
    while (!command.equals("m")) {
      command = scan.nextLine();
      if (command.equalsIgnoreCase("a")) {
        System.out.println("You write " + command + ", will be available soon! =)");
      } else if (command.equalsIgnoreCase("m")) {
        System.out.println(MenuConstants.MAIN_MENU);
      } else {
        System.out.println(MenuConstants.COURSE_MENU);
      }
    }
    return command;
  }
}
