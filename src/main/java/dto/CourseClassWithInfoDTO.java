/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Course;
import entities.CourseClass;
import entities.Teacher;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abed
 */
public class CourseClassWithInfoDTO{
    
    private int id;
    private int semester;
    private int numberOfStudents;
    
    private CourseDTO courseDTO;
    
    private List<TeacherDTO> teachers;

    public CourseClassWithInfoDTO() {
    }

    public CourseClassWithInfoDTO(int id, int semester, int numberOfStudents) {
        this.id = id;
        this.semester = semester;
        this.numberOfStudents = numberOfStudents;
    }
    
    public CourseClassWithInfoDTO(CourseClass cc){
        this.id = cc.getId();
        this.semester = cc.getSemester();
        this.numberOfStudents = cc.getNumberOfStudents();
        this.courseDTO = new CourseDTO(cc.getCourse());
        teacherToTeacherDTO(cc.getTeachers());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public List<TeacherDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherDTO> teachers) {
        this.teachers = teachers;
    }
    
    private void teacherToTeacherDTO(List<Teacher> teachers){
        this.teachers = new ArrayList<>();
        for (Teacher t : teachers){
            this.teachers.add(new TeacherDTO(t));
        }
    }
}
