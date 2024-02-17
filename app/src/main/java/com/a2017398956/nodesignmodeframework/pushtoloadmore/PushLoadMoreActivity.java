package com.a2017398956.nodesignmodeframework.pushtoloadmore;

import android.os.Bundle;

import com.a2017398956.nodesignmodeframework.R;
import com.nfl.libraryoflibrary.utils.net.BaseBean;
import com.nfl.libraryoflibrary.view.activity.CommonActionBarActivity;
import com.nfl.libraryoflibrary.view.recyclerview.CustomRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PushLoadMoreActivity extends CommonActionBarActivity {

    private CustomRecyclerView crv;
    private List<TextBean> data;
    private PushLoadMoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_load_more);
        data = new ArrayList<>();
        TextBean textBean;
        for (int i = 0; i < 20; i++) {
            textBean = new TextBean();
            textBean.setText(String.valueOf(i));
            data.add(textBean);
        }
        crv = (CustomRecyclerView) findViewById(R.id.crv);
        crv.setPullToLoadMoreListener(() -> {
            final int length = data.size();
            crv.postDelayed(() -> {
                TextBean textBean1;
                for (int i = length; i < length + 10; i++) {
                    textBean1 = new TextBean();
                    textBean1.setText(String.valueOf(i));
                    data.add(textBean1);
                }
                crv.finishLoadMore();
                adapter.notifyDataSetChanged();
            }, 4000);
        });
        adapter = new PushLoadMoreAdapter(this, data);
        crv.setAdapter(adapter);
    }

    public static class TextBean extends BaseBean {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

}
