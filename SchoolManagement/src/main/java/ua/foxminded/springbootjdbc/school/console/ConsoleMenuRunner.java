package ua.foxminded.springbootjdbc.school.console;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.foxminded.springbootjdbc.school.dao.SchoolService;
import ua.foxminded.springbootjdbc.school.dao.TestDataService;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;

@Component
public class ConsoleMenuRunner {

  private final SchoolService schoolService;
  CourseMaker course = new CourseMaker();

  public ConsoleMenuRunner(SchoolService schoolService) {
    this.schoolService = schoolService;
  }

  @Autowired
  public void runner(TestDataService testData) {
    testData.createGroup();
    testData.createStudent();
    testData.createCourse();
    testData.createCourseStudentRelation();

    Scanner scan = new Scanner(System.in);

    String command = "";

    System.out
        .println("Welcome to School console application. Please choose options below:\n\n" + ConsoleMenuConstants.MENU);

    while (!command.equalsIgnoreCase("q")) {
      command = scan.nextLine();

      if (command.equalsIgnoreCase("a")) {
        System.out.println(ConsoleMenuConstants.NUMBER_OF_STUDENTS);

        if (scan.hasNextInt()) {
          int quant = scan.nextInt();
          schoolService.findGroupsWithLessOrEqualsStudents(quant).stream().map(Group::toString)
              .forEach(System.out::println);
        } else {
          System.out.println(ConsoleMenuConstants.DIGITS_REQUIRED);
        }
      } else if (command.equalsIgnoreCase("b")) {
        System.out.println(ConsoleMenuConstants.COURSE_NAME);
        String courseName = scan.nextLine();

        if (course.generateCourses().contains(courseName)) {
          schoolService.findStudentsRelatedToCourse(courseName).forEach(System.out::println);
          System.out.println("\n" + ConsoleMenuConstants.MENU);
        } else {
          System.out.println(ConsoleMenuConstants.WRONG_COURSE + "\n" + ConsoleMenuConstants.MENU);
        }
      } else if (command.equalsIgnoreCase("c")) {
        System.out.println(ConsoleMenuConstants.STUDENT_NAME);
        String firstName = scan.nextLine();
        System.out.println(ConsoleMenuConstants.STUDENT_LAST_NAME);
        String lastName = scan.nextLine();

        if (!firstName.isEmpty() || !lastName.isEmpty()) {
          System.out.println(ConsoleMenuConstants.GROUP_ID);

          if (scan.hasNextInt()) {
            Integer groupId = scan.nextInt();

            if (groupId >= 0 && groupId <= 10) {

              if (groupId == 0) {
                groupId = null;
              }
              Student student = new Student(groupId, firstName, lastName);
              System.out
                  .println(schoolService.addNewStudent(student) + " student added" + "\n" + ConsoleMenuConstants.MENU);
            } else {
              System.out.println(ConsoleMenuConstants.GROUP_ID_NOTE);
            }
          } else {
            System.out.println(ConsoleMenuConstants.GROUP_ID_NOTE2);
          }
        } else {
          System.out.println(ConsoleMenuConstants.EMPTY_NOTE);
        }

      } else if (command.equalsIgnoreCase("d")) {
        System.out.println(ConsoleMenuConstants.STUDENT_ID);
        int studentId = scan.nextInt();
        System.out.println(schoolService.deleteStudentByID(studentId) + " student(s) deleted");
      } else if (command.equalsIgnoreCase("e")) {
        System.out.println(ConsoleMenuConstants.STUDENT_ID);

        if (scan.hasNextInt()) {
          Integer studentId = scan.nextInt();

          if (schoolService.getStudentID().contains(studentId)) {
            System.out.println(ConsoleMenuConstants.COURSE_LIST);
            course.generateCourses().forEach(System.out::println);
            String courseName = scan.next();

            if (course.generateCourses().contains(courseName)) {
              schoolService.addStudentToTheCourse(studentId, courseName);
              System.out.println("Student with ID: " + studentId + " assigned to the course: " + courseName);
            } else {
              System.out.println(ConsoleMenuConstants.WRONG_COURSE);
            }
          } else {
            System.out.println(ConsoleMenuConstants.STUDENT_ID_NOT_EXIST);
          }
        } else {
          System.out.println(ConsoleMenuConstants.DIGITS_REQUIRED);
        }
      } else if (command.equalsIgnoreCase("f")) {
        System.out.println(ConsoleMenuConstants.STUDENT_ID);

        if (scan.hasNextInt()) {
          Integer studentId = scan.nextInt();

          if (schoolService.getStudentID().contains(studentId)) {
            System.out.println(ConsoleMenuConstants.COURSE_LIST);
            course.generateCourses().forEach(System.out::println);
            String courseName = scan.next();

            if (course.generateCourses().contains(courseName)) {
              System.out.println(schoolService.removeStudentFromCourse(studentId, courseName) + " student(s) with ID "
                  + studentId + " deleted from course " + courseName);
            } else {
              System.out.println(ConsoleMenuConstants.WRONG_COURSE);
            }
          } else {
            System.out.println(ConsoleMenuConstants.STUDENT_ID_NOT_EXIST);
          }
        } else {
          System.out.println(ConsoleMenuConstants.DIGITS_REQUIRED);
        }
      } 
      
//      else if (command.equalsIgnoreCase("q")) {
//        System.out.println("exit - OK!");
//      } else {
//        System.out.println("\n" + ConsoleMenuConstants.MENU);
//      }
    }
    scan.close();
  }

}
