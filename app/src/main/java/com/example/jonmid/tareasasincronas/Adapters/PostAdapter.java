package com.example.jonmid.tareasasincronas.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jonmid.tareasasincronas.Models.Post;
import com.example.jonmid.tareasasincronas.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 29/04/2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    List<Post> postList = new ArrayList<>();
    Context context;

    public PostAdapter(List<Post> postList, Context context){
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.itempost, parent, false );
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewID.setText(String.valueOf(postList.get(position).getId()));
        holder.textViewTitle.setText(postList.get(position).getTitle());
        holder.textViewBody.setText(postList.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewID;
        TextView textViewTitle;
        TextView textViewBody;

        public ViewHolder(View item) {
            super(item);

            textViewID = (TextView) item.findViewById(R.id.tx_id);
            textViewTitle = (TextView) item.findViewById(R.id.tx_title);
            textViewBody = (TextView) item.findViewById(R.id.tx_body);

        }
    }
}
