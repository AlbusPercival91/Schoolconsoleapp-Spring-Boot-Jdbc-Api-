package ua.foxminded.springbootjdbc.school.console;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.foxminded.springbootjdbc.school.dao.GroupService;
import ua.foxminded.springbootjdbc.school.dao.StudentService;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;

@Component
public class MenuFacade {
 
  @Autowired
  private final StudentService studentService;

  @Autowired
  private final GroupService groupService;

  @Autowired
  private CourseMaker courseMaker;

  public MenuFacade(StudentService studentService, GroupService groupService) {
    this.studentService = studentService;
    this.groupService = groupService;
  }

  public void findGroupsWithLessOrEqualsStudents(Scanner scan) {
    System.out.println(MenuConstants.NUMBER_OF_STUDENTS);

    if (scan.hasNextInt()) {
      int quant = scan.nextInt();
      groupService.findGroupsWithLessOrEqualsStudents(quant).stream().map(Group::toString).forEach(System.out::println);
    } else {
      System.out.println(MenuConstants.DIGITS_REQUIRED);
    }
  }

  public void findStudentsRelatedToCourse(Scanner scan) {
    System.out.println(MenuConstants.COURSE_NAME);
    String courseName = scan.nextLine();

    if (courseMaker.generateCourses().contains(courseName)) {
      studentService.findStudentsRelatedToCourse(courseName).forEach(System.out::println);
      System.out.println("\n" + MenuConstants.STUDENT_MENU);
    } else {
      System.out.println(MenuConstants.WRONG_COURSE + "\n" + MenuConstants.STUDENT_MENU);
    }
  }

  public void addNewStudent(Scanner scan) {
    System.out.println(MenuConstants.STUDENT_NAME);
    String firstName = scan.nextLine();
    System.out.println(MenuConstants.STUDENT_LAST_NAME);
    String lastName = scan.nextLine();

    if (!firstName.isEmpty() || !lastName.isEmpty()) {
      System.out.println(MenuConstants.GROUP_ID);

      if (scan.hasNextInt()) {
        Integer groupId = scan.nextInt();

        if (groupId >= 0 && groupId <= 10) {

          if (groupId == 0) {
            groupId = null;
          }
          Student student = new Student(groupId, firstName, lastName);
          System.out
              .println(studentService.addNewStudent(student) + " student added" + "\n" + MenuConstants.STUDENT_MENU);
        } else {
          System.out.println(MenuConstants.GROUP_ID_NOTE);
        }
      } else {
        System.out.println(MenuConstants.GROUP_ID_NOTE2);
      }
    } else {
      System.out.println(MenuConstants.EMPTY_NOTE);
    }
  }

  public void deleteStudentByID(Scanner scan) {
    System.out.println(MenuConstants.STUDENT_ID);
    int studentId = scan.nextInt();
    System.out.println(studentService.deleteStudentByID(studentId) + " student(s) deleted");
  }

  public void addStudentToTheCourse(Scanner scan) {
    System.out.println(MenuConstants.STUDENT_ID);

    if (scan.hasNextInt()) {
      Integer studentId = scan.nextInt();

      if (studentService.getStudentID().contains(studentId)) {
        System.out.println(MenuConstants.COURSE_LIST);
        courseMaker.generateCourses().forEach(System.out::println);
        String courseName = scan.next();

        if (courseMaker.generateCourses().contains(courseName)) {
          studentService.addStudentToTheCourse(studentId, courseName);
          System.out.println("Student with ID: " + studentId + " assigned to the course: " + courseName);
        } else {
          System.out.println(MenuConstants.WRONG_COURSE);
        }
      } else {
        System.out.println(MenuConstants.STUDENT_ID_NOT_EXIST);
      }
    } else {
      System.out.println(MenuConstants.DIGITS_REQUIRED);
    }
  }

  public void removeStudentFromCourse(Scanner scan) {
    System.out.println(MenuConstants.STUDENT_ID);

    if (scan.hasNextInt()) {
      Integer studentId = scan.nextInt();

      if (studentService.getStudentID().contains(studentId)) {
        System.out.println(MenuConstants.COURSE_LIST);
        courseMaker.generateCourses().forEach(System.out::println);
        String courseName = scan.next();

        if (courseMaker.generateCourses().contains(courseName)) {
          System.out.println(studentService.removeStudentFromCourse(studentId, courseName) + " student(s) with ID "
              + studentId + " deleted from course " + courseName);
        } else {
          System.out.println(MenuConstants.WRONG_COURSE);
        }
      } else {
        System.out.println(MenuConstants.STUDENT_ID_NOT_EXIST);
      }
    } else {
      System.out.println(MenuConstants.DIGITS_REQUIRED);
    }
  }

}
