package com.promoteprovider.cartoonkids.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.promoteprovider.cartoonkids.Fragments.VideoFragment;
import com.promoteprovider.cartoonkids.Models.CartoonModel;
import com.promoteprovider.cartoonkids.R;

import java.util.ArrayList;

public class CartoonAdapter extends RecyclerView.Adapter<CartoonAdapter.viewHolder>{
    ArrayList<CartoonModel> list;
    Context context;


    public CartoonAdapter(ArrayList<CartoonModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final CartoonModel model = list.get(position);
        Glide.with(holder.imageUrl.getContext()).load(model.getImageUrl()).into(holder.imageUrl);
        Glide.with(holder.playUrl.getContext()).load(model.getPlayUrl()).into(holder.playUrl);
        holder.duration.setText(model.getDuration());
        holder.title.setText(model.getTitle());
        holder.videoUrl.setText(model.getVideoUrl());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout,new VideoFragment(model.getVideoUrl()))
                        .addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageUrl,playUrl;
        TextView title,duration,videoUrl;
       CardView card;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageUrl = itemView.findViewById(R.id.imageUrl);
            playUrl = itemView.findViewById(R.id.playUrl);
            title = itemView.findViewById(R.id.title);
            duration = itemView.findViewById(R.id.duration);
            videoUrl = itemView.findViewById(R.id.videoUrl);
            card = itemView.findViewById(R.id.card);
        }
    }
}
