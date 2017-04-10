package nfl.com.androidart.contents.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        super.onCreateViewHolder(parent, viewType);
        itemContentsBindingTemp = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_contents, parent, false);
        myViewHolder = new MyViewHolder(itemContentsBindingTemp);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
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
