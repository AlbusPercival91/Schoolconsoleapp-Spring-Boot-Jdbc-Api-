package ua.foxminded.springbootjdbc.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ua.foxminded.springbootjdbc.school.facade.ConsoleMenuManager;
import ua.foxminded.springbootjdbc.school.testdata.dao.TestDataService;

@Component
public class DatabaseInitializer implements ApplicationRunner {

  @Autowired
  private TestDataService testData;

  @Autowired
  private ConsoleMenuManager consoleManager;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (testData.databaseIsEmpty()) {
      testData.createGroup();
      testData.createStudent();
      testData.createCourse();
      testData.createCourseStudentRelation();
      System.out.println("created data\n\n");
    }
    consoleManager.manage();
  }

}
