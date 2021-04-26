
package com.sss.blogapp.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

@Entity(tableName = "blog_tbl")
public class Blog {

    @PrimaryKey()
    @SerializedName("id")       //server db id
    @Expose
    private Integer id;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    private String title;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose
    private String description;

    @ColumnInfo(name = "cover_photo")
    @SerializedName("cover_photo")
    @Expose
    private String coverPhoto;

    @TypeConverters(ListConverter.class)
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;


    @Embedded
    @SerializedName("author")
    @Expose
    private Author author;

    public Blog() {
    }

    //    @ColumnInfo(name = "fkAid")
//    private int fkAid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

//    public int getFkAid() {
//        return fkAid;
//    }
//
//    public void setFkAid(int fkAid) {
//        this.fkAid = fkAid;
//    }

}
