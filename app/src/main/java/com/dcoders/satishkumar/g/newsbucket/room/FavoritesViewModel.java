package com.dcoders.satishkumar.g.newsbucket.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {
    private FavoritesRepository repository;
    private LiveData<List<Favorites>> allfavorites;
    private Favorites favorites;
    public FavoritesViewModel(@NonNull Application application)
    {
        super(application);
        repository=new FavoritesRepository(application);
        allfavorites=repository.getAllFavorites();
    }

    public LiveData<List<Favorites>> getAllfavorites() {
        return allfavorites;
    }
    public void insert(Favorites favorites)
    {
        repository.insert(favorites);
    }
    public void delete(Favorites favorites)
    {
        repository.delete(favorites);
    }
    public Favorites checkFavinDB(String title)
    {
        return repository.checkFavorites(title);
    }
}
