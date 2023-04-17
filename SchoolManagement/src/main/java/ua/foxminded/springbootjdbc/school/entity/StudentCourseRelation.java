package ua.foxminded.springbootjdbc.school.entity;

import lombok.Getter;

@Getter
public class StudentCourseRelation {
  private int studentId;
  private int courseId;

  public StudentCourseRelation(int studentId, int courseId) {
    this.studentId = studentId;
    this.courseId = courseId;
  }

}
