package com.example.mrsplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private ArrayList<VideoRModel> videoRModelArrayList;
    private Context context;
    private VideoClicklInterface videoClicklInterface;

    public VideoAdapter(ArrayList<VideoRModel> videoRModelArrayList, Context context, VideoClicklInterface videoClicklInterface) {
        this.videoRModelArrayList = videoRModelArrayList;
        this.context = context;
        this.videoClicklInterface = videoClicklInterface;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_listype,parent,false);

    return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        VideoRModel videoRModel=videoRModelArrayList.get(position);
        holder.thumbnailIV.setImageBitmap(videoRModel.getThumbNail());
        holder.videNameText.setText(videoRModel.getVideoName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoClicklInterface.onVideoClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return videoRModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnailIV;
        private TextView videNameText;

        public ViewHolder( View itemView) {
            super(itemView);
            thumbnailIV=itemView.findViewById(R.id.idIVThumbNail);
            videNameText=itemView.findViewById(R.id.vtitleID);

        }
    }

    public interface VideoClicklInterface{
        void onVideoClick(int position);

    }


}
