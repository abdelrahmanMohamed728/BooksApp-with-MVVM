package com.example.booksappmvvm.Repos;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.booksappmvvm.Models.Book;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;

public class BooksListRepo {
    private ArrayList<Book> books ;
    String url;
    String search;
    Context context;

    public BooksListRepo(String search,Context context) {
        this.search = search;
        this.context=context;
        url="https://www.googleapis.com/books/v1/volumes?q="+search;
    }

    public MutableLiveData<ArrayList<Book>> getData()
    {
        setData();
        MutableLiveData<ArrayList<Book>> data = new MutableLiveData<>();
        data.setValue(books);
        return data;
    }
    private void setData(){
        books = new ArrayList<>();
        {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>(){
                @Override
                public void onResponse(JSONObject response) {
                    try
                    {
                        JSONArray array = response.getJSONArray("items");
                        for (int i =0;i<array.length();i++)
                        {
                            JSONObject object = array.getJSONObject(i);
                            String id = object.getString("id");
                            JSONObject details = object.getJSONObject("volumeInfo");
                            String name = details.getString("title");

                            JSONArray author = details.getJSONArray("authors");
                            String authorName = author.getString(0);
                            JSONObject imageLinks = details.getJSONObject("imageLinks");
                            String link = imageLinks.getString("thumbnail");
                            books.add(new Book(id,name,authorName,link,"a","none"));
                        }

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Log.e("tag",e.toString());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                }
            });
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(request);
        }
    }
}
