package com.example.booksappmvvm.ViewModels;

import android.content.Context;

import com.example.booksappmvvm.Models.Book;
import com.example.booksappmvvm.Repos.BooksListRepo;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListViewModel extends ViewModel {
   private MutableLiveData<ArrayList<Book>>books;
   private BooksListRepo repo;
   private String search;
  private Context context;
    public ListViewModel() {

    }
    public void init(String search,Context context)
    {
        this.search = search;
        this.context = context;
        repo = new BooksListRepo(search,context);
        books = repo.getData();
    }

    public MutableLiveData<ArrayList<Book>> getBooks() {
        return books;
    }
}
