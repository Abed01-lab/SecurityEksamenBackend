/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.AddClassToCourseDTO;
import dto.CourseClassDTO;
import dto.CourseDTO;
import entities.Course;
import entities.CourseClass;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author abed
 */
public class CourseFacade {
    private static CourseFacade instance;
    private static EntityManagerFactory emf;
    
    private CourseFacade(){}
    
     public static CourseFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CourseFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //opret kurser
    public void addCourse(CourseDTO coursedto){
        EntityManager em = getEntityManager();
        Course course = new Course(coursedto.getCourseName(), coursedto.getCourseDescription());
        try{
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    //se en liste af kurser
    public List<CourseDTO> getListCourses(){
        EntityManager em = getEntityManager();
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c", Course.class);
        List<CourseDTO> coursesDTO = new ArrayList<>();
        for (Course c : query.getResultList()){
            coursesDTO.add(new CourseDTO(c.getId(), c.getCourseName(), c.getCourseDescription()));
        }
        return coursesDTO;
    }
    //edit kurser
    public void updateCourse(CourseDTO coursedto){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Course courseToUpdate = em.find(Course.class, coursedto.getId());
            courseToUpdate.setCourseDescription(coursedto.getCourseDescription());
            courseToUpdate.setCourseName(coursedto.getCourseName());
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    //tilføj classer til kurser
    public void addClassToCourses(AddClassToCourseDTO id){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Course course = em.find(Course.class, id.getCourseId());
            CourseClass courseClass = em.find(CourseClass.class, id.getCourseClassId());
            course.addCourseClasses(courseClass);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
