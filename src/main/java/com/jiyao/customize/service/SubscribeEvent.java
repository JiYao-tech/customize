package com.jiyao.customize.service;

public interface SubscribeEvent {

     /**
      * 订阅可是对讲事件
      */
     void subscribeAccessEvent();

     /**
      * 查询订阅的事件
      */
     void queryEventSubscriptionView();
}
