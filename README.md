# testScorllview

这是个scrollView 嵌套 PinnedHeaderExpandableListView 并且可以实现悬浮窗效果


主要实现自定义scrollView然后根据事件拦截来实现不同的滑动

这是拦截的代码：

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
    
    实现悬浮窗的代码：
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
    主要是根据view的高度来判断，让scrollview滑动到指定位置就不能在滑动了
    


----

