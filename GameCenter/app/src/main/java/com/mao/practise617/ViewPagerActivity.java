package com.mao.practise617;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.camera2.params.LensShadingMap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mao.practise617.Adapter.ImageSliderAdapter;
import com.mao.practise617.GameGuide.GameGuide;
import com.mao.practise617.GameGuide.GameGuideContentActivity;
import com.mao.practise617.GameStar.StarIntroduceActivity;
import com.mao.practise617.Network.Network;
import com.mao.practise617.Network.ResponseCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewPagerActivity extends AppCompatActivity implements View.OnClickListener {


    private IpAddr ipAddr = new IpAddr();

    private ViewPager viewPager;//  轮播图
    private ArrayList<View> pageViewList; //子界面  容器
    private TextView weixin1, wo, fax, tongxunl;//  文本

    private ListView stulist;
    private Button button5;

    Network network = new Network();


    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);// 主页


        // 菜单  点击事件
        weixin1 = (TextView) findViewById(R.id.weixin1);
        wo = (TextView) findViewById(R.id.wo);
        fax = (TextView) findViewById(R.id.fax);
        tongxunl = (TextView) findViewById(R.id.tongxunl);

        Toast.makeText(this, "hello world", Toast.LENGTH_SHORT).show();

        // 事件触发
        weixin1.setOnClickListener(this);
        wo.setOnClickListener(this);
        fax.setOnClickListener(this);
        tongxunl.setOnClickListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewPager);//轮播图

        //一 、查找布局文件用LayoutInflater.inflate
        // 子界面 定义的过程
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.activity_lun_bo, null); // 子界面1
        View view2 = inflater.inflate(R.layout.events_list, null); // 子界面2
        View view3 = inflater.inflate(R.layout.game_star_list, null);
        View view4 = inflater.inflate(R.layout.activity_game_guide, null);

        view1init(view1);
        view2init(view2);
        view3init(view3);
        view4init(view4);

        // 集合容器
        pageViewList = new ArrayList<View>();
        pageViewList.add(view1);  //0
        pageViewList.add(view2);//添加想要切换的界面 1
        pageViewList.add(view3);
        pageViewList.add(view4);

        viewPage();//轮播图初始加载
    }


    public void viewPage() {
        //数据适配器
        //  布局对象已经添加到  适配器
        PagerAdapter mPagerAdapter = new PagerAdapter() {

            @Override
            //获取当前窗体界面数
            public int getCount() {
                // TODO Auto-generated method stub
                return pageViewList.size();// 有几个子界面
            }

            @Override
            //判断是否由对象生成界面
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            //使从ViewGroup中移出当前View
            public void destroyItem(View arg0, int arg1, Object arg2) {
                ((ViewPager) arg0).removeView(pageViewList.get(arg1));
            }

            //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
            public Object instantiateItem(View arg0, int arg1) {
                ((ViewPager) arg0).addView(pageViewList.get(arg1));// pageview.get(arg1) 从多个子界面中 根据 arg1 返回第几个子界面
                return pageViewList.get(arg1);
            }
        };
        //绑定适配器
        viewPager.setAdapter(mPagerAdapter);
        //设置viewPager的初始界面为第一个界面
        viewPager.setCurrentItem(1);

    }

    ListView goodsList;
    ListView gameGuideList;
    ListView gameStarList;
    ListView events_new_list;

    public void view1init(View view1) {
        // 以下代码是最初代码，如果之后不能使用，可以恢复，如果修复好了，可以删除
//        goodsList = view1.findViewById(R.id.goodsList);
//
//        List<Map<String, String>> users = new ArrayList<Map<String, String>>();
//
//        for (int i = 0; i < 5; ++i) {
//            Map<String, String> user = new HashMap<String, String>();
//            user.put("mesgg", "商品" + i);
//            user.put("tel", "123456789" + i);
//            users.add(user);
//        }
//
//        SimpleAdapter userAdapter = new SimpleAdapter(ViewPagerActivity.this, users, R.layout.goodslistshow,
//                new String[]{"mesgg", "tel"},
//                new int[]{R.id.userName, R.id.tel});
//
//        goodsList.setAdapter(userAdapter);


        ViewPager2 viewPager2 = view1.findViewById(R.id.lun_bo);
//        TabLayout tabLayout = findViewById(R.id.tabLayout);
//        ViewPager2  viewPager;
        List<Integer> images = Arrays.asList(
                R.drawable.lun_bo_1,
                R.drawable.lun_bo_2,
                R.drawable.lun_bo_3,
                R.drawable.lun_bo_4,
                R.drawable.lun_bo_5
        );
        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(images);

        viewPager2.setAdapter(imageSliderAdapter);

        // 设置 TabLayout 和 ViewPager2 的联动
//        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
//            // 设置 tab 的文本或图标，可以根据需要自定义
//        }).attach();
    }


    public void view2init(View view2) {
        events_new_list = view2.findViewById(R.id.events_new_list);

        List<Map<String, String>> events = new ArrayList<Map<String, String>>();

        for (int i = 0; i < 5; ++i) {
            Map<String, String> event = new HashMap<String, String>();
            event.put("title", "R6S major" + i);
            event.put("team_name", "G2" + i);
            events.add(event);
        }

        SimpleAdapter eventsAdapter = new SimpleAdapter(ViewPagerActivity.this, events, R.layout.activity_events,
                new String[]{"title", "team_name"},
                new int[]{R.id.events_title, R.id.team_name});

        events_new_list.setAdapter(eventsAdapter);
    }

    private void view4init(View view4) {
        gameGuideList = view4.findViewById(R.id.gameGuide_list);
        List<Map<String, String>> users = new ArrayList<Map<String, String>>();

        // 获取数据
        String url = ipAddr.getIpGameDetail() + "/findAll";
        view4Data(url);
    }

    private void view3init(View view3) {
        gameStarList = view3.findViewById(R.id.gameStarList);

        List<Map<String, String>> gameStar = new ArrayList<Map<String, String>>();

        String url = ipAddr.getIpGameStar() + "/findAll";
        view3Data(url);

//        for (int i = 0; i < 5; ++i) {
//            Map<String, String> user = new HashMap<String, String>();
//            user.put("name", "玩家" + i);
//            user.put("content", "he is special at rainbow six " + i);
//            gameStar.add(user);
//        }

//        SimpleAdapter gameStarAdapter = new SimpleAdapter(ViewPagerActivity.this, gameStar, R.layout.gamestar,
//                new String[]{"name", "content"},
//                new int[]{R.id.gameStarName, R.id.gameStarContent});
//
//        gameStarList.setAdapter(gameStarAdapter);
//
//
//        // Set OnItemClickListener to handle click events
//        gameStarList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // 获取你的数据源中的数据项
////                Map<String, String> item = (Map<String, String>) parent.getItemAtPosition(position);
//
//                // 假设你的数据项有一个名为 "id" 的键，用于标识唯一标识符
////                String itemId = item.get("id");
//                toNewActivity(parent, view, position, id, StarIntroduceActivity.class);
//            }
//        });
    }

    @Override
    public void onClick(View view) {

        //判断   点击 id 判断
        if (view.getId() == R.id.weixin1) {
            viewPager.setCurrentItem(0);//
        } else if (view.getId() == R.id.tongxunl) {
            viewPager.setCurrentItem(1);//
        } else if (view.getId() == R.id.fax) {
            viewPager.setCurrentItem(2);
        } else if (view.getId() == R.id.wo) {
            viewPager.setCurrentItem(3);
        }
    }

    public static String toOpenUrl(String url1, String postParms) {
        //String registerUrl = "http://192.168.43.206:8083/login";
        String sign = "";
        try {
            //1\访问的网络地址
            URL url = new URL(url1);
            // 创建连接
            HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();
            // 连接参数
            postConnection.setRequestMethod("POST");//post 请求
            postConnection.setConnectTimeout(1000 * 5);
            postConnection.setReadTimeout(1000 * 5);
            postConnection.setDoInput(true);//允许从服务端读取数据
            postConnection.setDoOutput(true);//允许写入
            postConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");//以表单形式传递参数
            //发送数据  ?username=123&password=123
            // String postParms ="username="+username.getText().toString()+"&password="+password.getText().toString()+"&age=1";
            OutputStream outputStream = postConnection.getOutputStream();
            outputStream.write(postParms.getBytes());//把参数发送过去.
            outputStream.flush();
            // 接受数据
            final StringBuffer buffer = new StringBuffer();
            int code = postConnection.getResponseCode();

            if (code == 200) {//成功
                InputStream inputStream = postConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;//一行一行的读取
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);//把一行数据拼接到buffer里
                }
                // 接受结束  buffer
                sign = buffer.toString();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }

    public void getJsonArray(String data, List<Map<String, String>> users) {
        try {
            System.out.println("datadata======" + data);
            JSONArray jsonArray = new JSONArray(data);
            // 遍历JSON数组
            for (int i = 0; i < jsonArray.length(); i++) {
                // 获取每个元素，并将其转换为JSONObject
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                // 从JSONObject中获取数据
                int goodsid = jsonObject.getInt("goodsid");
                String goodsname = jsonObject.getString("goodsname");
                // 处理获取到的数据，例如打印输出
                System.out.println("goodsid: " + goodsid + ", goodsname: " + goodsname);
                Map<String, String> goodsMap = new HashMap<>();
                goodsMap.put("goodsid", String.valueOf(goodsid));
                goodsMap.put("price", jsonObject.getString("price"));
                goodsMap.put("goodsname", goodsname);

                users.add(goodsMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toNewActivity(AdapterView<?> parent, View view, int position, int id, Class<?> targetActivityClass) {
        Intent intent = new Intent(view.getContext(), targetActivityClass);
        Map<String, String> selectedItem = (Map<String, String>) parent.getItemAtPosition(position);
        for (Map.Entry<String, String> entry : selectedItem.entrySet()) {
            intent.putExtra(entry.getKey(), entry.getValue());
        }
        intent.putExtra("itemId", id);

        // Start the new activity
        view.getContext().startActivity(intent);
    }

    public void view4Data(String url) {
        network.sendGetRequestWithThread(url, new ResponseCallback() {
            @Override
            public void onSuccess(String response) {
                // 在这里处理成功响应
                System.out.println("响应成功: " + response);

//                // 在这里处理解析后的 JSON 对象
                List<Map<String, String>> users = handleData(response);
                System.out.println("users======" + users);


                SimpleAdapter gameAdapter = new SimpleAdapter(ViewPagerActivity.this, users, R.layout.game_list_show,
                        new String[]{"guideId", "gameName", "guideContent"},
                        new int[]{R.id.gameTitle, R.id.gameContent});

                gameGuideList.setAdapter(gameAdapter);

                gameGuideList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Map<String, String> user = users.get(position);
                        int idd = Integer.parseInt(user.get("guideId"));
                        toNewActivity(parent, view, position, idd, GameGuideContentActivity.class);
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                // 在这里处理失败情况
                System.out.println("请求失败: " + e.getMessage());
            }
        });
    }

    public void view3Data(String url) {
        network.sendGetRequestWithThread(url, new ResponseCallback() {
            @Override
            public void onSuccess(String response) {
                // 在这里处理成功响应
                System.out.println("响应成功: " + response);

//                // 在这里处理解析后的 JSON 对象
                List<Map<String, String>> users = handleData(response);
                System.out.println("users======" + users);


                SimpleAdapter gameAdapter = new SimpleAdapter(ViewPagerActivity.this, users, R.layout.gamestar,
                        new String[]{"starId", "starName", "starDescription"},
                        new int[]{R.id.gameStarName, R.id.gameStarContent});

                gameStarList.setAdapter(gameAdapter);

                gameStarList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Map<String, String> user = users.get(position);
                        int idd = Integer.parseInt(user.get("starId"));
                        toNewActivity(parent, view, position, idd, StarIntroduceActivity.class);
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                // 在这里处理失败情况
                System.out.println("请求失败: " + e.getMessage());
            }
        });
    }

    public List<Map<String, String>> handleData(String data) {
        // 使用 Gson 将响应字符串转换为 JSON 对象
        Gson gson = new Gson();
        // 提取json的数据
//        List<Map<String, String>> users = new ArrayList<>();
        // 使用 TypeToken 获取 List<Map<String, String>> 的类型
        Type type = new TypeToken<List<Map<String, String>>>() {}.getType();

        // 解析 JSON 数据
        List<Map<String, String>> res = gson.fromJson(data, type);

//        for (int i = 0; i < 5; ++i) {
//            Map<String, String> user = new HashMap<String, String>();
//            user.put("id", String.valueOf(i));
//            user.put("title", "彩虹六号" + i);
//            user.put("content", "i love rainbow six " + i);
//            users.add(user);
//        }
        System.out.println("res------------------------" + res);

        return res;
    }
}