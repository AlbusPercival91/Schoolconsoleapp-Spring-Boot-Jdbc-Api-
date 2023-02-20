package ua.foxminded.springbootjdbc.school.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchoolController {
    
    @Autowired
    public void createGroup(SchoolDataService dataService) {
        dataService.createGroup();
    }

    @Autowired
    public void createStudent(SchoolDataService dataService) {
        dataService.createStudent();
    }
       
    @Autowired
    public void createCourse(SchoolDataService dataService) {
        dataService.createCourse();
    }
    
    @Autowired
    public void createCourseStudentRelation(SchoolDataService dataService) {
        dataService.createCourseStudentRelation();
    }
}
