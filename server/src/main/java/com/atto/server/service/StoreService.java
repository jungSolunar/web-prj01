package com.atto.server.service;

import com.atto.server.model.ft.Menu;
import com.atto.server.model.ft.Order;
import com.atto.server.model.ft.Store;
import com.atto.server.model.util.PagingParam;

import java.util.List;

/**
 * Created by dhjung on 2017. 11. 7..
 */
public interface StoreService extends CommonService{
    /* Store */
    List<Store> getStores(PagingParam pagingParam);
    Store getStoreByStoreSid(String storeSid);
    Store addStore(Store store);
    Store modifyStore(Store store);
    void removeStore(String storeSid);

    /* Menu */
    List<Menu> getMenus(String storeSid);
    Menu getMenuByMenuSid(String storeSid, String menuSid);
    Menu addMenu(Menu menu);
    Menu modifyMenu(Menu menu);
    void removeMenu(String storeSid, String menuSid);

    Menu actionMenu(Menu menu);

    /* Order */
    List<Order> getOrders(String storeSid);
    Order addOrder(Order order);
    void removeOrder(String storeSid, String orderSid);

    List<Order> completeOrder(Order order);
}
