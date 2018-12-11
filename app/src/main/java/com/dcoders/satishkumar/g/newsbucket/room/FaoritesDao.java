package com.dcoders.satishkumar.g.newsbucket.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface FaoritesDao {
    @Insert(onConflict = REPLACE)
    void insert(Favorites favorites);

    @Delete
    void delete(Favorites favorites);

    @Query("SELECT * FROM Favorites")
    LiveData<List<Favorites>> getAllFavorites();

    @Query("SELECT * FROM Favorites WHERE title = :title LIMIT 1")
    Favorites checkinDB(String title);
}
