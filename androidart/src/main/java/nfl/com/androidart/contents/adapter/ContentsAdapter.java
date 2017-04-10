package nfl.com.androidart.contents.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nfl.libraryoflibrary.utils.LogTool;
import com.nfl.libraryoflibrary.view.recyclerview.RecyclereViewBaseAdapter;

import java.util.List;

import nfl.com.androidart.R;
import nfl.com.androidart.contents.databinding.Contents;
import nfl.com.androidart.databinding.ItemContentsBinding;

/**
 * Created by fuli.niu on 2017/4/5.
 */

public class ContentsAdapter extends RecyclereViewBaseAdapter<ContentsAdapter.MyViewHolder> {

    private Context context;
    private List<Contents> contentsList;
    private ItemContentsBinding itemContentsBinding;
    private ItemContentsBinding itemContentsBindingTemp;
    private MyViewHolder myViewHolder;

    public ContentsAdapter(Context context, List<Contents> contentsList) {
        this.context = context;
        this.contentsList = contentsList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public MyViewHolder onCreateViewHolder2(ViewGroup parent, int viewType) {
        LogTool.i("这里应该只执行一次" + parent.toString());
        itemContentsBindingTemp = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_contents, parent, false);
        myViewHolder = new MyViewHolder(itemContentsBindingTemp);
        LogTool.i("parent:" + parent.toString());
        LogTool.i("myViewHolder01:" + myViewHolder.toString());
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder2(MyViewHolder holder, int position) {
        LogTool.i("myViewHolder02:" + holder.toString());
        LogTool.i("position:" + position);
        itemContentsBinding = holder.getItemContentsBinding();
        itemContentsBinding.setContents(contentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return null == contentsList ? 0 : contentsList.size();
    }

    public class MyViewHolder extends RecyclereViewBaseAdapter.BaseViewHolder {

        private ItemContentsBinding itemContentsBinding;

        public MyViewHolder(View itemView) {
            super(itemView);
        }

        public MyViewHolder(ItemContentsBinding itemContentsBinding) {
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
