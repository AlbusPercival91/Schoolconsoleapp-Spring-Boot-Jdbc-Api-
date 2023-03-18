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
        studentMenu.findStudentsRelatedToCourse(scan);
      } else if (command.equalsIgnoreCase("b")) {
        studentMenu.addNewStudent(scan);
      } else if (command.equalsIgnoreCase("c")) {
        studentMenu.deleteStudentByID(scan);
      } else if (command.equalsIgnoreCase("d")) {
        studentMenu.addStudentToTheCourse(scan);
      } else if (command.equalsIgnoreCase("e")) {
        studentMenu.removeStudentFromCourse(scan);
      } else if (command.equalsIgnoreCase("f")) {
        studentMenu.updateStudentById(scan);
      } else if (command.equalsIgnoreCase("g")) {
        studentMenu.showAllStudents();
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
        groupMenu.findGroupsWithLessOrEqualsStudents(scan);
      } else if (command.equalsIgnoreCase("b")) {
        groupMenu.createGroup(scan);
      } else if (command.equalsIgnoreCase("c")) {
        groupMenu.editGroupName(scan);
      } else if (command.equalsIgnoreCase("d")) {
        groupMenu.deleteGroupByName(scan);
      } else if (command.equalsIgnoreCase("e")) {
        groupMenu.showAllGroups();
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
        courseMenu.findCoursesWithLessOrEqualsStudents(scan);
      } else if (command.equalsIgnoreCase("b")) {
        courseMenu.createCourse(scan);
      } else if (command.equalsIgnoreCase("c")) {
        courseMenu.editCourseNameAndDescription(scan);
      } else if (command.equalsIgnoreCase("d")) {
        courseMenu.deleteCourseByName(scan);
      } else if (command.equalsIgnoreCase("e")) {
        courseMenu.showAllCourses();
      } else if (command.equalsIgnoreCase("m")) {
        System.out.println(MenuConstants.MAIN_MENU);
      } else {
        System.out.println(MenuConstants.COURSE_MENU);
      }
    }
    return command;
  }
}
