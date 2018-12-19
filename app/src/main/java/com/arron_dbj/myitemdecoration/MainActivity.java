package com.arron_dbj.myitemdecoration;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mList;
    private RecyclerView recyclerView;

    private String[] cities = {
            "成都", "绵阳", "德阳", "广元", "自贡", "宜宾", "内江", "都江堰", "峨眉山", "简阳", "达州", "广安",
            "开封", "洛阳", "郑州", "安阳", "南阳", "焦作", "许昌", "周口", "三门峡", "驻马店", "鹤壁", "新乡",
            "无锡", "常州", "扬州", "徐州", "苏州", "连云港", "盐城", "淮安", "宿迁", "镇江", "南通", "泰州",
            "潍坊", "淄博", "威海", "枣庄", "泰安", "临沂", "东营", "济宁", "烟台", "菏泽", "日照", "德州",
            "广州", "佛山", "汕头", "湛江", "韶关", "中山", "珠海", "肇庆", "阳江", "惠州", "潮州", "东莞",
    };
    private String[] provinces = {
            "四川省", "河南省", "江苏省", "山东省", "广东省"
    };
    private String colors[] = {"#EEE685", "#3CB371", "#87CEEB", "#40E0D0", "#EE7942"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initProvince();
        recyclerView = findViewById(R.id.recycler_view);
        MyAdapter adapter = new MyAdapter(mList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        StickyHeaderDecoration stickyHeader = new StickyHeaderDecoration(new OnProvinceListener() {
            @Override
            public Province getProvinceInfo(int position) {
                int provinceId = position / 12;
                int cityPositionInProvince = position % 12;
                Bitmap bitmap = getProvinceBitmap(provinceId);
                Province province = new Province(provinceId, provinces[provinceId]);
                province.setProvinceLength(12);
                province.setCityPosition(cityPositionInProvince);
                province.setProvinceBitmap(bitmap);
                province.setProvinceBackground(colors[provinceId]);
                return province;
            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, mList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.addItemDecoration(stickyHeader);
        recyclerView.setAdapter(adapter);


    }
    private Bitmap getProvinceBitmap(int provinceId){

        String bitmapName = "subject" + (provinceId + 1);
        int bitmapId = this.getResources().getIdentifier(bitmapName, "drawable",
                "com.arron_dbj.myitemdecoration");
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), bitmapId);
        return bitmap;
    }

    private void initProvince(){
        mList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++){
            mList.add(cities[i]);
        }
    }
}
