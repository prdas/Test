package com.od.wetest.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DataModel implements Serializable{

    @SerializedName("title")
    private String actionBarTitle;

    @SerializedName("rows")
    private ArrayList<Rows> rows;

    public String getActionBarTitle() {
        return actionBarTitle;
    }

    public void setActionBarTitle(String actionBarTitle) {
        this.actionBarTitle = actionBarTitle;
    }

    public ArrayList<Rows> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Rows> rows) {
        this.rows = rows;
    }

    public  class Rows {
        @SerializedName("title")
        private String title;
        @SerializedName("description")
        private String description;
        @SerializedName("imageHref")
        private String imageHref;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageHref() {
            return imageHref;
        }

        public void setImageHref(String imageHref) {
            this.imageHref = imageHref;
        }
    }
}
