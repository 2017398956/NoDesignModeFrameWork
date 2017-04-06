package nfl.com.androidart.contents.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nfl.libraryoflibrary.utils.LogTool;

import java.util.List;

import nfl.com.androidart.R;
import nfl.com.androidart.contents.databinding.Contents;
import nfl.com.androidart.databinding.ItemContentsBinding;

/**
 * Created by fuli.niu on 2017/4/5.
 */

public class Contents2Adapter extends BaseAdapter {

    private Context context;
    private List<Contents> contentsList;
    private ItemContentsBinding itemContentsBindingTemp;

    public Contents2Adapter(Context context, List<Contents> contentsList) {
        this.context = context;
        this.contentsList = contentsList;
    }

    @Override
    public int getCount() {
        return null == contentsList ? 0 : contentsList.size();
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
        ViewHolder holder;
        if (null == convertView) {
            itemContentsBindingTemp = DataBindingUtil.inflate(LayoutInflater.from(context),
                    R.layout.item_contents, parent, false);
            convertView = itemContentsBindingTemp.getRoot();
            holder = new ViewHolder(itemContentsBindingTemp);
            LogTool.i("ViewHolder 应该只被创建一次");
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.getItemContentsBinding().setContents(contentsList.get(position));
        LogTool.i("Contents2Adapter:" + convertView.toString());
        LogTool.i("position:" + position);
        return convertView;
    }

    private class ViewHolder {

        private ItemContentsBinding itemContentsBinding;

        private ViewHolder(View itemView) {
            itemView.setTag(this);
        }

        public ViewHolder(ItemContentsBinding itemContentsBinding) {
            this(itemContentsBinding.getRoot());
            this.itemContentsBinding = itemContentsBinding;

        }

        public ItemContentsBinding getItemContentsBinding() {
            return itemContentsBinding;
        }

        public void setItemContentsBinding(ItemContentsBinding itemContentsBinding) {
            this.itemContentsBinding = itemContentsBinding;
        }
    }
}
