package gasstationmerchant.hantangyunjie.com.testscrollview;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * 作者： WangZL on 2017/4/5 0005.
 */

public class ShopServiceAdapter extends BaseExpandableListAdapter {
    //    public ShopServiceAdapter(List<ShopServiceEntity> data) {
//        super(R.layout.item_shop_service,data);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder baseViewHolder, ShopServiceEntity shopServiceEntity) {
//
//    }
    private Context mConetxt;
    private List<String> letterlist;
    private HashMap<String, List<ShopServiceEntity>> map;
    private LayoutInflater inflater;

    public ShopServiceAdapter(Context mContext, List<String> letterlist, HashMap<String, List<ShopServiceEntity>> map) {
        this.mConetxt = mContext;
        this.letterlist = letterlist;
        this.map = map;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getGroupCount() {
        return letterlist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(letterlist.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return letterlist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return map.get(letterlist.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_service_list,null);
            holder = new GroupHolder();
            holder.tv_service_name = (TextView) convertView.findViewById(R.id.tv_service_name);
            convertView.setTag(holder);
        }else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.tv_service_name.setGravity(Gravity.CENTER_VERTICAL);
        holder.tv_service_name.setText(letterlist.get(groupPosition).toString());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_shop_service, null);
            holder = new ChildHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_service_name);
            holder.tv_service = (TextView) convertView.findViewById(R.id.tv_service_content);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_service_price);
            holder.tv_pay = (TextView) convertView.findViewById(R.id.tv_pay);
            convertView.setTag(holder);
        }else {
            holder = (ChildHolder) convertView.getTag();
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }



    static class ChildHolder{
        private TextView tv_name;
        private TextView tv_service;
        private TextView tv_price;
        private TextView tv_pay;
    }

    static class GroupHolder{
        private TextView tv_service_name;
    }
}
