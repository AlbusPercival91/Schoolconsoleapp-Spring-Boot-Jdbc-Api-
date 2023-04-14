package ua.foxminded.springbootjdbc.school.facade;

import java.util.Scanner;
import org.springframework.stereotype.Component;
import ua.foxminded.springbootjdbc.school.dao.GroupService;
import ua.foxminded.springbootjdbc.school.entity.Group;

@Component
public class GroupMenuComponents {
  private final GroupService groupService;

  public GroupMenuComponents(GroupService groupService) {
    this.groupService = groupService;
  }

  public void findGroupsWithLessOrEqualsStudentsFacade(Scanner scan) {
    System.out.println(MenuConstants.NUMBER_OF_STUDENTS);

    if (scan.hasNextInt()) {
      int quant = scan.nextInt();
      groupService.findGroupsWithLessOrEqualsStudents(quant).stream().map(Group::toString).forEach(System.out::println);
    } else {
      System.out.println(MenuConstants.DIGITS_REQUIRED + "\n" + MenuConstants.GROUP_MENU);
    }
  }

  public void createGroupFacade(Scanner scan) {
    System.out.println(MenuConstants.GROUP_NAME);
    String groupName = scan.nextLine();

    if (!groupName.isEmpty()
        && groupService.showAllGroups().stream().noneMatch(group -> group.getGroupName().equals(groupName))) {
      Group group = new Group(groupName);
      System.out.println(groupService.createGroup(group) + " group added" + "\n" + MenuConstants.GROUP_MENU);
    } else {
      System.out.println(MenuConstants.EMPTY_NOTE + "\n" + MenuConstants.GROUP_MENU);
    }
  }

  public void editGroupNameFacade(Scanner scan) {
    System.out.println(MenuConstants.CHOOSE_GROUP_NAME);
    groupService.showAllGroups().forEach(System.out::println);
    String groupName = scan.nextLine();

    if (groupService.showAllGroups().stream().anyMatch(group -> group.getGroupName().equals(groupName))) {
      System.out.println(MenuConstants.NEW_GROUP_NAME);
      String newGroupName = scan.nextLine();
      System.out.println(groupService.editGroupName(groupName, newGroupName) + " group name updated" + "\n"
          + MenuConstants.GROUP_MENU);
    } else {
      System.out.println(MenuConstants.NO_SUCH_GROUP + "\n" + MenuConstants.GROUP_MENU);
    }
  }

  public void deleteGroupByNameFacade(Scanner scan) {
    System.out.println(MenuConstants.CHOOSE_GROUP_NAME);
    groupService.showAllGroups().forEach(System.out::println);
    String groupName = scan.nextLine();

    if (groupService.showAllGroups().stream().anyMatch(group -> group.getGroupName().equals(groupName))) {
      System.out
          .println(groupService.deleteGroupByName(groupName) + " group deleted" + "\n" + MenuConstants.GROUP_MENU);
    } else {
      System.out.println(MenuConstants.NO_SUCH_GROUP + "\n" + MenuConstants.GROUP_MENU);
    }
  }

  public void showAllGroupsFacade() {
    groupService.showAllGroups().forEach(System.out::println);
    System.out.println(MenuConstants.GROUP_MENU);
  }

}
