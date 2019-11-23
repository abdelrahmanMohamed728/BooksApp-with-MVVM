package com.example.booksappmvvm.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;

import com.example.booksappmvvm.Adapters.BooksAdapter;
import com.example.booksappmvvm.Models.Book;
import com.example.booksappmvvm.R;
import com.example.booksappmvvm.ViewModels.ListViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   SearchView searchView;
   ListView listView;
   ListViewModel viewModel;
   BooksAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        listView = findViewById(R.id.listBooks);
        searchView =findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.init(newText,MainActivity.this);
                viewModel.getBooks().observe(MainActivity.this, new Observer<ArrayList<Book>>() {
                    @Override
                    public void onChanged(ArrayList<Book> books) {

                        initListView();
                        adapter.notifyDataSetChanged();
                    }
                });
                return true;
            }
        });
    }
    public void initListView()
    {
     adapter = new BooksAdapter(this,viewModel.getBooks().getValue());
     listView.setAdapter(adapter);
    }
}
