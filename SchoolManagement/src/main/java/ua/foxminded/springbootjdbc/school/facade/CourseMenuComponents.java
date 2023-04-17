package ua.foxminded.springbootjdbc.school.facade;

import java.util.Scanner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ua.foxminded.springbootjdbc.school.dao.CourseService;
import ua.foxminded.springbootjdbc.school.entity.Course;

@Slf4j
@Component
public class CourseMenuComponents {
  private final CourseService courseService;

  public CourseMenuComponents(CourseService courseService) {
    this.courseService = courseService;
  }

  public void findCoursesWithLessOrEqualsStudentsFacade(Scanner scan) {
    log.info(MenuConstants.NUMBER_OF_STUDENTS);

    if (scan.hasNextInt()) {
      int quant = scan.nextInt();
      courseService.findCoursesWithLessOrEqualsStudents(quant).stream().map(Course::toString)
          .forEach(System.out::println);
    } else {
      log.warn(MenuConstants.DIGITS_REQUIRED);
      log.info(MenuConstants.COURSE_MENU);
    }
  }

  public void createCourseFacade(Scanner scan) {
    log.info(MenuConstants.COURSE_NAME);
    String courseName = scan.nextLine();

    if (!courseName.isEmpty()
        && courseService.showAllCourses().stream().noneMatch(course -> course.getCourseName().equals(courseName))) {
      log.info(MenuConstants.COURSE_DESCRIPTION);
      String courseDescription = scan.nextLine();
      Course course = new Course(courseName, courseDescription);
      log.info(courseService.createCourse(course) + " course added" + "\n" + MenuConstants.COURSE_MENU);
    } else {
      log.warn(MenuConstants.EMPTY_NOTE);
      log.info(MenuConstants.COURSE_MENU);
    }
  }

  public void editCourseNameAndDescriptionFacade(Scanner scan) {
    log.info(MenuConstants.CHOOSE_COURSE_NAME);
    courseService.showAllCourses().forEach(System.out::println);
    String courseName = scan.nextLine();

    if (courseService.showAllCourses().stream().anyMatch(course -> course.getCourseName().equals(courseName))) {
      log.info(MenuConstants.NEW_COURSE_NAME);
      String newCourseName = scan.nextLine();

      if (!newCourseName.isEmpty()) {
        log.info(MenuConstants.COURSE_DESCRIPTION);
        String newCourseDescription = scan.nextLine();
        log.info(courseService.editCourseNameAndDescription(courseName, newCourseName, newCourseDescription)
            + " course name and description updated" + "\n" + MenuConstants.COURSE_MENU);
      } else {
        log.warn(MenuConstants.EMPTY_NOTE);
        log.info(MenuConstants.COURSE_MENU);
      }
    } else {
      log.warn(MenuConstants.NO_SUCH_COURSE);
      log.info(MenuConstants.COURSE_MENU);
    }
  }

  public void deleteCourseByNameFacade(Scanner scan) {
    log.info(MenuConstants.CHOOSE_COURSE_NAME);
    courseService.showAllCourses().forEach(System.out::println);
    String courseName = scan.nextLine();

    if (courseService.showAllCourses().stream().anyMatch(course -> course.getCourseName().equals(courseName))) {
      log.info(courseService.deleteCourseByName(courseName) + " course deleted" + "\n" + MenuConstants.COURSE_MENU);
    } else {
      log.warn(MenuConstants.NO_SUCH_COURSE + "\n" + MenuConstants.COURSE_MENU);
    }
  }

  public void showAllCoursesFacade() {
    courseService.showAllCourses().forEach(System.out::println);
    log.info(MenuConstants.COURSE_MENU);
  }

}
