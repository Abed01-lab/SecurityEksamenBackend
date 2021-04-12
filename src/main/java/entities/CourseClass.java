/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author abed
 */
@Entity
@Table(name = "class")
public class CourseClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private int id;
    @Column(name = "class_semester")
    private int semester;
    @Column(name = "class_number_of_students")
    private int numberOfStudents;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Course course;
    @ManyToMany(mappedBy = "classes", cascade = CascadeType.PERSIST)
    private List<Teacher> teachers;

    public CourseClass() {
    }

    public CourseClass(int semester, int numberOfStudents) {
        this.semester = semester;
        this.numberOfStudents = numberOfStudents;
        this.teachers = new ArrayList<>();
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeachers(Teacher teacher) {
        this.teachers.add(teacher);
        if (teacher != null)
            teacher.getClasses().add(this);
    }
}
