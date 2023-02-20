package ua.foxminded.springbootjdbc.school.testdatadao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDataController {
    
    @Autowired
    public void createGroup(TestDataService dataService) {
        dataService.createGroup();
    }

    @Autowired
    public void createStudent(TestDataService dataService) {
        dataService.createStudent();
    }
       
    @Autowired
    public void createCourse(TestDataService dataService) {
        dataService.createCourse();
    }
    
    @Autowired
    public void createCourseStudentRelation(TestDataService dataService) {
        dataService.createCourseStudentRelation();
    }
}
