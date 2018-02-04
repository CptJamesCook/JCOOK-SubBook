package com.example.james.jcook_subbook;

import com.google.gson.annotations.Expose;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The base class of a subscription.
 *
 * @author James Cook
 * @version 0.0
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

    @Expose
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd");

    /**
     * Constructs a subscription with default values.
     */
    public BasicSubscription(){
        this.name = "BasicSubscription";
        this.date = new Date();
        this.cost = 0.0f;
        this.comment = "A comment";
    }

    /**
     * Constructs a subscription with passed in values.
     *
     * @param name the subscriptions name
     * @param date the subscriptions date
     * @param cost the subscriptions cost
     * @param comment the subscriptions comment
     */
    public BasicSubscription(String name, Date date, float cost, String comment){
        this.setName(name);
        this.setDate(date);
        this.setCost(cost);
        this.setComment(comment);
    }

    /**
     * Set the subscription name.
     *
     * @param name the name of the subscription
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Get the subscriptions name.
     *
     * @return the subscription name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Set the subscriptions date.
     *
     * @param date the subscriptions date
     */
    public void setDate(Date date){
        this.date = date;
    }

    /**
     * Get the subscriptions date
     *
     * @return the subsciptions date
     */
    public Date getDate(){
        return this.date;
    }

    /**
     * Set the subscriptions cost.
     *
     * @param cost the subscriptions cost
     */
    public void setCost(float cost){
        this.cost = cost;
    }

    /**
     * Get the subscriptions cost
     *
     * @return the subscriptions cost
     */
    public float getCost(){
        return this.cost;
    }

    /**
     * Set the subscriptions comment.
     *
     * @param comment the subscriptions comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Get the subscriptions comment
     *
     * @return the subscriptions comment
     */
    public String getComment(){
        return this.comment;
    }

    /**
     * Define behavior for conversion to a string.
     *
     * @return a string representation of the subscription
     */
    @Override
    public String toString(){
        return this.name;
                //+ ", Date: " + dateFormatter.format(this.date)
                //+ ", Cost: " + String.valueOf(this.cost);
    }
}
