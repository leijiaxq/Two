package com.meimengmeng.two.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.baidu.mobads.AdView;
import com.baidu.mobads.AdViewListener;
import com.meimengmeng.two.R;
import com.meimengmeng.two.bean.Databean2;
import com.meimengmeng.two.utils.ScreenUtils;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by  leijiaxq
 * Date       2017/3/16 15:01
 * Describe
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Databean2.AdlistEntity> mDatas;

    private Handler mHandler;

    public int mDelayedTime = 1000;
    int mScreenWidth;

    public MainAdapter(Context context,List<Databean2.AdlistEntity> datas,Handler handler) {
        mHandler = handler;
        mContext = context;
        mDatas = datas;

        mScreenWidth = ScreenUtils.getScreenWidth(mContext);
//        int screenHeight = ScreenUtils.getScreenHeight(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new ViewHolderType1(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolderType1 viewHolderType1 = (ViewHolderType1) holder;
        Databean2.AdlistEntity bean = mDatas.get(position);
       final String AppSid =  bean.AID;
        final String adPlaceID = bean.CID;

        ViewGroup.LayoutParams params = viewHolderType1.mItemLayout.getLayoutParams();
        params.height = mScreenWidth/8;
        viewHolderType1.mItemLayout.setLayoutParams(params);
        viewHolderType1.mItemLayout.removeAllViews();

//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
                initAdView(AppSid, adPlaceID, viewHolderType1.mItemLayout);
//            }
//        }, position * mDelayedTime);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }




    private void initAdView(String appSid, String adPlaceID, LinearLayout layout) {
        // 代码设置AppSid，此函数必须在AdView实例化前调用
        AdView.setAppSid(mContext, appSid);
        //创建广告view
        //        String adPlaceID = "3537271";//重要：请填上你的代码位ID,否则无法请求到广告
        AdView adView = new AdView(mContext, adPlaceID);

        //设置监听器
        adView.setListener(new AdViewListener() {

            @Override
            public void onAdReady(AdView adView) {

            }

            @Override
            public void onAdShow(JSONObject jsonObject) {

            }

            @Override
            public void onAdClick(JSONObject jsonObject) {

            }

            @Override
            public void onAdFailed(String s) {

            }

            @Override
            public void onAdSwitch() {

            }

            @Override
            public void onAdClose(JSONObject jsonObject) {

            }
        });
        //将adView添加到父控件中（注：该父控件不一定为您的根控件，只要该控件能通过addView添加广告视图即可）
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        rllp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layout.removeAllViews();
        layout.addView(adView, rllp);
    }

    static class ViewHolderType1 extends RecyclerView.ViewHolder {
        @BindView(R.id.item_layout)
        LinearLayout mItemLayout;

        ViewHolderType1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
