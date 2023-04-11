package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.foxminded.springbootjdbc.school.entity.Student;

@Service
public class StudentService {
  private static final String WRONG = "oops something went wrong";

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
      throw new IllegalStateException(WRONG);
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
    int result = studentDao.addStudentToTheCourse(studentId, courseName);

    if (result != 1) {
      throw new IllegalStateException(WRONG);
    }
    return result;
  }

  public int removeStudentFromCourse(Integer studentId, String courseName) {
    return studentDao.removeStudentFromCourse(studentId, courseName);
  }

  public int updateStudentById(Integer studentId, Student student) {
    int result = studentDao.updateStudentById(studentId, student);

    if (result != 1) {
      throw new IllegalStateException(WRONG);
    }
    return result;
  }

  public List<Object> showAllStudents() {
    return studentDao.showAllStudents();
  }

}
