package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.List;

public class DatesCompleted extends AppCompatActivity {

    DB_Management myDB;
//    MyRecyclerViewAdapter adapter;
    List<String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates_completed);

        myDB = new DB_Management(this);
//        data = myDB.getAllDateCompleted();

    }

//    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
//
//        private List<String> mData;
//        private LayoutInflater mInflater;
//
//        // data is passed into the constructor
//        MyRecyclerViewAdapter(Context context, List<String> data) {
//            this.mInflater = LayoutInflater.from(context);
//            this.mData = data;
//        }
//
//        // inflates the row layout from xml when needed
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = mInflater.inflate(R.layout.class_recycler_view_row, parent, false);
//            return new ViewHolder(view);
//        }
//
//        // binds the data to the TextView in each row
//        @Override
//        public void onBindViewHolder(ViewHolder holder, int position) {
//            String s = mData.get(position);
//            holder.myTextView.setText(s);
//        }
//
//        // total number of rows
//        @Override
//        public int getItemCount() {
//            return mData.size();
//        }
//
//        // stores and recycles views as they are scrolled off screen
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            TextView myTextView;
//
//            ViewHolder(View itemView) {
//                super(itemView);
//                myTextView = itemView.findViewById(R.id.classType);
//
//            }
//        }
//    }
}