package gasstationmerchant.hantangyunjie.com.testscrollview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 作者： WangZL on 2017/4/11 0011.
 */

public class ContentAdapter extends BaseAdapter {

    private Context mContex;
    private LayoutInflater inflater;
    public ContentAdapter(Context mContext){
        this.mContex = mContext;
        inflater = LayoutInflater.from(mContext);

    }



    @Override
    public int getCount() {
        return 20;
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
        convertView = inflater.inflate(R.layout.item_service_list,null);

        return convertView;
    }
}
