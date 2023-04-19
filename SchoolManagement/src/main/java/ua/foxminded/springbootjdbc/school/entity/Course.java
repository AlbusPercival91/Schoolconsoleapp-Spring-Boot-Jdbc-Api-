package ua.foxminded.springbootjdbc.school.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Course {
  private String courseName;
  private String courseDescription;

  public Course(String courseName, String courseDescription) {
    this.courseName = courseName;
    this.courseDescription = courseDescription;
  }

  public Course(String courseName) {
    this.courseName = courseName;
  }

}
