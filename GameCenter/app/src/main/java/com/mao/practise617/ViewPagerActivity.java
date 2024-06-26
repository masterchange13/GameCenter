package com.mao.practise617;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mao.practise617.Adapter.ImageSliderAdapter;
import com.mao.practise617.GameGuide.GameGuideContentActivity;
import com.mao.practise617.GameRecommend.GameRecommendActivity;
import com.mao.practise617.GameStar.StarIntroduceActivity;
import com.mao.practise617.Network.Network;
import com.mao.practise617.Network.ResponseCallback;

import org.json.JSONArray;
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


    private static final int REQUEST_CODE_ADD_GAME_STAR = 1;
    private IpAddr ipAddr = new IpAddr();

    private ViewPager viewPager;//  轮播图
    private ArrayList<View> pageViewList; //子界面  容器
    private TextView weixin1, wo, fax, tongxunl;//  文本

    private ListView stulist;
    private Button button5;

    private ListView recommendGameList;
    private ImageView gameStarView;

    GameStarAddActivity gameStarAddActivity;

    Network network = new Network();

    private SimpleAdapter gameAdapter;


    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);// 主页


        // 菜单  点击事件
        weixin1 = (TextView) findViewById(R.id.weixin1);
//        Drawable drawable = getResources().getDrawable(R.drawable.cup);
//        drawable.setBounds(0, 0, 188, 188); // 设置图标的宽度和高度
//        weixin1.setCompoundDrawables(null, drawable, null, null);

        wo = (TextView) findViewById(R.id.wo);
//        Drawable drawable2 = getResources().getDrawable(R.drawable.hand);
//        drawable2.setBounds(0, 0, 188, 188); // 设置图标的宽度和高度
//        wo.setCompoundDrawables(null, drawable2, null, null);

        fax = (TextView) findViewById(R.id.fax);
//        Drawable drawable3 = getResources().getDrawable(R.drawable.star);
//        drawable3.setBounds(0, 0, 188, 188); // 设置图标的宽度和高度
//        fax.setCompoundDrawables(null, drawable3, null, null);

        tongxunl = (TextView) findViewById(R.id.tongxunl);
//        Drawable drawable4 = getResources().getDrawable(R.drawable.book);
//        drawable4.setBounds(0, 0, 188, 188); // 设置图标的宽度和高度
//        tongxunl.setCompoundDrawables(null, drawable4, null, null);

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

        recommendGameList = view1.findViewById(R.id.game_recommend_list);

        List<Map<String, String>> events = new ArrayList<Map<String, String>>();

//        for (int i = 0; i < 5; ++i) {
//            Map<String, String> event = new HashMap<String, String>();
//            event.put("gameTitle", "R6" + i);
//            event.put("gameContent", "i like it" + i);
//            events.add(event);
//        }

//        SimpleAdapter eventsAdapter = new SimpleAdapter(ViewPagerActivity.this, events, R.layout.game_list_show,
//                new String[]{"gameTitle", "gameContent"},
//                new int[]{R.id.gameTitle, R.id.gameContent});
//
//        recommendGameList.setAdapter(eventsAdapter);

        String  url = ipAddr.getIpGameRecommend() + "/findAll";
        view1Data(url);
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

        gameStarView = view3.findViewById(R.id.game_star_add);
        imageViewInit(gameStarView, GameStarAddActivity.class);
    }

    @Override
    public void onClick(View view) {

        //判断   点击 id 判断
        if (view.getId() == R.id.weixin1) {
//            view1init();
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

    public void view1Data(String url) {
        network.sendGetRequestWithThread(url, new ResponseCallback() {
            @Override
            public void onSuccess(String response) {
                // 在这里处理成功响应
                System.out.println("响应成功: " + response);

                // 解析 JSON 对象
                List<Map<String, String>> users = handleData(response);
                System.out.println("users======" + users);

                // 确保视图更新在主线程中进行
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SimpleAdapter eventsAdapter = new SimpleAdapter(ViewPagerActivity.this, users, R.layout.game_list_show,
                                new String[]{"gameId", "gameName", "gameDescription"},
                                new int[]{R.id.gameTitle, R.id.gameContent});

                        recommendGameList.setAdapter(eventsAdapter);

                        recommendGameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Map<String, String> user = users.get(position);
                                // 获取到游戏的id
                                int idd = Integer.parseInt(user.get("gameId"));
                                toNewActivity(parent, view, position, idd, GameRecommendActivity.class);
                            }
                        });
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                // 在这里处理失败情况
                System.out.println("请求失败: " + e.getMessage());
                // 使用 runOnUiThread 确保 Toast 显示在主线程中
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ViewPagerActivity.this, "请求失败，找不到数据", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void view4Data(String url) {
        network.sendGetRequestWithThread(url, new ResponseCallback() {
            @Override
            public void onSuccess(String response) {
                // 在这里处理成功响应
                System.out.println("响应成功: " + response);

                // 处理解析后的 JSON 对象
                final List<Map<String, String>> users = handleData(response);
                System.out.println("users======" + users);

                // 确保UI操作在主线程上执行
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gameAdapter = new SimpleAdapter(ViewPagerActivity.this, users, R.layout.game_list_show,
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

                // 解析数据
                List<Map<String, String>> users = handleData(response);
                System.out.println("users======" + users);

                // 确保视图更新在主线程中进行
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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

                        gameStarList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                showDeleteConfirmationDialog(position, users, gameAdapter);
                                return true;
                            }
                        });
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                // 在这里处理失败情况
                System.out.println("请求失败: " + e.getMessage());
                // 使用 runOnUiThread 确保 Toast 显示在主线程中
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ViewPagerActivity.this, "请求失败，找不到数据", Toast.LENGTH_SHORT).show();
                    }
                });
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

        System.out.println("res------------------------" + res);

        return res;
    }

    private void showDeleteConfirmationDialog(int position, List<Map<String, String>> users, SimpleAdapter adapter) {
        final String[] id = {"-1"};
        new AlertDialog.Builder(this)
                .setTitle("Delete Item")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Map<String, String> user = users.get(position);
                        id[0] = user.get("starId");
                        users.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(ViewPagerActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();

                        String url = ipAddr.getIpGameStar() + "/delete/" + id[0];
                        network.sendGetRequestWithThread(url, new ResponseCallback() {
                            @Override
                            public void onSuccess(String response) {
                                // 在这里处理成功响应
                                System.out.println("响应成功: " + response);


                            }

                            @Override
                            public void onFailure(Exception e) {
                                // 在这里处理失败情况
                                System.out.println("请求失败: " + e.getMessage());
                            }
                        });
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();

//        String url = ipAddr.getIpGameStar() + "/delete/" + id[0];
//        network.sendGetRequestWithThread(url, new ResponseCallback() {
//            @Override
//            public void onSuccess(String response) {
//                // 在这里处理成功响应
//                System.out.println("响应成功: " + response);
//
//
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                // 在这里处理失败情况
//                System.out.println("请求失败: " + e.getMessage());
//            }
//        });
    }

    public void imageViewInit(View view, Class<?> targetActivityClass) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理点击事件
                Intent intent = new Intent(view.getContext(), targetActivityClass);
                view.getContext().startActivity(intent);
                Toast.makeText(view.getContext(), "ImageView clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 调用刷新方法，比如重新获取数据并更新视图
        refreshGameStarList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_GAME_STAR && resultCode == RESULT_OK) {
            boolean refreshNeeded = data.getBooleanExtra("refreshNeeded", false);
            if (refreshNeeded) {
                // 调用刷新方法，比如重新获取数据并更新视图
                refreshGameStarList();
            }
        }
    }

    private void refreshGameStarList() {
        String url = ipAddr.getIpGameStar() + "/findAll";
        network.sendGetRequestWithThread(url, new ResponseCallback() {
            @Override
            public void onSuccess(String response) {
                // 在这里处理成功响应
                System.out.println("响应成功: " + response);

                // 解析数据
                List<Map<String, String>> users = handleData(response);
                System.out.println("users======" + users);

                // 确保视图更新在主线程中进行
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 创建一个新的 SimpleAdapter，并设置给 gameStarList
                        SimpleAdapter newAdapter = new SimpleAdapter(
                                ViewPagerActivity.this,
                                users,
                                R.layout.gamestar,
                                new String[]{"starId", "starName", "starDescription"},
                                new int[]{R.id.gameStarName, R.id.gameStarContent}
                        );

                        gameStarList.setAdapter(newAdapter);

                        // Optional: 如果你还想保留对新适配器的引用，可以将其赋给全局变量
                        // gameAdapter = newAdapter;
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                // 在这里处理失败情况
                System.out.println("请求失败: " + e.getMessage());
                // 使用 runOnUiThread 确保 Toast 显示在主线程中
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ViewPagerActivity.this, "请求失败，找不到数据", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
