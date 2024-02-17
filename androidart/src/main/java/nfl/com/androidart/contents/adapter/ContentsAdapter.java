package nfl.com.androidart.contents.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nfl.libraryoflibrary.view.recyclerview.RecyclerViewBaseAdapter;

import java.util.List;

import nfl.com.androidart.contents.databinding.Contents;
import nfl.com.androidart.databinding.ItemContentsBinding;

/**
 * Created by fuli.niu on 2017/4/5.
 */

public class ContentsAdapter extends RecyclerViewBaseAdapter<ContentsAdapter.MyViewHolder> {

    private Context context;
    private List<Contents> contentsList;

    public ContentsAdapter(Context context, List<Contents> contentsList) {
        this.context = context;
        this.contentsList = contentsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        super.onCreateViewHolder(parent, viewType);
        return new MyViewHolder(ItemContentsBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.binding.tvChapterName.setText(contentsList.get(position).getChapter());
    }

    @Override
    public int getItemCount() {
        return null == contentsList ? 0 : contentsList.size();
    }

    public class MyViewHolder extends RecyclerViewBaseAdapter.BaseViewHolder {

        private ItemContentsBinding binding;

        public MyViewHolder(ItemContentsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
