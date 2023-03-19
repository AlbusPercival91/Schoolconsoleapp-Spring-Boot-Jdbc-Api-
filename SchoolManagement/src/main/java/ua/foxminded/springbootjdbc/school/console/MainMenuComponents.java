package ua.foxminded.springbootjdbc.school.console;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenuComponents {

  @Autowired
  private StudentMenuComponents studentMenu;

  @Autowired
  private GroupMenuComponents groupMenu;

  @Autowired
  private CourseMenuComponents courseMenu;

  public MainMenuComponents(StudentMenuComponents studentMenu, GroupMenuComponents groupMenu,
      CourseMenuComponents courseMenu) {
    this.studentMenu = studentMenu;
    this.groupMenu = groupMenu;
    this.courseMenu = courseMenu;
  }

  public String studentMenu(Scanner scan) {
    String command = "";
    System.out.println(MenuConstants.STUDENT_MENU);

    while (!command.equalsIgnoreCase("m")) {
      command = scan.nextLine();
      if (command.equalsIgnoreCase("a")) {
        studentMenu.findStudentsRelatedToCourseFacade(scan);
      } else if (command.equalsIgnoreCase("b")) {
        studentMenu.addNewStudentFacade(scan);
      } else if (command.equalsIgnoreCase("c")) {
        studentMenu.deleteStudentByIdFacade(scan);
      } else if (command.equalsIgnoreCase("d")) {
        studentMenu.addStudentToTheCourseFacade(scan);
      } else if (command.equalsIgnoreCase("e")) {
        studentMenu.removeStudentFromCourseFacade(scan);
      } else if (command.equalsIgnoreCase("f")) {
        studentMenu.updateStudentByIdFacade(scan);
      } else if (command.equalsIgnoreCase("g")) {
        studentMenu.showAllStudentsFacade();
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
        groupMenu.findGroupsWithLessOrEqualsStudentsFacade(scan);
      } else if (command.equalsIgnoreCase("b")) {
        groupMenu.createGroupFacade(scan);
      } else if (command.equalsIgnoreCase("c")) {
        groupMenu.editGroupNameFacade(scan);
      } else if (command.equalsIgnoreCase("d")) {
        groupMenu.deleteGroupByNameFacade(scan);
      } else if (command.equalsIgnoreCase("e")) {
        groupMenu.showAllGroupsFacade();
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
        courseMenu.findCoursesWithLessOrEqualsStudentsFacade(scan);
      } else if (command.equalsIgnoreCase("b")) {
        courseMenu.createCourseFacade(scan);
      } else if (command.equalsIgnoreCase("c")) {
        courseMenu.editCourseNameAndDescriptionFacade(scan);
      } else if (command.equalsIgnoreCase("d")) {
        courseMenu.deleteCourseByNameFacade(scan);
      } else if (command.equalsIgnoreCase("e")) {
        courseMenu.showAllCoursesFacade();
      } else if (command.equalsIgnoreCase("m")) {
        System.out.println(MenuConstants.MAIN_MENU);
      } else {
        System.out.println(MenuConstants.COURSE_MENU);
      }
    }
    return command;
  }
}
