package com.example.studente;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ArrayList<HashMap<String, Object>> actionItems;
    SimpleAdapter actionAdapter;
    TextView tV_title;
    ImageView iVSliding;
    ImageView ivHome;
    private SlidingPaneLayout slidepanel;
    private ExpandableListView expandableListView;
    List<Map<String, Object>> groupData = new ArrayList<>();//大组成员
    List<List<Map<String, Object>>> childData = new ArrayList<>();//小组成员
    String key1 = "zhao";
    String key2 = "hong";
    String key3 = "bin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slidepanel = (SlidingPaneLayout) findViewById(R.id.slidingrPLPL);
        expandableListView = (ExpandableListView) findViewById(R.id.Expanda);
        ivHome = (ImageView) findViewById(R.id.imageView_home);
        iVSliding = (ImageView) findViewById(R.id.imageView_Sliding);
        tV_title = (TextView) findViewById(R.id.tv_title);
        tV_title.setText(getString(R.string.app_name));
        iVSliding.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (slidepanel.isOpen()) {
                    slidepanel.closePane();
                } else {
                    slidepanel.openPane();
                }
            }
        });
        Map<String, Object> gound = new HashMap<>();
        gound.put(key1, "账户信息");
        groupData.add(gound);
        List<Map<String, Object>> son = new ArrayList<>();
        Map<String, Object> goundl = new HashMap<>();
        goundl.put(key2, R.drawable.eee);
        goundl.put(key3,"个人信息");
        son.add(goundl);
        goundl = new HashMap<>();
        goundl.put(key2, R.drawable.eee);
        goundl.put(key3, "修改密码");
        son.add(goundl);
        childData.add(son);
        gound = new HashMap<>();
        gound.put(key1, "学生详细信息查询");
        groupData.add(gound);
        son = new ArrayList<>();
        goundl = new HashMap<>();
        goundl.put(key2, R.drawable.eee);
        goundl.put(key3,"信息查询");
        son.add(goundl);
        goundl = new HashMap<>();
        goundl.put(key2, R.drawable.eee);
        goundl.put(key3, "增添信息");
        son.add(goundl);
        childData.add(son);
        gound = new HashMap<>();
        gound.put(key1, "学生成绩分析");
        groupData.add(gound);
        son = new ArrayList<>();
        goundl = new HashMap<>();
        goundl.put(key2, R.drawable.eee);
        goundl.put(key3, "图一");
        son.add(goundl);
        goundl = new HashMap<>();
        goundl.put(key2, R.drawable.eee);
        goundl.put(key3, "图一");
        son.add(goundl);
        childData.add(son);
        gound = new HashMap<>();
        gound.put(key1, "一卡通充值");
        groupData.add(gound);
        son = new ArrayList<>();
        goundl = new HashMap<>();
        goundl.put(key2,R.drawable.eee);
        goundl.put(key3, "充值");
        son.add(goundl);
        childData.add(son);
        Expandd expandd=new Expandd();
        expandableListView.setAdapter(expandd);
    }

    class Expandd extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return groupData.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return childData.get(i).size();
        }

        @Override
        public Object getGroup(int i) {
            return null;
        }

        @Override
        public Object getChild(int i, int i1) {
            return null;
        }

        @Override
        public long getGroupId(int i) {
            return 0;
        }

        @Override
        public long getChildId(int i, int i1) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(getApplication());
                view = inflater.inflate(R.layout.layout_demo, null);
            }
            ImageView imageViewListGount = (ImageView) view.findViewById(R.id.imgimg);
            TextView textViewListGount = (TextView) view.findViewById(R.id.texttxxt);
            textViewListGount.setText(groupData.get(i).get(key1).toString());
            return  view;
        }
        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(getApplication());
                view = inflater.inflate(R.layout.layout_demo, null);
            }
            ImageView imageViewListGount = (ImageView) view.findViewById(R.id.imgimg);
            imageViewListGount.setImageResource((Integer) childData.get(i).get(i1).get(key2));
            TextView textViewListGount = (TextView) view.findViewById(R.id.texttxxt);
            textViewListGount.setText(childData.get(i).get(i1).get(key3).toString());
            return  view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        // 按下键盘上返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitAppDialog();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    public void exitAppDialog() {
        new AlertDialog.Builder(this)
                // .setIcon(android.R.drawable.ic_menu_info_detailsp)
                .setTitle("提示").setMessage("你确定要退出吗").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener()

        {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        }).show();

    }
}
