package gasstationmerchant.hantangyunjie.com.testscrollview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 作者： WangZL on 2017/4/7 0007.
 */

public class MyAdapter extends BaseAdapter {


    private Context mContext;
    public MyAdapter(Context mContext){
        this.mContext = mContext;

    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_layout,null);
        return convertView;
    }
}
