package ua.foxminded.springbootjdbc.school.entity;

public class Student {
  private Integer groupId;
  private String firstName;
  private String lastName;

  public Student(Integer groupId, String firstName, String lastName) {
    super();
    this.groupId = groupId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Integer getGroupId() {
    return groupId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public String toString() {
    if (groupId == 0) {
      groupId = null;
    }
    return "Student [group ID= " + groupId + ", Name= " + firstName + ", Surname= " + lastName + "]";
  }

}
