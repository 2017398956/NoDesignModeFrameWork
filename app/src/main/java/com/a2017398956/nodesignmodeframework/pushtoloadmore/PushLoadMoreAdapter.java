package com.a2017398956.nodesignmodeframework.pushtoloadmore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nfl.libraryoflibrary.view.recyclerview.RecyclerViewBaseAdapter;

import java.util.List;

/**
 * Created by fuli.niu on 2017/6/8.
 */

public class PushLoadMoreAdapter extends RecyclerViewBaseAdapter<PushLoadMoreAdapter.ViewHolder> {

    private Context context;
    private List<PushLoadMoreActivity.TextBean> data;

    public PushLoadMoreAdapter(Context context, List<PushLoadMoreActivity.TextBean> data) {
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
            holder.text1.setText(data.get(position).getText());
        } else {

        }
    }

    public class ViewHolder extends RecyclerViewBaseAdapter.BaseViewHolder {

        private TextView text1;

        public ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
