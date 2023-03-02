package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import org.springframework.stereotype.Service;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;

@Service
public class SchoolService {

  private final SchoolDAO schoolDao;

  public SchoolService(SchoolDAO schoolDao) {
    this.schoolDao = schoolDao;
  }

  public List<Group> findGroupsWithLessOrEqualsStudents(int students) {
    return schoolDao.findGroupsWithLessOrEqualsStudents(students);
  }

  public List<Student> findStudentsRelatedToCourse(String courseName) {
    return schoolDao.findStudentsRelatedToCourse(courseName);
  }

  public int addNewStudent(Student student) {
    int result = schoolDao.addNewStudent(student);

    if (result != 1) {
      throw new IllegalStateException("oops something went wrong");
    }
    return result;
  }

  public int deleteStudentByID(int id) {
    return schoolDao.deleteStudentByID(id);
  }

}
