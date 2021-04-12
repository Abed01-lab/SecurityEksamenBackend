/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author abed
 */
public class AddClassToCourseDTO {
    private int courseId;
    private int courseClassId;

    public AddClassToCourseDTO() {
    }

    public AddClassToCourseDTO(int courseId, int courseClassId) {
        this.courseId = courseId;
        this.courseClassId = courseClassId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseClassId() {
        return courseClassId;
    }

    public void setCourseClassId(int courseClassId) {
        this.courseClassId = courseClassId;
    }
}
