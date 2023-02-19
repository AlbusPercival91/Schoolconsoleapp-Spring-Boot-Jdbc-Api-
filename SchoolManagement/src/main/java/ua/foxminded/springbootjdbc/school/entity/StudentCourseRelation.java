package ua.foxminded.springbootjdbc.school.entity;

public class StudentCourseRelation {
  private int studentId;
  private int courseId;

  public StudentCourseRelation(int studentId, int courseId) {
    super();
    this.studentId = studentId;
    this.courseId = courseId;
  }

  public int getStudentId() {
    return studentId;
  }

  public int getCourseId() {
    return courseId;
  }

}
