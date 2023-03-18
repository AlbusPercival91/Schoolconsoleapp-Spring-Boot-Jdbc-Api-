package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import org.springframework.stereotype.Service;

import ua.foxminded.springbootjdbc.school.entity.Course;

@Service
public class CourseService {
  private static final String WRONG = "oops something went wrong";

  private final CourseDAO courseDao;

  public CourseService(CourseDAO courseDao) {
    this.courseDao = courseDao;
  }

  public List<Course> findCoursesWithLessOrEqualsStudents(int students) {
    return courseDao.findCoursesWithLessOrEqualsStudents(students);
  }

  public int createCourse(Course course) {
    int result = courseDao.createCourse(course);

    if (result != 1) {
      throw new IllegalStateException(WRONG);
    }
    return result;
  }

  public int editCourseNameAndDescription(String courseName, String newCourseName, String newDescription) {
    int result = courseDao.editCourseNameAndDescription(courseName, newCourseName, newDescription);

    if (result != 1) {
      throw new IllegalStateException(WRONG);
    }
    return result;
  }

  public int deleteCourseByName(String courseName) {
    int result = courseDao.deleteCourseByName(courseName);

    if (result != 1) {
      throw new IllegalStateException(WRONG);
    }
    return result;
  }

  public List<Course> showAllGroups() {
    return courseDao.showAllCourses();
  }
}
