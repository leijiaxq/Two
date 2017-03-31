package com.meimengmeng.two.api;


import com.meimengmeng.two.bean.DataBean1;
import com.meimengmeng.two.bean.Databean2;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Create by  leijiaxq
 * Date       2017/3/2 16:57
 * Describe
 */

public interface BaseService {

    //
    @GET("JL/ssp/getsspAccount.jsp")
    Observable<DataBean1> getData1Net();

    //
    @GET("JL/ssp/getsspAdList.jsp")
    Observable<Databean2> getData2Net(@Query("account") String account);


}
