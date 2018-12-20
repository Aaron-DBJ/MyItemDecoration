package com.arron_dbj.myitemdecoration;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;
import java.util.Random;

public class HeaderFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> mList;
    private Context context;
    private static final int HEADER = 0;
    private static final int CONTENT = 1;
    private static final int FOOTER = 2;
    private int headerCount = 1;
    private int footerCount = 1;
//    private OnItemClickListener onItemClickListener;
    private List<Integer> imageList;
    private List<String> titleList;


//    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
//        this.onItemClickListener = onItemClickListener;
//    }
    public HeaderFooterAdapter(List<String> list, Context context,List<Integer> imageList, List<String> titleList){
        mList = list;
        this.context = context;
        this.imageList = imageList;
        this.titleList = titleList;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        Banner banner;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder{
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        ImageView cityImage;
//        TextView cityName, cityIntro;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
//            cityName = itemView.findViewById(R.id.city_name);
//            cityImage = itemView.findViewById(R.id.city_image);
//            cityIntro = itemView.findViewById(R.id.city_intro);
//            itemView.setOnClickListener(this);
            textView = itemView.findViewById(R.id.tv_item);
        }


        @Override
        public void onClick(View view) {
//            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == HEADER){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
            return new HeaderViewHolder(view);
        }else if (viewType == CONTENT){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new ViewHolder(view);
        }else if (viewType == FOOTER){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder){
            ((HeaderViewHolder) holder).banner.setDelayTime(4000)
                    .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                    .setImageLoader(new MyImageLoader())
                    .setImages(imageList)
                    .setIndicatorGravity(BannerConfig.CENTER)
                    .setBannerTitles(titleList)
                    .isAutoPlay(true)
                    .setBannerAnimation(Transformer.DepthPage)
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
//                            Intent intent = new Intent(context, StickyHeaderActivity.class);
//                            context.startActivity(intent);
                            Toast.makeText(context, "第"+ position + "项", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .start();
        }else if (holder instanceof ViewHolder){
            ((ViewHolder) holder).textView.setText(mList.get(position - headerCount));
//            ((ViewHolder) holder).cityName.setText(mList.get(position - headerCount));
//            ((ViewHolder) holder).cityIntro.setText("城市城市城市城市城市城市城市城市城市" + position);
//            int imageId = getRandomImage(10);
//            ((ViewHolder) holder).cityImage.setImageResource(imageId);
        }else if (holder instanceof FooterViewHolder){

        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + headerCount + footerCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (headerCount != 0 && headerCount > position){
            return HEADER;
        }else if (footerCount != 0 && position >= (getItemCount() - 1)) {
            return FOOTER;
        }else {
            return CONTENT;
        }
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
