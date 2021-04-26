package com.sss.blogapp.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface BlogDao {

    @Insert(onConflict = REPLACE)
    long insert(Blog blog);

    @Delete
    void delete(Blog blog);

    @Delete
    void reset(List<Blog> blogList);

    @Query("SELECT * FROM blog_tbl ")
    LiveData<List<Blog>> getAllBlog();

    @Query("SELECT * FROM blog_tbl WHERE id IN ( SELECT max( id ) FROM blog_tbl )")
    LiveData<Blog> lastEntry();

    @Query("SELECT id FROM blog_tbl ORDER BY id DESC LIMIT 1")
    Integer lastRowId();

    @Query("SELECT aid FROM blog_tbl ORDER BY aid DESC LIMIT 1")
    Integer lastAuthorId();


//    @Insert(onConflict = REPLACE)
//    void insertAuthor(Author author);
//
//    @Delete
//    void deleteAuthor(Author author);
//
//    @Delete
//    void resetAuthor(List<Author> authors);
//
//    @Query("SELECT * FROM blog_tbl ")
//    LiveData<List<Author>> getAllAuthors();
//
//
//    @Insert(onConflict = REPLACE)
//    void insertCat(Author author);
//
//    @Delete
//    void deleteCat(Author author);
//
//    @Delete
//    void resetCat(List<Author> authors);
//
//    @Query("SELECT * FROM blog_tbl ")
//    LiveData<List<Category>> getAllCategories();

}
