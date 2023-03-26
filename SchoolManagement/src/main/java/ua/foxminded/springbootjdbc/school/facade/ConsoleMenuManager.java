package ua.foxminded.springbootjdbc.school.facade;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleMenuManager {

  @Autowired
  private MainMenuComponents mainMenu;

  public ConsoleMenuManager(MainMenuComponents mainMenu) {
    this.mainMenu = mainMenu;
  }

  public void manage() {
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
