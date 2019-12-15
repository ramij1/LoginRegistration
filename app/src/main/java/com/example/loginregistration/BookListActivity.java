package com.example.loginregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {


    private static final String TAG = "BookListActivity";

    private Context mContext;

    ArrayList<String> titleArrayList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);


        mContext = BookListActivity.this;

        titleArrayList = new ArrayList<String>();

        titleArrayList.add(Constants.WHAT_IS_JAVA);
        titleArrayList.add(Constants.HISTRY_OF_JAVA);
        titleArrayList.add(Constants.FEATURES_OF_JAVA);
        titleArrayList.add(Constants.C_VS_JAVA);
        titleArrayList.add(Constants.HELLO_JAVA_PROGRAM);
        titleArrayList.add(Constants.PROGRAM_INTERNAL);
        titleArrayList.add(Constants.HOW_TO_SET_PATH);
        titleArrayList.add(Constants.JDK_JRE_JVM);
        titleArrayList.add(Constants.INTERNAL_DETAILES_OF_JVM);
        titleArrayList.add(Constants.JAVA_VERIABLE);
        titleArrayList.add(Constants.JAVA_DATA_TYPES);





        mRecyclerView = findViewById(R.id.title_layout_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        TitleAdapter adapter = new TitleAdapter(mContext, titleArrayList, new CustomItemClickListner() {
            @Override
            public void OnItemClick(View v, int position) {

                Intent intent = new Intent(mContext, DescriptionActivity.class);
                intent.putExtra("titles", titleArrayList.get(position));
                startActivity(intent);

                Toast.makeText(mContext, "Clicked "+titleArrayList.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(adapter);
    }
}
