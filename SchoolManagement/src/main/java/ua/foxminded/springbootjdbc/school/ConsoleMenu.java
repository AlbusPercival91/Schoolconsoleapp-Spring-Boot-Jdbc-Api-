package ua.foxminded.springbootjdbc.school;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.foxminded.springbootjdbc.school.movie.Movie;
import ua.foxminded.springbootjdbc.school.movie.MovieDataAccessService;

@Component
public class ConsoleMenu {

  @Autowired
  public void addMovie(JdbcTemplate jdbcTemplate) {
    Scanner scan = new Scanner(System.in);
    System.out.println(ConsoleMenuConstants.STUDENT_LAST_NAME);
    String name = scan.nextLine();

    if (!name.isEmpty()) {    
        Movie movie = new Movie(null, name, null, null);
        MovieDataAccessService mds = new MovieDataAccessService(jdbcTemplate);
        mds.insertMovie(movie);
      } else {
        System.out.println(ConsoleMenuConstants.GROUP_ID_NOTE);
      }
    scan.close();
  }

//  CourseMaker course = new CourseMaker();
//
//  public void findGroupsWithLessOrEqualsStudentsFacade(Scanner scan) {
//      System.out.println(ConsoleMenuConstants.NUMBER_OF_STUDENTS);
//
//      if (scan.hasNextInt()) {
//          int quant = scan.nextInt();
//          school.findGroupsWithLessOrEqualsStudents(quant, DBConnection.getConnection(PSQL))
//                  .forEach(System.out::println);
//      } else {
//          System.out.println(ConsoleMenuConstants.DIGITS_REQUIRED);
//      }
//  }
//
//  public void findStudentsRelatedToCourseFacade(Scanner scan, String menu) {
//      System.out.println(ConsoleMenuConstants.COURSE_NAME);
//      String courseName = scan.nextLine();
//
//      if (course.generateCourses().contains(courseName)) {
//          school.findStudentsRelatedToCourse(courseName, DBConnection.getConnection(PSQL))
//                  .forEach(System.out::println);
//          System.out.println("\n" + menu);
//      } else {
//          System.out.println(ConsoleMenuConstants.WRONG_COURSE);
//          System.out.println("\n" + menu);
//      }
//  }
//
//  public void addNewStudentFacade(Scanner scan) {
//      System.out.println(ConsoleMenuConstants.STUDENT_NAME);
//      String firstName = scan.nextLine();
//      System.out.println(ConsoleMenuConstants.STUDENT_LAST_NAME);
//      String lastName = scan.nextLine();
//
//      if (!firstName.isEmpty() || !lastName.isEmpty()) {
//          System.out.println(ConsoleMenuConstants.GROUP_ID);
//
//          if (scan.hasNextInt()) {
//              Integer groupId = scan.nextInt();
//
//              if (groupId >= 0 && groupId <= 10) {
//
//                  if (groupId == 0) {
//                      groupId = null;
//                  }
//                  Student student = new Student(groupId, firstName, lastName);
//                  System.out.println(school.addNewStudent(student, DBConnection.getConnection(PSQL)));
//              } else {
//                  System.out.println(ConsoleMenuConstants.GROUP_ID_NOTE);
//              }
//          } else {
//              System.out.println(ConsoleMenuConstants.GROUP_ID_NOTE2);
//          }
//      } else {
//          System.out.println(ConsoleMenuConstants.EMPTY_NOTE);
//      }
//  }
//
//  public void deleteStudentByIdFacade(Scanner scan) {
//      System.out.println(ConsoleMenuConstants.STUDENT_ID);
//      int studentId = scan.nextInt();
//      System.out.println(school.deleteStudentByID(studentId, DBConnection.getConnection(PSQL))
//              + " student(s) deleted from data base");
//  }
//
//  public void addStudentToTheCourseFacade(Scanner scan) {
//      System.out.println(ConsoleMenuConstants.STUDENT_ID);
//
//      if (scan.hasNextInt()) {
//          Integer studentId = scan.nextInt();
//
//          if (school.getStudentID(DBConnection.getConnection(PSQL)).contains(studentId)) {
//              System.out.println(ConsoleMenuConstants.COURSE_LIST);
//              course.generateCourses().forEach(System.out::println);
//              String courseName = scan.next();
//
//              if (course.generateCourses().contains(courseName)) {
//                  school.addStudentToTheCourse(studentId, courseName, DBConnection.getConnection(PSQL));
//                  System.out.println("Student with ID: " + studentId + " assigned to the course: " + courseName);
//              } else {
//                  System.out.println(ConsoleMenuConstants.WRONG_COURSE);
//              }
//          } else {
//              System.out.println(ConsoleMenuConstants.STUDENT_ID_NOT_EXIST);
//          }
//      } else {
//          System.out.println(ConsoleMenuConstants.DIGITS_REQUIRED);
//      }
//  }
//
//  public void removeStudentFromCourseFacade(Scanner scan) {
//      System.out.println(ConsoleMenuConstants.STUDENT_ID);
//
//      if (scan.hasNextInt()) {
//          Integer studentId = scan.nextInt();
//
//          if (school.getStudentID(DBConnection.getConnection(PSQL)).contains(studentId)) {
//              System.out.println(ConsoleMenuConstants.COURSE_LIST);
//              course.generateCourses().forEach(System.out::println);
//              String courseName = scan.next();
//
//              if (course.generateCourses().contains(courseName)) {
//                  System.out.println(
//                          school.removeStudentFromCourse(studentId, courseName, DBConnection.getConnection(PSQL))
//                                  + " student(s) with ID: " + studentId + " deleted from course " + courseName);
//              } else {
//                  System.out.println(ConsoleMenuConstants.WRONG_COURSE);
//              }
//          } else {
//              System.out.println(ConsoleMenuConstants.STUDENT_ID_NOT_EXIST);
//          }
//      } else {
//          System.out.println(ConsoleMenuConstants.DIGITS_REQUIRED);
//      }
//  }

}
