package ua.foxminded.springbootjdbc.school.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Group {
  private String groupName;

  public Group(String groupName) {
    this.groupName = groupName;
  }

}
