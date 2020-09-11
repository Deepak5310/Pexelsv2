package com.example.pexels;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PexelsAdapter extends RecyclerView.Adapter<PexelsViewHolder> {

    private Context context;
    private List<PexelsModel> pexelsModelList;

    public PexelsAdapter(Context context, List<PexelsModel> pexelsModelList) {
        this.context = context;
        this.pexelsModelList = pexelsModelList;
    }

    @NonNull
    @Override
    public PexelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        return new PexelsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PexelsViewHolder holder, final int position) {
        Glide.with(context).load(pexelsModelList.get(position).getMediumUrl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, FullScreenImage.class)
                        .putExtra("originalUrl", pexelsModelList.get(position).getOriginalUrl()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return pexelsModelList.size();
    }
}

class PexelsViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public PexelsViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image_view);
    }
}
