package com.example.booksappmvvm.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.booksappmvvm.Models.Book;
import com.example.booksappmvvm.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BooksAdapter extends ArrayAdapter<Book> {
    public BooksAdapter(@NonNull Context context, @NonNull List<Book> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.booklistviewlayout, parent, false);
        Book book = getItem(position);
        ImageView img = convertView.findViewById(R.id.listBookImageView);
        Picasso.with(getContext()).load(book.getLink()).into(img);
        TextView name = convertView.findViewById(R.id.listBookName);
        TextView author = convertView.findViewById(R.id.listBookAuthor);
        name.setText(book.getName());
        author.setText(book.getAuthor());
        return convertView;
    }
}
