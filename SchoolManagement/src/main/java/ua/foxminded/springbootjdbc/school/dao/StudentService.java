package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.foxminded.springbootjdbc.school.entity.Student;

@Service
public class StudentService {

  private final StudentDAO studentDao;

  public StudentService(StudentDAO studentDao) {
    this.studentDao = studentDao;
  }

  public List<Student> findStudentsRelatedToCourse(String courseName) {
    return studentDao.findStudentsRelatedToCourse(courseName);
  }

  public int addNewStudent(Student student) {
    int result = studentDao.addNewStudent(student);

    if (result != 1) {
      throw new IllegalStateException("oops something went wrong");
    }
    return result;
  }

  public int deleteStudentByID(int id) {
    return studentDao.deleteStudentByID(id);
  }

  public List<Integer> getStudentID() {
    return studentDao.getStudentID();
  }

  public int addStudentToTheCourse(Integer studentId, String courseName) {
    return studentDao.addStudentToTheCourse(studentId, courseName);
  }

  public int removeStudentFromCourse(Integer studentId, String courseName) {
    return studentDao.removeStudentFromCourse(studentId, courseName);
  }

}
