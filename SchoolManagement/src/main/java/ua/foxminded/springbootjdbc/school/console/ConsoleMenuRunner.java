package ua.foxminded.springbootjdbc.school.console;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.foxminded.springbootjdbc.school.testdata.dao.TestDataService;

@Component
public class ConsoleMenuRunner {

  @Autowired
  private MainMenuComponents mainMenu;

  public ConsoleMenuRunner(MainMenuComponents mainMenu) {
    this.mainMenu = mainMenu;
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

      if (command.equalsIgnoreCase("a")) {
        command = mainMenu.studentMenu(scan);
      } else if (command.equalsIgnoreCase("b")) {
        command = mainMenu.groupMenu(scan);
      } else if (command.equalsIgnoreCase("c")) {
        command = mainMenu.courseMenu(scan);
      }
    }
    scan.close();
  }

}
