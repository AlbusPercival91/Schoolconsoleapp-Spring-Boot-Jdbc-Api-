package ua.foxminded.springbootjdbc.school.entity;

public class Student {
    private Integer groupId;
    private String firstName;
    private String lastName;

    public Student(Integer groupId, String firstName, String lastName) {
        super();
        this.groupId = groupId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Student group ID = " + groupId + ", first name = " + firstName + ", last name = " + lastName;
    }
}
