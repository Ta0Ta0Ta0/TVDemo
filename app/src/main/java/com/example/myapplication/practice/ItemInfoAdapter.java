package com.example.myapplication.practice;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

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
        final ViewHolder holder = new ViewHolder(view);
        holder.programView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ProgramSeriesDTO program = programSeriesList.get(position);
                Toast.makeText(v.getContext(),"选择了"+program.getName(),Toast.LENGTH_LONG)
                        .show();
            }
        });
//        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//
//                    scale(holder.itemImage,true);
//                    holder.itemImage.setBackgroundResource(R.drawable.shape_example);
//                } else {
//                    holder.itemImage.setBackgroundResource(0);
////                    holder.itemImage.setImageURI(pro);
//                }
//            }
//        });
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

        if(programSeries != null){
            if(!TextUtils.isEmpty(programSeries.getVImg())){
                holder.itemImage.setImageURI(programSeries.getVImg());
            }else{
                holder.itemImage.setImageURI((Uri)null);
            }
            if(!TextUtils.isEmpty(programSeries.getName())){
                holder.itemName.setText(programSeries.getName());
            }else{
                holder.itemName.setText("");
            }

            if(!programSeries.getCorner().isEmpty()){
                holder.itemCornerImage.setImageURI(programSeries.getCorner().get(0).getCornerImg());
            }else{
                holder.itemCornerImage.setImageURI((Uri)null);
            }

        }



        holder.programView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                scale(holder.programView,hasFocus);
                holder.itemName.setSelected(hasFocus);
                if(hasFocus){


                    holder.itemImage.setBackgroundResource(R.drawable.shape_example);

                } else {
                    holder.itemImage.setBackgroundResource(0);

//                    holder.itemImage.setImageURI(pro);
                }
            }
        });

        holder.programView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d("按键测试：",String.valueOf(position));


                int size = programSeriesList.size();
                int mod_1 = size % 5 ;
                int mod_2 = position % 5;
                if(mod_1 == 0){
                    if(position >= size - 5 && position < size){
                        Log.d("按键测试：","1");
                        if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.getAction() == KeyEvent.ACTION_DOWN){
                            Toast.makeText(v.getContext(),"向下",Toast.LENGTH_SHORT).show();
                            Log.d("按键测试：","D");
                        }
                    }
                }else{
                    if(position >= size - 5 && mod_2 < mod_1 && position < size){
                        Log.d("按键测试：","1");
                        if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.getAction() == KeyEvent.ACTION_DOWN){
                            Toast.makeText(v.getContext(),"向下",Toast.LENGTH_SHORT).show();
                            Log.d("按键测试：","D");
                        }
                    }
                }

//                if(keyCode == KeyEvent.KEYCODE_DPAD_UP && event.getAction() == KeyEvent.ACTION_DOWN){
//                    Toast.makeText(v.getContext(),"向上",Toast.LENGTH_LONG).show();
//                    Log.d("按键测试：","U");
//
//                }
//                if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT && event.getAction() == KeyEvent.ACTION_DOWN)
//                {
//                    Toast.makeText(v.getContext(),"向左",Toast.LENGTH_LONG).show();
//                    Log.d("按键测试：","L");
//
//                }
//                if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && event.getAction() == KeyEvent.ACTION_DOWN){
//                    Toast.makeText(v.getContext(),"向右",Toast.LENGTH_LONG).show();
//                    Log.d("按键测试：","R");
//
//                }
                return false;
            }
        });
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
        View programView;
        SimpleDraweeView itemCornerImage;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemImage  = (SimpleDraweeView) itemView.findViewById(R.id.item_image);
            itemName  = (TextView) itemView.findViewById(R.id.item_name);
            itemCornerImage = (SimpleDraweeView) itemView.findViewById(R.id.item_corner_image);
            programView = itemView;
        }
    }
    private void scale(View v,boolean hasFocus){
        Emphasize.scaleCenter(v,hasFocus? 1.0f : 1.2f,hasFocus?1.2f : 1.0f,500);
    }

}
