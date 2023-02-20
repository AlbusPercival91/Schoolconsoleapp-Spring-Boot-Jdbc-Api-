package ua.foxminded.springbootjdbc.school.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchoolController {

    private final SchoolDataService dataService;

    public SchoolController(SchoolDataService dataService) {
        this.dataService = dataService;
    }
    
    @Autowired
    public void createGroup() {
        dataService.createGroup();
    }

    @Autowired
    public void createStudent() {
        dataService.createStudent();
    }
       
    @Autowired
    public void createCourse() {
        dataService.createCourse();
    }
    
    @Autowired
    public void createCourseStudentRelation() {
        dataService.createCourseStudentRelation();
    }
}
