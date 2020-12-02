package com.mt.time.detail;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.mt.time.R;


public class DetailActivity extends AppCompatActivity {

    private RecyclerView mDetailRV;
    private DetailAdapter mAdapter;
    private ProgressBar mProgressBar;

    private static final String TAG = "DetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mDetailRV = (RecyclerView) findViewById(R.id.id_detail_rv);
        mProgressBar = (ProgressBar) findViewById(R.id.id_pb);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
        mProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.GONE);
                showDetail();
            }
        } , 500);

    }

    private void showDetail(){
        mAdapter = new DetailAdapter(this);
        mDetailRV.setAdapter(mAdapter);
        mDetailRV.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.VERTICAL_LIST));
        mDetailRV.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: " );
    }
}
