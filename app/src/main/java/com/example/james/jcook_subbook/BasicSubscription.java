/*
 * Copyright 2018 James Cook
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
     * @return the subscriptions date
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
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd");
        return this.name
                + ", Date: " + dateFormatter.format(this.date)
                + ", Cost: " + String.valueOf(this.cost);
    }
}
