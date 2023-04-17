package ua.foxminded.springbootjdbc.school.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Student {
  private Integer groupId;
  private String firstName;
  private String lastName;

  public Student(Integer groupId, String firstName, String lastName) {
    this.groupId = groupId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

}
