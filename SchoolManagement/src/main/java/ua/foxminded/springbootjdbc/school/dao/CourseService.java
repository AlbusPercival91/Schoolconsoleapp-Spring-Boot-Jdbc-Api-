package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
  private static final String WRONG = "oops something went wrong";

  private final CourseDAO courseDao;

  public CourseService(CourseDAO courseDao) {
    this.courseDao = courseDao;
  }

  public List<Object> findCoursesWithLessOrEqualsStudents(int students) {
    return courseDao.findCoursesWithLessOrEqualsStudents(students);
  }
}
