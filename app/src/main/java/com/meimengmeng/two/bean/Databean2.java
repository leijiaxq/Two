package com.meimengmeng.two.bean;

import java.util.List;

/**
 * Create by  leijiaxq
 * Date       2017/3/16 14:07
 * Describe
 */
public class Databean2 {


    /**
     * result : ok
     * delay : 1000
     * adlist : [{"AID":"c509245f","CID":"3551848","id":11},{"AID":"aa0741b4","CID":"3551847","id":12},{"AID":"fbd82f4d","CID":"3551851","id":9},{"AID":"a062bf91","CID":"3551850","id":10},{"AID":"b751ca00","CID":"3551854","id":7},{"AID":"b0a12708","CID":"3551853","id":8},{"AID":"b75479c5","CID":"3551858","id":5},{"AID":"c4c7985f","CID":"3551856","id":6},{"AID":"e5b1d970","CID":"3551861","id":3},{"AID":"ac503795","CID":"3551860","id":4},{"AID":"dfb81849","CID":"3551863","id":2},{"AID":"d211a24a","CID":"3551864","id":1}]
     */

    public String result;
    public String             delay;
    public List<AdlistEntity> adlist;

    public static class AdlistEntity {
        /**
         * AID : c509245f
         * CID : 3551848
         * id : 11
         */

        public String AID;
        public String CID;
        public int    id;
    }
}
