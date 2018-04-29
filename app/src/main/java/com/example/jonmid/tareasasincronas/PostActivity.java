package com.example.jonmid.tareasasincronas;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.jonmid.tareasasincronas.Adapters.PostAdapter;
import com.example.jonmid.tareasasincronas.Models.Post;
import com.example.jonmid.tareasasincronas.Parser.Json;
import com.example.jonmid.tareasasincronas.URL.HttpManager;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<Post> postList = new ArrayList<>();

    PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        recyclerView = (RecyclerView) findViewById(R.id.rcv_p);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        if (isOnLine()){
            MyPosttask myPosttask = new MyPosttask();
            myPosttask.execute("https://jsonplaceholder.typicode.com/posts");
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }

    }

    public Boolean isOnLine(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }

    public void processData(){
        postAdapter = new PostAdapter(postList, getApplicationContext());
        recyclerView.setAdapter(postAdapter);
    }


    public class MyPosttask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManager.getDataJson(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                postList = Json.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            processData();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }
}
