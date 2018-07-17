package com.jiangxq.sphericalviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiangxq.sphericalviewdemo.view.TagCloudView;
import com.jiangxq.sphericalviewdemo.view.TagsAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TagCloudView v = findViewById(R.id.tagview);
        initData();
        v.setAdapter(new MyAdapter());

    }

    public void initData(){
        data.add("测试1");
        data.add("测试2");
        data.add("测试3");
        data.add("测试4");
        data.add("测试5");
        data.add("测试6");
        data.add("测试7");
        data.add("测试8");
        data.add("测试9");
        data.add("测试10");
        data.add("测试11");
        data.add("测试12");
    }
    public class MyAdapter extends TagsAdapter {
        @Override
        public int getCount() {
            return data.size();
        }
            @Override
            public View getView(Context context, final int position, ViewGroup parent) {
                TextView tv = new TextView(context);
                ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(100,100);
                tv.setLayoutParams(params);
                tv.setText("No."+data.get(position));
                tv.setGravity(Gravity.CENTER);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("click","TAG"+position+"clicked");
                    }
                });
                return tv;
//                View v = LayoutInflater.from(context).inflate(R.layout.test_layout,parent,false);
//                CircleView circleView = new CircleView(MainActivity.this);
//                return circleView;
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public int getPopularity(int position) {
                return position % 20;
            }

            @Override
            public void onThemeColorChanged(View view, int themeColor) {
//                ( (TextView)view).setTextColor(themeColor);
            }
    }
}
