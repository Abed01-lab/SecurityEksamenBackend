/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.AddTeacherToClassDTO;
import dto.CourseClassDTO;
import dto.CourseClassWithInfoDTO;
import dto.CourseDTO;
import entities.CourseClass;
import entities.Teacher;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author abed
 */
public class CourseClassFacade {
    private static CourseClassFacade instance;
    private static EntityManagerFactory emf;
    
    private CourseClassFacade(){}
    
     public static CourseClassFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CourseClassFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //opret klasser
    public void addClass(CourseClassDTO courseClassDTO){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(new CourseClass(courseClassDTO.getSemester(), courseClassDTO.getNumberOfStudents()));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    //se alle klasser
    public List<CourseClassDTO> getAllClasses(){
        EntityManager em = getEntityManager();
        TypedQuery<CourseClass> query = em.createQuery("SELECT c FROM CourseClass c", CourseClass.class);
        List<CourseClassDTO> classes = new ArrayList<>();
        for (CourseClass cc : query.getResultList()){
            System.out.println(cc);
            classes.add(new CourseClassDTO(cc.getId(), cc.getSemester(), cc.getNumberOfStudents()));
        }
    return classes;
    }
    
    //add teacher to class
    public void addTeacherToClass(AddTeacherToClassDTO id){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            CourseClass cc = em.find(CourseClass.class, id.getClassId());
            Teacher t = em.find(Teacher.class, id.getTeacherId());
            cc.addTeachers(t);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    //se klasser med mere info
    public List<CourseClassWithInfoDTO> getAllClassesWithInfo(){
        EntityManager em = getEntityManager();
        TypedQuery<CourseClass> query = em.createQuery("SELECT c FROM CourseClass c", CourseClass.class);
        List<CourseClassWithInfoDTO> classes = new ArrayList<>();
        for (CourseClass cc : query.getResultList()){
            classes.add(new CourseClassWithInfoDTO(cc));
        }
    return classes;
    }
}
