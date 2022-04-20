package com.myapps.bottomnavigationbarfragments;

import androidx.annotation.NonNull;

public class TransactionModel {
    private Integer id;
    private Integer amount;
    private String description;
    private String category;
    private String type;
    private String date;
    private String time;

    @NonNull
    @Override
    public String toString() {
        if (date.length() == 8)
            return "                                                              ₹ " + amount +
                    "\n " + description +
                    "\n on " + date +
                    " @ " + time +
                    "                " + type + "\n" + " "+ category

                    ;
        else if (date.length() == 9)
            return "                                                              ₹ " + amount +
                    "\n " + description +
                    "\n on " + date +
                    " @ " + time +
                    "              " + type + "\n" + " "+ category

                    ;
        else
            return "                                                              ₹ " + amount +
                    "\n " + description +
                    "\n on " + date +
                    " @ " + time +
                    "             " + type + "\n" + " "+ category

                    ;
    }

    public TransactionModel(Integer id, Integer amount, String description, String category, String type, String date, String time) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.type = type;
        this.date = date;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
