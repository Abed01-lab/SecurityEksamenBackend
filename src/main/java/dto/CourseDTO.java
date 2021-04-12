/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Course;

/**
 *
 * @author abed
 */
public class CourseDTO {
    private int id;
    private String courseName;
    private String courseDescription;

    public CourseDTO() {
    }

    public CourseDTO(int id, String courseName, String courseDescription) {
        this.id = id;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }
    
    public CourseDTO(Course c){
        this.id = c.getId();
        this.courseName = c.getCourseName();
        this.courseDescription = c.getCourseDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
}
