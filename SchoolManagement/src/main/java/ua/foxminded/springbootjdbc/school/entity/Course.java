package ua.foxminded.springbootjdbc.school.entity;

public class Course {
  private String courseName;
  private String courseDescription;

  public Course(String courseName, String courseDescription) {
    super();
    this.courseName = courseName;
    this.courseDescription = courseDescription;
  }

  public Course(String courseName) {
    super();
    this.courseName = courseName;
  }

  public String getCourseName() {
    return courseName;
  }

  public String getCourseDescription() {
    return courseDescription;
  }

}
