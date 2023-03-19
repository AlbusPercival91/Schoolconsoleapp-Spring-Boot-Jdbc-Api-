package ua.foxminded.springbootjdbc.school.console;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.foxminded.springbootjdbc.school.dao.CourseService;
import ua.foxminded.springbootjdbc.school.entity.Course;

@Component
public class CourseMenuComponents {

  @Autowired
  private final CourseService courseService;

  public CourseMenuComponents(CourseService courseService) {
    this.courseService = courseService;
  }

  public void findCoursesWithLessOrEqualsStudentsFacade(Scanner scan) {
    System.out.println(MenuConstants.NUMBER_OF_STUDENTS);

    if (scan.hasNextInt()) {
      int quant = scan.nextInt();
      courseService.findCoursesWithLessOrEqualsStudents(quant).stream().map(Course::toString)
          .forEach(System.out::println);
    } else {
      System.out.println(MenuConstants.DIGITS_REQUIRED + "\n" + MenuConstants.COURSE_MENU);
    }
  }

  public void createCourseFacade(Scanner scan) {
    System.out.println(MenuConstants.COURSE_NAME);
    String courseName = scan.nextLine();

    if (!courseName.isEmpty()) {
      System.out.println(MenuConstants.COURSE_DESCRIPTION);
      String courseDescription = scan.nextLine();
      Course course = new Course(courseName, courseDescription);
      System.out.println(courseService.createCourse(course) + " course added" + "\n" + MenuConstants.COURSE_MENU);
    } else {
      System.out.println(MenuConstants.EMPTY_NOTE + "\n" + MenuConstants.COURSE_MENU);
    }
  }

  public void editCourseNameAndDescriptionFacade(Scanner scan) {
    System.out.println(MenuConstants.CHOOSE_COURSE_NAME);
    courseService.showAllCourses().forEach(System.out::println);
    String courseName = scan.nextLine();

    if (courseService.showAllCourses().stream().anyMatch(course -> course.getCourseName().equals(courseName))) {
      System.out.println(MenuConstants.NEW_COURSE_NAME);
      String newCourseName = scan.nextLine();

      if (!newCourseName.isEmpty()) {
        System.out.println(MenuConstants.COURSE_DESCRIPTION);
        String newCourseDescription = scan.nextLine();
        System.out.println(courseService.editCourseNameAndDescription(courseName, newCourseName, newCourseDescription)
            + " course name and description updated" + "\n" + MenuConstants.COURSE_MENU);
      } else {
        System.out.println(MenuConstants.EMPTY_NOTE + "\n" + MenuConstants.COURSE_MENU);
      }
    } else {
      System.out.println(MenuConstants.NO_SUCH_COURSE + "\n" + MenuConstants.COURSE_MENU);
    }
  }

  public void deleteCourseByNameFacade(Scanner scan) {
    System.out.println(MenuConstants.CHOOSE_COURSE_NAME);
    courseService.showAllCourses().forEach(System.out::println);
    String courseName = scan.nextLine();

    if (courseService.showAllCourses().stream().anyMatch(course -> course.getCourseName().equals(courseName))) {
      System.out
          .println(courseService.deleteCourseByName(courseName) + " course deleted" + "\n" + MenuConstants.COURSE_MENU);
    } else {
      System.out.println(MenuConstants.NO_SUCH_COURSE + "\n" + MenuConstants.COURSE_MENU);
    }
  }

  public void showAllCoursesFacade() {
    courseService.showAllCourses().forEach(System.out::println);
    System.out.println(MenuConstants.COURSE_MENU);
  }

}
