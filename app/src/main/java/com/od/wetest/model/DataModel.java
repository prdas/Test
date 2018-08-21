package com.od.wetest.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Data model for JSON response
 */
public class DataModel implements Serializable{

    @SerializedName("title")
    private String actionBarTitle;

    @SerializedName("rows")
    private ArrayList<Rows> rows;

    /**
     * Gets action bar title.
     *
     * @return the action bar title
     */
    public String getActionBarTitle() {
        return actionBarTitle;
    }

    /**
     * Sets action bar title.
     *
     * @param actionBarTitle the action bar title
     */
    public void setActionBarTitle(String actionBarTitle) {
        this.actionBarTitle = actionBarTitle;
    }

    /**
     * Gets rows.
     *
     * @return the rows
     */
    public ArrayList<Rows> getRows() {
        return rows;
    }

    /**
     * Sets rows.
     *
     * @param rows the rows
     */
    public void setRows(ArrayList<Rows> rows) {
        this.rows = rows;
    }

    /**
     * The type Rows.
     */
    public  class Rows {
        @SerializedName("title")
        private String title;
        @SerializedName("description")
        private String description;
        @SerializedName("imageHref")
        private String imageHref;

        /**
         * Gets title.
         *
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * Sets title.
         *
         * @param title the title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * Gets description.
         *
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * Sets description.
         *
         * @param description the description
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * Gets image href.
         *
         * @return the image href
         */
        public String getImageHref() {
            return imageHref;
        }

        /**
         * Sets image href.
         *
         * @param imageHref the image href
         */
        public void setImageHref(String imageHref) {
            this.imageHref = imageHref;
        }
    }
}
