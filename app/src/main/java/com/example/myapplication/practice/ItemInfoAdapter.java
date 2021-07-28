package com.example.myapplication.practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.practice.ItemInfo;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.transition.Hold;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class ItemInfoAdapter extends RecyclerView.Adapter<ItemInfoAdapter.ViewHolder>{

    private ItemInfo itemInfo;
    private List<ProgramSeriesDTO> programSeriesList;

    public ItemInfoAdapter(ItemInfo itemInfo) {
        this.itemInfo = itemInfo;
        this.programSeriesList = new ArrayList<>(itemInfo.getProgramSeries());
    }

    /**
     *创建viewholder实例,并把加载出来的布局传入到构造函数中去.
     * @param parent RecycleView子项的最外层布局
     * @param viewType
     * @return
     */
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_show,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return  holder;
    }

    /**
     * 对RecycleView的子项数据进行赋值.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        ProgramSeriesDTO programSeries = programSeriesList.get(position);
        holder.itemImage.setImageURI(programSeries.getVImg());
        holder.itemName.setText(programSeries.getName());
    }

    @Override
    public int getItemCount() {
        return programSeriesList.size();
    }


    /**
     * ViewHolder继承自recyclerView.ViewHolder.用于获取布局中的控件实例
     */
    static class ViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView itemImage;
        TextView itemName;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemImage  = (SimpleDraweeView) itemView.findViewById(R.id.item_image);
            itemName  = (TextView) itemView.findViewById(R.id.item_name);
        }
    }

}
