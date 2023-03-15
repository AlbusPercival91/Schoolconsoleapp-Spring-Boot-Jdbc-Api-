package ua.foxminded.springbootjdbc.school.entity;

public class Group {
  private String groupName;

  public Group(String groupName) {
    this.groupName = groupName;
  }

  public String getGroupName() {
    return groupName;
  }

  @Override
  public String toString() {
    return groupName;
  }
}
