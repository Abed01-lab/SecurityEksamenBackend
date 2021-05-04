/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import dto.PostDTO;
import dto.PostDTOWithUid;
import dto.UIDDTO;
import dto.UserInfoDTO;
import entities.Post;
import entities.UserInfo;
import facades.PostFacade;
import facades.UserInfoFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author abed
 */
public class SetupTestDB {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
        UserInfoFacade FACADE =  UserInfoFacade.getFacade(EMF);
        PostFacade postFACADE =  PostFacade.getFacade(EMF);
          
        
        List<Post> posts = new ArrayList();
        
        Post p1 = new Post("test");
        Post p2 = new Post("test2");
        
        String uid = "AqAWTnG7ZJUVwNh01TJU0mu0pBR2";
        for (PostDTO p : postFACADE.getAllPost(uid)){
            System.out.println(p.getMessage());
        }
        
        PostDTOWithUid postDTOWtihUid = new PostDTOWithUid("TestSetUP", uid);
        postFACADE.addPost(postDTOWtihUid);
    }   
}