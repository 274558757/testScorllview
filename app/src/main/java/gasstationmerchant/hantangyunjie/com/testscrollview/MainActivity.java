package gasstationmerchant.hantangyunjie.com.testscrollview;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements ExpandableListView.OnChildClickListener
        ,ExpandableListView.OnGroupClickListener,PinnedHeaderExpandableListView.OnHeaderUpdateListener{

    private MyScrollView scrollView;
    private MyListView lv_view;
    private LinearLayout lin_view;
    private PinnedHeaderExpandableListView plvService;
    private ShopServiceAdapter madapter;
    private ListView lv_content_view;
    private TextView test1;
    private TextView test2;
    private ImageView img_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        setContentView(R.layout.activity_main);
        scrollView = (MyScrollView) findViewById(R.id.my_view);
        img_view = (ImageView) findViewById(R.id.img_view);
        lin_view = (LinearLayout) findViewById(R.id.lin_view);
        lv_view = (MyListView) findViewById(R.id.lv_view);
        plvService = (PinnedHeaderExpandableListView) findViewById(R.id.ex_view);
        lv_content_view = (ListView) findViewById(R.id.lv_content_view);
        test1 = (TextView) findViewById(R.id.test1);
        test2 = (TextView) findViewById(R.id.test2);


        scrollView.setView(lin_view);
        //设置高度为屏幕的高度
        ViewGroup.LayoutParams params = plvService.getLayoutParams();
        params.height = height;
        plvService.setLayoutParams(params);
        plvService.requestLayout();
        lv_content_view.setLayoutParams(params);
        lv_content_view.requestLayout();
        scrollView.isScroll(true);



        List<String> mlist = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mlist.add("ss"+i);
        }
        List<ShopServiceEntity> entities = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            entities.add(new ShopServiceEntity());
        }

        HashMap<String, List<ShopServiceEntity>> map = new HashMap<>();
        map.put("ss0", entities);
        map.put("ss1", entities);
        map.put("ss2", entities);
        map.put("ss3", entities);
        map.put("ss4", entities);

        madapter = new ShopServiceAdapter(this, mlist, map);
        plvService.setAdapter(madapter);
        for (int i = 0; i < madapter.getGroupCount(); i++) {
            plvService.expandGroup(i);
        }

        ContentAdapter contentAdapter = new ContentAdapter(this);
        lv_view.setAdapter(contentAdapter);

        lv_content_view.setAdapter(contentAdapter);


        plvService.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.i("TAG","onScroll--->"+"firstVisibleItem = "+firstVisibleItem+"; visibleItemCount = "+visibleItemCount+"; totalItemCount = "+totalItemCount);
                setScroll(firstVisibleItem,plvService);
            }
        });
        img_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"点击了",Toast.LENGTH_SHORT).show();
            }
        });

        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                lin_service.setVisibility(View.VISIBLE);
                plvService.setVisibility(View.VISIBLE);
                lv_view.setVisibility(View.VISIBLE);
                lv_content_view.setVisibility(View.GONE);
            }
        });
        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                lin_service.setVisibility(View.GONE);
                lv_content_view.setVisibility(View.VISIBLE);
                plvService.setVisibility(View.GONE);
                lv_view.setVisibility(View.GONE);

            }
        });

        lv_content_view.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                setScroll(firstVisibleItem,lv_content_view);
            }
        });


        lv_view.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

//                setScroll(firstVisibleItem,lv_view);
            }
        });
        lv_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
            }
        });


        plvService.setOnHeaderUpdateListener(this);
        plvService.setOnGroupClickListener(this);
        plvService.setOnChildClickListener(this);

    }

    private void setScroll(int firstVisibleItem,ListView lv) {
        if (firstVisibleItem ==0){
            View firstVisibleItemView = lv.getChildAt(0);
            if (firstVisibleItemView != null && firstVisibleItemView.getTop() == 0){
                Log.i("TAG","plvService------>滑动到了顶部");
//                            scrollView.requestDisallowInterceptTouchEvent(true);
                scrollView.setRun();
            }



        }

        if (firstVisibleItem>0){
            scrollView.isTop(true);

        }
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Log.i("TAG","onChildClick");
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return false;
    }

    @Override
    public View getPinnedHeader() {
        View headerView=getLayoutInflater().inflate(R.layout.item_service_list, null);
        headerView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                AbsListView.LayoutParams.WRAP_CONTENT));
        return headerView;
    }

    @Override
    public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
        TextView tv=(TextView) headerView.findViewById(R.id.tv_service_name);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        String name="";

        if (firstVisibleGroupPos>=0) {
            name=(String) madapter.getGroup(firstVisibleGroupPos);
        }
        tv.setText(name);
    }
}
