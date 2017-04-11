package gasstationmerchant.hantangyunjie.com.testscrollview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import java.lang.reflect.Field;

/**
 * 作者： WangZL on 2017/4/7 0007.
 */

public class MyScrollView extends ScrollView {

    private View mView;
    private OnScrollListener onScrollListener;
    /**
     * 最小的滑动距离
     */
    private static final int SCROLLLIMIT = 40;
    private float dowmX;
    private float dowmY;
    private int mViewHeight;

    private boolean isRun = true;
    private float scrollY = 0;
    private boolean isTop;
    private boolean isScroll = true;


    /**
     * 主要是用在用户手指离开MyScrollView，MyScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
     */
    private int lastScrollY;
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置view
     * @param mView
     */
    public void setView(View mView){
        this.mView = mView;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                dowmX = ev.getX();
                dowmY = ev.getY();
                mViewHeight = mView.getHeight();
                scrollY = dowmY;
                break;
            case MotionEvent.ACTION_MOVE:
                float X = ev.getX();
                float Y = ev.getY();
                float xx = X - dowmX;
                float yy = Y - dowmY;
                Log.i("TAG","Y----->"+Y);
                Log.i("TAG","-----》getScorll : "+getScrollY());
                if (Math.abs(yy)>Math.abs(xx)&& isScroll){
//                    return true;//scrollview执行滑动  Listview不滑动

//                    return false;//listView滑动  scrollview不滑动
                    if (!isRun && isTop){
                        return false;
                    }
                    else if (!isRun && !isTop){
                        if (scrollY < Y && (Y - scrollY) > SCROLLLIMIT){
                            //往下拉
                            isTop = false;
                            isRun = true;
                            return true;
                        }else {
                            if (getScrollY() == mViewHeight){
                                return false;
                            }
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
    public void setRun(){
        isTop = false;

    }

    //是否到顶部了
    public void  isTop(boolean isTop){
        this.isTop = isTop;

    }


    public void isScroll(boolean Scroll){
        this.isScroll = isScroll;

    }





    private int CrollY;


    /**
     * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中
     */
    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            int scrollY = MyScrollView.this.getScrollY();

            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
            if(lastScrollY != scrollY){
                lastScrollY = scrollY;
                handler.sendMessage(handler.obtainMessage());
            }
            if(onScrollListener != null){
                onScrollListener.onScroll(scrollY);
            }

        };

    };


    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        CrollY = scrollY;
        //判断如果滑动的距离大于view的高度就直接设置成view的高度
        if (scrollY >= mViewHeight){
            scrollY =mViewHeight;
            isRun  = false;
        }
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }




    /**
     * 重写onTouchEvent， 当用户的手在MyScrollView上面的时候，
     * 直接将MyScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
     * MyScrollView可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理
     * MyScrollView滑动的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(onScrollListener != null){
            onScrollListener.onScroll(lastScrollY = this.getScrollY());
        }
        switch(ev.getAction()){
            case MotionEvent.ACTION_UP:
                handler.sendMessageDelayed(handler.obtainMessage(), 20);
                break;
        }
        return super.onTouchEvent(ev);
    }



    // Return false if we're scrolling in the x direction
    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.i("TAG","distanceY---->"+distanceY);
            if (Math.abs(distanceY) > Math.abs(distanceX)) {
                return true;
            }
            return false;
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {

        super.onScrollChanged(l, t, oldl, oldt);
    }

    public static Field getDeclaredField(Object object, String field_name) {
        Class<?> cla = object.getClass();
        Field field = null;
        for (; cla != Object.class; cla = cla.getSuperclass()) {
            try {
                field = cla.getDeclaredField(field_name);
                field.setAccessible(true);
                return field;
            } catch (Exception e) {

            }
        }
        return null;
    }

    /**
     * 设置滚动接口
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener){
        this.onScrollListener = onScrollListener;
    }



    /**
     * 滚动的回调接口
     */
    public interface OnScrollListener{
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         */
        public void onScroll(int scrollY);
    }

}
