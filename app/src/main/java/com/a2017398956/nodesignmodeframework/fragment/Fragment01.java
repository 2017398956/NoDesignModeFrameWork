package com.a2017398956.nodesignmodeframework.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nfl.libraryoflibrary.view.CustomV4BaseFragment;

/**
 * Created by fuli.niu on 2017/10/10.
 */

public class Fragment01 extends CustomV4BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(container.getContext());
        textView.setText("textView " + Math.random());
        return textView;
    }
}
