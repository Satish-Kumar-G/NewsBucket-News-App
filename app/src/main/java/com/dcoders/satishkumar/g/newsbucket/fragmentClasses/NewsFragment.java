package com.dcoders.satishkumar.g.newsbucket.fragmentClasses;

import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dcoders.satishkumar.g.newsbucket.BuildConfig;
import com.dcoders.satishkumar.g.newsbucket.R;
import com.dcoders.satishkumar.g.newsbucket.adapters.NewsArticleAdapter;
import com.dcoders.satishkumar.g.newsbucket.mainDataClasses.MyDataClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.dcoders.satishkumar.g.newsbucket.ChannelsDisplayActivity.CHANNELKEY;

public class NewsFragment extends Fragment {

    public static final String BASEURL="https://newsapi.org/v2/top-headlines?sources=";
    public static final String ENDURL="&apiKey=";
    public static final String POSITIONKEY="POSITION";
    public static final String LISTKEY="LISTKEY";


    List<MyDataClass> myDataClassList=new ArrayList<>();
    String channel;
    RecyclerView articleRecycler;
    ProgressBar articleprogressbar;
    LinearLayoutManager linearLayoutManager;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           channel=getArguments().getString(CHANNELKEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        articleRecycler = view.findViewById(R.id.news_fragment_recycler_view);
        articleprogressbar=view.findViewById(R.id.news_fragment_progressBar);
        linearLayoutManager=new LinearLayoutManager(getContext());
        if (savedInstanceState!=null)
        {

            articleprogressbar.setVisibility(View.GONE);
            myDataClassList= (List<MyDataClass>) savedInstanceState.getSerializable(LISTKEY);
            int position=savedInstanceState.getInt(POSITIONKEY,0);
            articleRecycler.setAdapter(new NewsArticleAdapter(getContext(),myDataClassList));
            articleRecycler.setLayoutManager(linearLayoutManager);
            articleRecycler.getLayoutManager().scrollToPosition(position);
        }
        else {
            loadData();

        }
        return view;
    }
    public void loadData()
    {
        articleprogressbar.setVisibility(View.VISIBLE);
        String url=BASEURL+channel+ENDURL+BuildConfig.APIKEY;
        RequestQueue queue= Volley.newRequestQueue(getContext());
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               articleprogressbar.setVisibility(View.GONE);
                try {
                    JSONObject main=new JSONObject(response);
                    JSONArray array=main.optJSONArray("articles");
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.optJSONObject(i);
                        String author=object.optString("author");
                        String title=object.optString("title");
                        String description=object.optString("description");
                        String url=object.optString("url");
                        String imageUrl=object.optString("urlToImage");
                        String publishedAt=object.optString("publishedAt");
                        MyDataClass dataClass=new MyDataClass(author,title,description,url,imageUrl,publishedAt);
                        myDataClassList.add(dataClass);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                articleRecycler.setAdapter(new NewsArticleAdapter(getContext(),myDataClassList));
                articleRecycler.setLayoutManager(linearLayoutManager);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(LISTKEY, (Serializable) myDataClassList);
        if (linearLayoutManager!=null) {
            int position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            outState.putInt(POSITIONKEY,position);
        }

    }
}
