/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import dto.UserInfoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author abedh
 */
@Entity
@Table(name = "userInfo")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userInfo_id")
    private int id;
    @Column(name = "userInfo_uid")
    private String uid;
    @Column(name = "userInfo_name")
    private String name;
    @Column(name = "userInfo_phone_number")
    private int phoneNumber;
    @Column(name = "userInfo_sex")
    private String sex;
    
    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.PERSIST)
    private List<Post> posts;

    public UserInfo() {
    }
    
    
    public UserInfo(String uid, String name, int phoneNumber, String sex) {
        this.uid = uid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.posts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        this.posts.add(post);
        if(post != null)
            post.setUserInfo(this);
    }
    
    

}
