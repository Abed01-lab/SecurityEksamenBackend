/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author abedh
 */
public class PostDTOWithUid {
    private String message;
    private String uid;

    public PostDTOWithUid() {
    }

    public PostDTOWithUid(String message, String uid) {
        this.message = message;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
