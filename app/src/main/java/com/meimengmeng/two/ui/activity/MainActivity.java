package com.meimengmeng.two.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mobads.AdView;
import com.baidu.mobads.AdViewListener;
import com.meimengmeng.two.R;
import com.meimengmeng.two.api.Constants;
import com.meimengmeng.two.api.RetrofitHelper;
import com.meimengmeng.two.base.RxBaseActivity;
import com.meimengmeng.two.bean.DataBean1;
import com.meimengmeng.two.bean.Databean2;
import com.meimengmeng.two.bean.PopBean;
import com.meimengmeng.two.utils.ThreadPoolFactory;
import com.meimengmeng.two.utils.ToastUtil;
import com.meimengmeng.two.widget.pop.SelectSynthePop;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends RxBaseActivity {
    @BindView(R.id.tv1)
    TextView     mTv1;
    @BindView(R.id.tv2)
    TextView     mTv2;
    @BindView(R.id.title_layout)
    LinearLayout mTitleLayout;
    //    @BindView(R.id.main_layout)
    //    LinearLayout mMainLayout;

    @BindView(R.id.layout1)
    LinearLayout mLayout1;
    @BindView(R.id.layout2)
    LinearLayout mLayout2;
    @BindView(R.id.layout3)
    LinearLayout mLayout3;
    @BindView(R.id.layout4)
    LinearLayout mLayout4;
    @BindView(R.id.layout5)
    LinearLayout mLayout5;
    @BindView(R.id.layout6)
    LinearLayout mLayout6;
    @BindView(R.id.layout7)
    LinearLayout mLayout7;
    @BindView(R.id.layout8)
    LinearLayout mLayout8;
    @BindView(R.id.layout9)
    LinearLayout mLayout9;
    @BindView(R.id.layout10)
    LinearLayout mLayout10;
    @BindView(R.id.layout11)
    LinearLayout mLayout11;

    /*private List<PopBean> mData1 = new ArrayList<>();
    private List<Databean2.AdlistEntity> mData2 = new ArrayList<>();
    private Handler mHandler;
    private List<LinearLayout> mLayoutsList  = new ArrayList<>();
    private List<AdView>       mAdViewsList  = new ArrayList<>();
    private List<AdView>       mAdViewsList2 = new ArrayList<>();
    private boolean mFristFlag = true;
    private int mDelayedTime = 1000;*/

    private List<PopBean> mData1 = new ArrayList<>();

    private List<Databean2.AdlistEntity> mData2 = new ArrayList<>();

    private Handler mHandler;

    private List<LinearLayout> mLayoutsList = new ArrayList<>();

    private List<AdView> mAdViewsList  = new ArrayList<>();
    private List<AdView> mAdViewsList2 = new ArrayList<>();
    private boolean      mFristFlag    = true;
    private int          mDelayedTime  = 1000;

    private String account = "2";

    private int mIndex = 0;


    private boolean mFlag = true;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mHandler = new Handler();

        mLayoutsList.add(mLayout1);
        mLayoutsList.add(mLayout2);
        mLayoutsList.add(mLayout3);
        mLayoutsList.add(mLayout4);
        mLayoutsList.add(mLayout5);
        mLayoutsList.add(mLayout6);
        mLayoutsList.add(mLayout7);
        mLayoutsList.add(mLayout8);
        mLayoutsList.add(mLayout9);
        mLayoutsList.add(mLayout10);
        mLayoutsList.add(mLayout11);
        getData222Net(account);
        getData1Net();


    }

    @OnClick(R.id.tv1)
    public void clickTv1(View view) {

        if (mAdViewsList2.size() == 11) {
            synchronized (MainActivity.class) {
                mAdViewsList.clear();
                mAdViewsList.addAll(mAdViewsList2);
                mAdViewsList2.clear();

                initAdViewAll();

                mFlag = false;
                getData222Net(account);
            }
        } else {
            if (mAdViewsList2.size() != 11) {
                mFlag = false;
                getData222Net(account);
            } else {
                ToastUtil.showShort(this, "资源正在初始化");
            }
        }

    }

    @OnClick(R.id.tv2)
    public void clickTv2(View view) {
        getData1Net();
        SelectSynthePop selectSynthePop = new SelectSynthePop(this, mData1);
        selectSynthePop.setShareListener(new SelectSynthePop.ShareListener() {
            @Override
            public void onItem(int position) {
                //                mMainLayout.removeAllViews();
                PopBean bean = mData1.get(position);

                String[] split = bean.text.split(":");
                account = split[0];
                getData222Net(account);

            }
        });
        selectSynthePop.showAsDropDown(mTitleLayout, 0, 0);
    }


    private void getData1Net() {
        RetrofitHelper.getBaseApi().getData1Net()
                .compose(this.<DataBean1>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBean1>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(MainActivity.this, "请检查网络设置");
                    }

                    @Override
                    public void onNext(DataBean1 dataBean1) {
                        if (Constants.OK.equals(dataBean1.result)) {
                            setDataBean1(dataBean1);
                        } else {
                            ToastUtil.showShort(MainActivity.this, "数据错误");
                        }
                    }
                });
    }


    //设置bean1 的数据
    private void setDataBean1(DataBean1 dataBean1) {
        if (dataBean1.accountlist != null && dataBean1.accountlist.size() > 0) {

            mData1.clear();
            for (int i = 0, length = dataBean1.accountlist.size(); i < length; i++) {
                String s = dataBean1.accountlist.get(i);
                mData1.add(new PopBean(s));
            }
        }
    }

    private void getData222Net(String str) {
        RetrofitHelper.getBaseApi().getData2Net(str)
                .compose(this.<Databean2>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Databean2>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(MainActivity.this, "请检查网络设置");
                    }

                    @Override
                    public void onNext(Databean2 bean) {
                        if (Constants.OK.equals(bean.result)) {
                            if (mFlag) {
                                setDataBean222(bean);
                            } else {
                                setDataBean222333(bean);
                            }
                        } else {
                            ToastUtil.showShort(MainActivity.this, "数据错误");
                        }
                    }
                });
    }


    private void setDataBean222(Databean2 bean) {
        if (bean.adlist != null) {
            mData2.clear();
            mData2.addAll(bean.adlist);
            mDelayedTime = Integer.valueOf(bean.delay);

            initAsycAdView();
        }
    }

    private void setDataBean222333(Databean2 bean) {
        if (bean.adlist != null) {
            mData2.clear();
            mData2.addAll(bean.adlist);
            mDelayedTime = Integer.valueOf(bean.delay);

            mIndex = 0;
            ThreadPoolFactory.getNormalThreadPool().remove(mRunnable2);
            ThreadPoolFactory.getNormalThreadPool().excute(mRunnable2);
        }

    }

    private void initAsycAdView() {
        mIndex = 0;
        ThreadPoolFactory.getNormalThreadPool().remove(mRunnable);
        ThreadPoolFactory.getNormalThreadPool().excute(mRunnable);
    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            Databean2.AdlistEntity bean2 = mData2.get(0);
            AdView.setAppSid(MainActivity.this, bean2.AID);

            for (int i = 0; i < 11; i++) {
                final int postion = i;

                AdView adView = new AdView(MainActivity.this, bean2.CID);

                adView.setListener(new AdViewListener() {
                    public void onAdSwitch() {
                        Log.w("AdViewListener", "onAdSwitch");
                    }

                    public void onAdShow(JSONObject info) {
                        // 广告已经渲染出来
                        Log.w("AdViewListener", "onAdShow " + info.toString());
                        mIndex++;
                        if (mIndex == 11) {
                            //所有广告已经加载完成了，进行下一轮加载

                            //缓存一轮广告
                            mFlag = false;

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    getData222Net(account);
                                }
                            });
                        }
                    }

                    public void onAdReady(AdView adView) {
                        // 资源已经缓存完毕，还没有渲染出来
                        Log.w("AdViewListener", "onAdReady " + adView);
                    }

                    public void onAdFailed(String reason) {
                        Log.w("AdViewListener", "onAdFailed " + reason);
                    }

                    public void onAdClick(JSONObject info) {
                        Log.w("AdViewListener", "onAdClick " + info.toString());

                    }

                    @Override
                    public void onAdClose(JSONObject arg0) {
                        Log.w("AdViewListener", "onAdClose");
                    }
                });

                if (mAdViewsList.size() > postion) {
                    mAdViewsList.remove(postion);
                    mAdViewsList.add(postion, adView);
                } else {
                    mAdViewsList.add(adView);
                }
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initAdViewOne2One(postion);
                    }
                }, i * mDelayedTime);
            }
        }
    };

    Runnable mRunnable2 = new Runnable() {
        @Override
        public void run() {
            Databean2.AdlistEntity bean2 = mData2.get(0);
            AdView.setAppSid(MainActivity.this, bean2.AID);

            mAdViewsList2.clear();
            for (int i = 0; i < 11; i++) {

                AdView adView = new AdView(MainActivity.this, bean2.CID);

                adView.setListener(new AdViewListener() {
                    public void onAdSwitch() {
                        Log.w("AdViewListener", "onAdSwitch");
                    }

                    public void onAdShow(JSONObject info) {
                        // 广告已经渲染出来
                        Log.w("AdViewListener", "onAdShow " + info.toString());
                    }

                    public void onAdReady(AdView adView) {
                        // 资源已经缓存完毕，还没有渲染出来
                        Log.w("AdViewListener", "onAdReady " + adView);
                    }

                    public void onAdFailed(String reason) {
                        Log.w("AdViewListener", "onAdFailed " + reason);
                    }

                    public void onAdClick(JSONObject info) {
                        Log.w("AdViewListener", "onAdClick " + info.toString());

                    }

                    @Override
                    public void onAdClose(JSONObject arg0) {
                        Log.w("AdViewListener", "onAdClose");
                    }
                });
                mAdViewsList2.add(adView);
            }
        }
    };

    private void initAdViewOne2One(int position) {
        AdView adView = mAdViewsList.get(position);
        LinearLayout layout = mLayoutsList.get(position);

        //将adView添加到父控件中（注：该父控件不一定为您的根控件，只要该控件能通过addView添加广告视图即可）
        layout.removeAllViews();
        layout.addView(adView);

    }

    private void initAdViewAll() {

        for (int i = 0; i < mAdViewsList.size(); i++) {


            AdView adView = mAdViewsList.get(i);
            LinearLayout layout = mLayoutsList.get(i);
            // 将adView添加到父控件中（注：该父控件不一定为您的根控件，只要该控件能通过addView添加广告视图即可）
            layout.removeAllViews();
            layout.addView(adView);

     /*       final int position = i;

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initAdViewOne2One(position);
                }
            }, i * mDelayedTime);*/

        }
    }

  /*  @OnClick(R.id.tv1)
    public void clickTv1(View view) {
        if (mAdViewsList2.size() == 11) {
           *//* for (LinearLayout layout : mLayoutsList) {
                layout.removeAllViews();
            }*//*
            synchronized (MainActivity.class) {
                mAdViewsList.clear();
                mAdViewsList.addAll(mAdViewsList2);
                mAdViewsList2.clear();
                initAdView();
            }
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initAsycAdView2();
                }
            }, 10000);
        } else {
            ToastUtil.showShort(MainActivity.this,"资源正在初始化，请稍后再试");
        }


    }


    @OnClick(R.id.tv2)
    public void clickTv2(View view) {
        getData1Net();
        SelectSynthePop selectSynthePop = new SelectSynthePop(this, mData1);
        selectSynthePop.setShareListener(new SelectSynthePop.ShareListener() {
            @Override
            public void onItem(int position) {
                //                mMainLayout.removeAllViews();
                PopBean bean = mData1.get(position);

                String[] split = bean.text.split(":");
                getData222Net(split[0]);

            }
        });
        selectSynthePop.showAsDropDown(mTitleLayout, 0, 0);
    }


    private void getData1Net() {
        RetrofitHelper.getBaseApi().getData1Net()
                .compose(this.<DataBean1>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataBean1>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(MainActivity.this, "请检查网络设置");
                    }

                    @Override
                    public void onNext(DataBean1 dataBean1) {
                        if (Constants.OK.equals(dataBean1.result)) {
                            setDataBean1(dataBean1);
                        } else {
                            ToastUtil.showShort(MainActivity.this, "数据错误");
                        }
                    }
                });
    }


    //设置bean1 的数据
    private void setDataBean1(DataBean1 dataBean1) {
        if (mFristFlag) {
            mFristFlag = false;
            if (dataBean1.accountlist != null && dataBean1.accountlist.size() > 0) {

                String s = dataBean1.accountlist.get(0);
                String[] split = s.split(":");
                getData222Net(split[0]);
            }
        }
        if (dataBean1.accountlist != null && dataBean1.accountlist.size() > 0) {
            mData1.clear();

            for (int i = 0, length = dataBean1.accountlist.size(); i < length; i++) {
                String s = dataBean1.accountlist.get(i);
                mData1.add(new PopBean(s));
            }

        }

    }

    private void getData222Net(String str) {
        RetrofitHelper.getBaseApi().getData2Net(str)
                .compose(this.<Databean2>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Databean2>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(MainActivity.this, "请检查网络设置");
                    }

                    @Override
                    public void onNext(Databean2 bean) {
                        if (Constants.OK.equals(bean.result)) {
                            setDataBean222(bean);
                        } else {
                            ToastUtil.showShort(MainActivity.this, "数据错误");
                        }
                    }
                });
    }


    private void setDataBean222(Databean2 bean) {
        if (bean.adlist != null) {
            mData2.clear();
            mData2.addAll(bean.adlist);
            mDelayedTime = Integer.valueOf(bean.delay);

            initAsycAdView();

        }
    }

    private void initAsycAdView() {
        ThreadPoolFactory.getNormalThreadPool().excute(new Runnable() {
            @Override
            public void run() {
                Databean2.AdlistEntity bean2 = mData2.get(0);
                AdView.setAppSid(MainActivity.this, bean2.AID);

                for (int i = 0; i < 11; i++) {
                    final int postion = i;

                    AdView adView = new AdView(MainActivity.this, bean2.CID);

                    if (mAdViewsList.size() > postion) {
                        mAdViewsList.remove(postion);
                        mAdViewsList.add(postion, adView);
                    } else {
                        mAdViewsList.add(adView);
                    }
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            initAdViewOne2One(postion);
                        }
                    }, i * mDelayedTime);

                    //最后一个
                    if (i == 10) {
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initAsycAdView2();
                            }
                        }, 10000);
                    }

                }
            }
        });
    }

    public void initAsycAdView2() {
        ThreadPoolFactory.getNormalThreadPool().excute(new Runnable() {
            @Override
            public void run() {
                Databean2.AdlistEntity bean2 = mData2.get(0);
                AdView.setAppSid(MainActivity.this, bean2.AID);

                mAdViewsList2.clear();
                for (int i = 0; i < 11; i++) {

                    AdView adView = new AdView(MainActivity.this, bean2.CID);
                    mAdViewsList2.add(adView);
                }
            }
        });

    }


    private void initAdViewOne2One(int position) {
        AdView adView = mAdViewsList.get(position);
        LinearLayout layout = mLayoutsList.get(position);

        //将adView添加到父控件中（注：该父控件不一定为您的根控件，只要该控件能通过addView添加广告视图即可）
        layout.removeAllViews();
        layout.addView(adView);
    }

    private void initAdView() {

        for (int i = 0; i < 11; i++) {
            AdView adView = mAdViewsList.get(i);
            LinearLayout layout = mLayoutsList.get(i);

            //将adView添加到父控件中（注：该父控件不一定为您的根控件，只要该控件能通过addView添加广告视图即可）
            layout.removeAllViews();
            layout.addView(adView);
        }
    }*/
}
