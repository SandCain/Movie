package com.example.mymoviese.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface movieDao {

    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * FROM favoritemovies")
    LiveData<List<FavoriteMovies>> getAllFavoriteMovies();

    @Query("SELECT * FROM movies WHERE id ==:movieID")
    Movie getMovieByID(int movieID);

    @Query("DELETE FROM movies")
    Void DeleteAllMovies();

    @Insert
    Void insertMovie(Movie movie);

    @Delete
    void DeleteMovie(Movie movie);

    @Insert
    void insertFavoriteMovie(FavoriteMovies movie);

    @Delete
    void DeleteFavoriteMovie(FavoriteMovies movie);

    @Query("SELECT * FROM favoritemovies WHERE id ==:movieID")
    FavoriteMovies getFMovieByID(int movieID);

}
