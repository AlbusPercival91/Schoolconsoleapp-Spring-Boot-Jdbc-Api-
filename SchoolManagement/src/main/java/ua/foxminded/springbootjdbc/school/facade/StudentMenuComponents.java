package ua.foxminded.springbootjdbc.school.facade;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ua.foxminded.springbootjdbc.school.dao.StudentService;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;

@Slf4j
@Component
public class StudentMenuComponents {
  private final StudentService studentService;

  @Autowired
  private CourseMaker courseMaker;

  public StudentMenuComponents(StudentService studentService) {
    this.studentService = studentService;
  }

  public void findStudentsRelatedToCourseFacade(Scanner scan) {
    log.info(MenuConstants.COURSE_NAME);
    String courseName = scan.nextLine();

    if (courseMaker.generateCourses().contains(courseName)) {
      studentService.findStudentsRelatedToCourse(courseName).forEach(System.out::println);
      log.info("\n" + MenuConstants.STUDENT_MENU);
    } else {
      log.warn(MenuConstants.WRONG_COURSE);
      log.info(MenuConstants.STUDENT_MENU);
    }
  }

  public void addNewStudentFacade(Scanner scan) {
    log.info(MenuConstants.STUDENT_NAME);
    String firstName = scan.nextLine();
    log.info(MenuConstants.STUDENT_LAST_NAME);
    String lastName = scan.nextLine();

    if (!firstName.isEmpty() || !lastName.isEmpty()) {
      log.info(MenuConstants.GROUP_ID);

      if (scan.hasNextInt()) {
        Integer groupId = scan.nextInt();

        if (groupId >= 0 && groupId <= 10) {

          if (groupId == 0) {
            groupId = null;
          }
          Student student = new Student(groupId, firstName, lastName);
          log.info(studentService.addNewStudent(student) + " student added");
        } else {
          log.warn(MenuConstants.GROUP_ID_NOTE);
        }
      } else {
        log.warn(MenuConstants.GROUP_ID_NOTE2);
      }
    } else {
      log.warn(MenuConstants.EMPTY_NOTE);
    }
  }

  public void deleteStudentByIdFacade(Scanner scan) {
    log.info(MenuConstants.STUDENT_ID);
    int studentId = scan.nextInt();
    log.info(studentService.deleteStudentByID(studentId) + " student(s) deleted");
  }

  public void addStudentToTheCourseFacade(Scanner scan) {
    log.info(MenuConstants.STUDENT_ID);

    if (scan.hasNextInt()) {
      Integer studentId = scan.nextInt();

      if (studentService.getStudentID().contains(studentId)) {
        log.info(MenuConstants.COURSE_LIST);
        courseMaker.generateCourses().forEach(System.out::println);
        String courseName = scan.next();

        if (courseMaker.generateCourses().contains(courseName)) {
          studentService.addStudentToTheCourse(studentId, courseName);
          log.info("Student with ID: " + studentId + " assigned to the course: " + courseName);
        } else {
          log.warn(MenuConstants.WRONG_COURSE);
        }
      } else {
        log.warn(MenuConstants.STUDENT_ID_NOT_EXIST);
      }
    } else {
      log.warn(MenuConstants.DIGITS_REQUIRED);
    }
  }

  public void removeStudentFromCourseFacade(Scanner scan) {
    log.info(MenuConstants.STUDENT_ID);

    if (scan.hasNextInt()) {
      Integer studentId = scan.nextInt();

      if (studentService.getStudentID().contains(studentId)) {
        log.info(MenuConstants.COURSE_LIST);
        courseMaker.generateCourses().forEach(System.out::println);
        String courseName = scan.next();

        if (courseMaker.generateCourses().contains(courseName)) {
          log.info(studentService.removeStudentFromCourse(studentId, courseName) + " student(s) with ID " + studentId
              + " deleted from course " + courseName);
        } else {
          log.warn(MenuConstants.WRONG_COURSE);
        }
      } else {
        log.warn(MenuConstants.STUDENT_ID_NOT_EXIST);
      }
    } else {
      log.warn(MenuConstants.DIGITS_REQUIRED);
    }
  }

  public void updateStudentByIdFacade(Scanner scan) {
    log.info(MenuConstants.STUDENT_ID);
    Integer studentId = scan.nextInt();

    if (studentService.getStudentID().contains(studentId)) {
      log.info(MenuConstants.STUDENT_NAME);
      String newFirstName = scan.next();
      log.info(MenuConstants.STUDENT_LAST_NAME);
      String newLastName = scan.next();

      if (!newFirstName.isEmpty() || !newLastName.isEmpty()) {
        log.info(MenuConstants.GROUP_ID);

        if (scan.hasNextInt()) {
          Integer newGroupId = scan.nextInt();
          updateStudent(studentId, newFirstName, newLastName, newGroupId);
        } else {
          log.warn(MenuConstants.GROUP_ID_NOTE2);
        }
      } else {
        log.warn(MenuConstants.EMPTY_NOTE);
      }
    } else {
      log.warn(MenuConstants.STUDENT_ID_NOT_EXIST);
    }
  }

  private void updateStudent(Integer studentId, String newFirstName, String newLastName, Integer newGroupId) {
    if (newGroupId >= 0 && newGroupId <= 10) {

      if (newGroupId == 0) {
        newGroupId = null;
      }
      Student updatedStudent = new Student(newGroupId, newFirstName, newLastName);
      log.info(studentService.updateStudentById(studentId, updatedStudent) + " student updated" + "\n"
          + MenuConstants.STUDENT_MENU);
    } else {
      log.warn(MenuConstants.GROUP_ID_NOTE);
    }
  }

  public void showAllStudentsFacade() {
    studentService.showAllStudents().forEach(System.out::println);
    log.info(MenuConstants.STUDENT_MENU);
  }

}
