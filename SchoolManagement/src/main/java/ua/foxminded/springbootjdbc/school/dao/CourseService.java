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
}
