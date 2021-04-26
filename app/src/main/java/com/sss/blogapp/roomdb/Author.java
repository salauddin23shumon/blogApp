
package com.sss.blogapp.roomdb;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Author implements Serializable {


    @ColumnInfo(name = "aid")
    @SerializedName("id")
    @Expose
    private Integer id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    @ColumnInfo(name = "avatar")
    @SerializedName("avatar")
    @Expose
    private String avatar;

    @ColumnInfo(name = "profession")
    @SerializedName("profession")
    @Expose
    private String profession;

    public Author() {
    }

    //    public Author(Integer id, String name, String avatar, String profession) {
//        this.id = id;
//        this.name = name;
//        this.avatar = avatar;
//        this.profession = profession;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

}
