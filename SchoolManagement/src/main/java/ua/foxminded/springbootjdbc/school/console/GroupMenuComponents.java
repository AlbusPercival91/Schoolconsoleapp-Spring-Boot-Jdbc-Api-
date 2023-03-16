package ua.foxminded.springbootjdbc.school.console;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.foxminded.springbootjdbc.school.dao.GroupService;
import ua.foxminded.springbootjdbc.school.entity.Group;

@Component
public class GroupMenuComponents {

  @Autowired
  private final GroupService groupService;
  
  public GroupMenuComponents(GroupService groupService) {
    this.groupService = groupService;
  }

  public void findGroupsWithLessOrEqualsStudents(Scanner scan) {
    System.out.println(MenuConstants.NUMBER_OF_STUDENTS);

    if (scan.hasNextInt()) {
      int quant = scan.nextInt();
      groupService.findGroupsWithLessOrEqualsStudents(quant).stream().map(Group::toString).forEach(System.out::println);
    } else {
      System.out.println(MenuConstants.DIGITS_REQUIRED);
    }
  }

}
