package com.dcoders.satishkumar.g.newsbucket.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

@Database(entities = {Favorites.class},version = 1,exportSchema = false)
public abstract class FavoritesDatabase extends RoomDatabase {
    public abstract FaoritesDao faoritesDao();
    private static FavoritesDatabase INSTANCE;


    public static FavoritesDatabase getDataBase(final Context context)
    {
        if (INSTANCE==null)
        {
            synchronized (FavoritesDatabase.class)
            {
                if (INSTANCE==null)
                {
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            FavoritesDatabase.class,"favorites.db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .addCallback(sRoomDatabase)
                            .build();
                }
            }

        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabase=new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new populateDB(INSTANCE).execute();
        }

    };
    private static class populateDB extends AsyncTask<Void,Void,LiveData<List<Favorites>>>{
        public   FaoritesDao mDao;
        populateDB(FavoritesDatabase db)
        {
            mDao=db.faoritesDao();
        }

        @Override
        protected LiveData<List<Favorites>> doInBackground(Void... voids) {
            return mDao.getAllFavorites();
        }
    }

}
