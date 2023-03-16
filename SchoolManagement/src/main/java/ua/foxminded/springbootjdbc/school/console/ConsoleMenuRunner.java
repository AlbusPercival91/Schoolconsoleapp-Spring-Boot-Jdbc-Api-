package ua.foxminded.springbootjdbc.school.console;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.foxminded.springbootjdbc.school.testdata.dao.TestDataService;

@Component
public class ConsoleMenuRunner {

  @Autowired
  private MainMenuComponents menu;

  public ConsoleMenuRunner(MainMenuComponents menu) {
    this.menu = menu;
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
        .println("Welcome to School console application. Please choose options below:\n" + MenuConstants.MAIN_MENU);

    while (!command.equalsIgnoreCase("q")) {
      command = scan.nextLine();

      if (command.equals("1")) {
        command = menu.studentMenu(scan);
      } else if (command.equals("2")) {
        command = menu.groupMenu(scan);
      } else if (command.equals("3")) {
        command = menu.courseMenu(scan);
      }
    }
    scan.close();
  }

}
