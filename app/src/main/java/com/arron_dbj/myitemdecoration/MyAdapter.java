package com.arron_dbj.myitemdecoration;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> mList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public MyAdapter(List<String> list, Context context){
        mList = list;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView cityImage;
        public TextView cityName, cityIntro;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item);
            cityName = itemView.findViewById(R.id.city_name);
            cityImage = itemView.findViewById(R.id.city_image);
            cityIntro = itemView.findViewById(R.id.city_intro);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).textView.setText(mList.get(position));
            int resourceId = getRandomImage(10);
            ((ViewHolder)holder).cityName.setText(mList.get(position));
            ((ViewHolder)holder).cityImage.setImageResource(resourceId);
            ((ViewHolder)holder).cityIntro.setText("城市城市城市城市城市城市城市城市城市城市" + position);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public int getRandomImage(int range){
        String imageName = "";
        if (range > 0) {
            Random random = new Random();
            imageName = "city" + (random.nextInt(range) + 1);
        }
        int imageId = context.getResources().getIdentifier(imageName, "drawable",
                "com.arron_dbj.myitemdecoration");
        return imageId;
    }
}
