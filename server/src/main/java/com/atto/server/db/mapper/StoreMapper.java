package com.atto.server.db.mapper;

import com.atto.server.model.ft.Menu;
import com.atto.server.model.ft.Order;
import com.atto.server.model.ft.Store;
import com.atto.server.model.util.PagingParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by dhjung on 2017. 11. 7..
 */
@Mapper
public interface StoreMapper extends CommonMapper{
    /*
        Store
     */
    List<Store> selectStores(PagingParam pagingParam);
    Store selectStoreByStoreSid(String storeSid);
    void insertStore(Store store);
    void updateStore(Store store);
    void deleteStore(String storeSid);

    /*
        Menu
     */
    List<Menu> selectMenus(String storeSid);
    Menu selectMenuByMenuSid(Menu menu);     // storeSid, menuSid
    void insertMenu(Menu menu);
    void updateMenu(Menu menu);
    void deleteMenu(Menu menu);     // storeSid, menuSid

    /*
       Order
     */
    List<Order> selectOrders(String storeSid);
    Order selectOrderByOrderSid(Order order);
    void insertOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(Order order);

    void insertOrderMenu(Order order);
    void deleteOrderMenu(String orderSid);
}
