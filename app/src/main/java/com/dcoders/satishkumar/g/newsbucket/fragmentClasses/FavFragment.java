package com.dcoders.satishkumar.g.newsbucket.fragmentClasses;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcoders.satishkumar.g.newsbucket.R;
import com.dcoders.satishkumar.g.newsbucket.adapters.NewsArticleAdapter;
import com.dcoders.satishkumar.g.newsbucket.mainDataClasses.MyDataClass;
import com.dcoders.satishkumar.g.newsbucket.room.Favorites;
import com.dcoders.satishkumar.g.newsbucket.room.FavoritesViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FavFragment extends Fragment {
    public static final String POSITIONKEY="POSITION";
    public static final String LISTKEY="LISTKEY";
    FavoritesViewModel viewModel;
    List<MyDataClass> dataClasses;
    RecyclerView favRecycler;


    public FavFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fav, container, false);

        favRecycler=view.findViewById(R.id.fav_recycler_view);
        dataClasses=new ArrayList<>();
        if (savedInstanceState!=null)
        {
            int position=savedInstanceState.getInt(POSITIONKEY,0);
            dataClasses= (List<MyDataClass>) savedInstanceState.getSerializable(LISTKEY);
            favRecycler.setAdapter(new NewsArticleAdapter(getContext(),dataClasses));
            favRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            favRecycler.getLayoutManager().scrollToPosition(position);

        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel= ViewModelProviders.of((FragmentActivity) getContext()).get(FavoritesViewModel.class);
        viewModel.getAllfavorites().observe(this, new Observer<List<Favorites>>() {
            @Override
            public void onChanged(@Nullable List<Favorites> favorites) {
                if (dataClasses!=null)
                {
                    dataClasses.clear();
                }
                for (int i=0;i<favorites.size();i++)
                {
                    String title=favorites.get(i).getTitle();
                    String desc=favorites.get(i).getDescription();
                    String author=favorites.get(i).getAuthor();
                    String imagelink=favorites.get(i).getImageUrl();
                    String link=favorites.get(i).getLink();
                    MyDataClass dataClass=new MyDataClass(author,title,desc,link,imagelink,null);
                    dataClasses.add(dataClass);

                }
                favRecycler.setAdapter(new NewsArticleAdapter(getContext(),dataClasses));
                favRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(LISTKEY, (Serializable) dataClasses);
        int position=((LinearLayoutManager)favRecycler.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        outState.putInt(POSITIONKEY,position);
    }
}
