package com.dcoders.satishkumar.g.newsbucket.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class FavoritesRepository {
    private FaoritesDao mDao;
    private LiveData<List<Favorites>> allFavorites;

    public FavoritesRepository(Application application) {
        FavoritesDatabase db=FavoritesDatabase.getDataBase(application);
        mDao=db.faoritesDao();
        allFavorites=mDao.getAllFavorites();
    }
    LiveData<List<Favorites>> getAllFavorites()
    {
        return allFavorites;
    }
    public Favorites checkFavorites(String title)
    {
        Favorites favorites=mDao.checkinDB(title);
        return favorites;
    }
    public void insert(Favorites favorites)
    {
        new insetAsync(mDao).execute(favorites);
    }
    public void delete(Favorites favorites)
    {
        new deleteAsync(mDao).execute(favorites);
    }
    public static class insetAsync extends AsyncTask<Favorites,Void,Void>
    {
        private FaoritesDao asyncTaskdao;
        insetAsync(FaoritesDao dao)
        {
            asyncTaskdao=dao;
        }

        @Override
        protected Void doInBackground(Favorites... favorites)
        {
            asyncTaskdao.insert(favorites[0]);
            return null;
        }
    }
    public static class deleteAsync extends AsyncTask<Favorites,Void,Void>
    {
        private FaoritesDao ayncDao;
        deleteAsync(FaoritesDao dao)
        {
            ayncDao=dao;
        }

        @Override
        protected Void doInBackground(Favorites... favorites) {
            ayncDao.delete(favorites[0]);
            return null;
        }
    }
}
