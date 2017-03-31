package com.meimengmeng.two.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meimengmeng.two.R;
import com.meimengmeng.two.bean.PopBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by  leijiaxq
 * Date       2017/3/16 11:22
 * Describe
 */
public class MarketSynthePopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<PopBean> mDatas;

    public MarketSynthePopAdapter(List<PopBean> datas) {
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_market_pop, parent, false);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolderItem holderItem = (ViewHolderItem) holder;
        PopBean bean = mDatas.get(position);

        holderItem.mItemTv.setText(TextUtils.isEmpty(bean.text) ? "" : bean.text);
        holderItem.mItemTv.setSelected(bean.flag);

        holderItem.mItemTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public OnItemClickListener mListener;

    public interface OnItemClickListener {

        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener l) {
        mListener = l;
    }


    static class ViewHolderItem extends RecyclerView.ViewHolder {
        @BindView(R.id.item_tv)
        TextView mItemTv;

        ViewHolderItem(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
