package com.a2017398956.nodesignmodeframework.pushtoloadmore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nfl.libraryoflibrary.view.recyclerview.RecyclereViewBaseAdapter;

import java.util.List;

/**
 * Created by fuli.niu on 2017/6/8.
 */

public class PushLoadMoreAdapter extends RecyclereViewBaseAdapter<PushLoadMoreAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public PushLoadMoreAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (data == null) {
            return VIEW_TYPE_ITEM;
        } else {
            if (data.size() == 0) {
                return VIEW_TYPE_ITEM;
            } else {
                if (data.size() == position) {
                    return VIEW_TYPE_FOOTER;
                } else {
                    return VIEW_TYPE_ITEM;
                }
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        super.onCreateViewHolder(parent, viewType);
        if (viewType == VIEW_TYPE_FOOTER) {
            return new ViewHolder(footerView);
        } else {
            return new ViewHolder(LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position != data.size()) {
            holder.text1.setText(data.get(position));
        } else {

        }
    }

    public class ViewHolder extends RecyclereViewBaseAdapter.BaseViewHolder {

        private TextView text1;

        public ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
