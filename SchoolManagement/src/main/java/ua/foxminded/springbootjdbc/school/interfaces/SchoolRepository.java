package ua.foxminded.springbootjdbc.school.interfaces;

import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.entity.StudentCourseRelation;

public interface SchoolRepository {

  int createStudent(Student student);

  int createGroup(Group group);

  int createCourse(Course course);

  int createCourseStudentRelation(StudentCourseRelation scRelation);
}
