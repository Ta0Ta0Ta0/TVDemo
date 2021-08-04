package com.example.myapplication.media;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static java.lang.String.valueOf;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    private Music music;
    private List<ResDTO> musicList;
    public MusicAdapter(Music music) {
        Log.d("测试","成功");
        this.music = music;
        this.musicList = new ArrayList<>(music.getRes());
        Log.d("测试size",valueOf(musicList.size()));
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.music_item,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.music_image.setImageURI(musicList.get(position).getAnime_info().getLogo());
        holder.music_name.setText(musicList.get(position).getTitle());
        holder.music_author.setText(musicList.get(position).getAuthor());
        holder.itemView.setTag(R.id.tag_first,musicList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView music_image;
        TextView music_name;
        TextView music_author;
        View musicView ;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            music_image = (SimpleDraweeView) itemView.findViewById(R.id.music_image);
            music_name = (TextView) itemView.findViewById(R.id.music_name);
            music_author = (TextView) itemView.findViewById(R.id.music_author);
            musicView = itemView;
        }
    }
}
