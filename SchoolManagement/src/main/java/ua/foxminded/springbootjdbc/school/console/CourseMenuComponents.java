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

  public void findCoursesWithLessOrEqualsStudents(Scanner scan) {
    System.out.println(MenuConstants.NUMBER_OF_STUDENTS);

    if (scan.hasNextInt()) {
      int quant = scan.nextInt();
      courseService.findCoursesWithLessOrEqualsStudents(quant).stream().map(Course::toString)
          .forEach(System.out::println);
    } else {
      System.out.println(MenuConstants.DIGITS_REQUIRED + "\n" + MenuConstants.COURSE_MENU);
    }
  }
  
  
}
