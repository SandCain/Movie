package com.example.mymoviese.Data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {

    private static MoviesDatabase database;
    private final LiveData<List<Movie>> movies;
    private final LiveData<List<FavoriteMovies>> favoriteMovies;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = MoviesDatabase.getInstance(getApplication());
        movies = database.moviesDao().getAllMovies();
        favoriteMovies = database.moviesDao().getAllFavoriteMovies();
    }

    public FavoriteMovies getFavouriteMovieById(int id) {
        try {
            return new GetFavouriteMovieTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Movie getMovieById(int id) {
        try {
            return new GetMovieTask().execute(id).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<List<FavoriteMovies>> getFavouriteMovies() {
        return favoriteMovies;
    }

    public void deleteAllMovies() {
        new DeleteMoviesTask().execute();
    }

    public void insertMovie(Movie movie) {
        new InsertTask().execute(movie);
    }

    public void deleteFavouriteMovie(FavoriteMovies movie) {
        new DeleteFavouriteTask().execute(movie);
    }

    public void insertFavouriteMovie(FavoriteMovies movie) {
        new InsertFavouriteTask().execute(movie);
    }

    public void deleteMovie(Movie movie) {
        new DeleteTask().execute(movie);
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    private static class DeleteTask extends AsyncTask<Movie, Void, Void> {
        @Override
        protected Void doInBackground(Movie... movies) {
            if (movies != null && movies.length > 0) {
                return database.moviesDao().DeleteAllMovies();
            }
            return null;
        }
    }

    private static class InsertFavouriteTask extends AsyncTask<FavoriteMovies, Void, Void> {
        @Override
        protected Void doInBackground(FavoriteMovies... movies) {
            if (movies != null && movies.length > 0) {
                database.moviesDao().insertFavoriteMovie(movies[0]);
            }
            return null;
        }
    }

    private static class DeleteMoviesTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... integers) {
            database.moviesDao().DeleteAllMovies();
            return null;
        }
    }

    private static class GetMovieTask extends AsyncTask<Integer, Void, Movie> {
        @Override
        protected Movie doInBackground(Integer... integers) {
            if (integers != null && integers.length > 0) {
                return database.moviesDao().getMovieByID(integers[0]);
            }
            return null;
        }
    }
    private static class DeleteFavouriteTask extends AsyncTask<FavoriteMovies, Void, Void> {
        @Override
        protected Void doInBackground(FavoriteMovies... movies) {
            if (movies != null && movies.length > 0) {
                database.moviesDao().DeleteFavoriteMovie(movies[0]);
            }
            return null;
        }
    }

    private static class InsertTask extends AsyncTask<Movie, Void, Void> {
        @Override
        protected Void doInBackground(Movie... movies) {
            if (movies != null && movies.length > 0) {
                return database.moviesDao().insertMovie(movies[0]);
            }
            return null;
        }
    }

    private static class GetFavouriteMovieTask extends AsyncTask<Integer, Void, FavoriteMovies> {
        @Override
        protected FavoriteMovies doInBackground(Integer... integers) {
            if (integers != null && integers.length > 0) {
                return database.moviesDao().getFMovieByID(integers[0]);
            }
            return null;
        }
    }
}
