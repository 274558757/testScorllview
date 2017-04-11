package gasstationmerchant.hantangyunjie.com.testscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/7/14 0014.
 */
public class NoScroolListView extends ListView {
    public NoScroolListView(Context context) {
        super(context);
    }

    public NoScroolListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScroolListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
