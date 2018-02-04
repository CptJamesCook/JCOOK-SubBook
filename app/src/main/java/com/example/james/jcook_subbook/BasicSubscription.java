package com.example.james.jcook_subbook;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.jar.Attributes;

/**
 * Created by james on 02/02/18.
 */

public class BasicSubscription {

    @Expose
    private String name;

    @Expose
    private Date date;

    @Expose
    private float cost;

    @Expose
    private String comment;

    public BasicSubscription(){
        this.name = "BasicSubscription";
        this.date = new Date();
        this.cost = 0.0f;
        this.comment = "A comment";
    }

    public BasicSubscription(String name, Date date, float cost, String comment){
        this.setName(name);
        this.setDate(date);
        this.setCost(cost);
        try {
            this.setComment(comment);
        } catch (CommentTooLongException e) {
            System.out.println("Comment too long.");
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }

    public void setCost(float cost){
        this.cost = cost;
    }
    public float getCost(){
        return this.cost;
    }

    public void setComment(String comment) throws CommentTooLongException{
        if(comment.length() > 30){
            throw new CommentTooLongException();
        }
        this.comment = comment;
    }
    public String getComment(){
        return this.comment;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
