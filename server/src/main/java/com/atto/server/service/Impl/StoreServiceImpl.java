package com.atto.server.service.Impl;

import com.atto.server.db.mapper.StoreMapper;
import com.atto.server.model.ft.Menu;
import com.atto.server.model.ft.Order;
import com.atto.server.model.ft.Store;
import com.atto.server.model.util.PagingParam;
import com.atto.server.service.CommonService;
import com.atto.server.service.StoreService;
import com.atto.server.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.atto.server.constant.SecurityConstant.*;


/**
 * Created by dhjung on 2017. 11. 7..
 */
@Service
public class StoreServiceImpl implements StoreService{

    private StoreMapper storeMapper;

    private List<String> tableColumnNames;

    public StoreServiceImpl(StoreMapper storeMapper){
        tableColumnNames = storeMapper.selectTableColumnNames(STORE_TABLE_NAME);
    }

    /*
       Store
    */

    @Override
    public List<Store> getStores(PagingParam pagingParam) {
        pagingParam.validateOrderColumn(tableColumnNames);
        return storeMapper.selectStores(pagingParam);
    }

    @Override
    public Store getStoreByStoreSid(String storeSid) {
        return storeMapper.selectStoreByStoreSid(storeSid);
    }

    @Override
    public Store addStore(Store store) {
        store.setStoreSid(SecurityUtil.createNewSid(STORE_UID_PREFIX));
        storeMapper.insertStore(store);

        return getStoreByStoreSid(store.getStoreSid());
    }

    @Override
    public Store modifyStore(Store store) {
        storeMapper.updateStore(store);
        return getStoreByStoreSid(store.getStoreSid());
    }

    @Override
    public void removeStore(String storeSid) {
        storeMapper.deleteStore(storeSid);
    }


    /*
       Menu
    */

    @Override
    public List<Menu> getMenus(String storeSid) {
        return storeMapper.selectMenus(storeSid);
    }

    @Override
    public Menu getMenuByMenuSid(String storeSid, String menuSid) {

        return storeMapper.selectMenuByMenuSid(new Menu(storeSid, menuSid));
    }

    @Override
    public Menu addMenu(Menu menu) {
        menu.setMenuSid(SecurityUtil.createNewSid(MENU_UID_PREFIX));
        storeMapper.insertMenu(menu);
        return getMenuByMenuSid(menu.getStoreSid(),menu.getMenuSid());
    }

    @Override
    public Menu modifyMenu(Menu menu) {
        storeMapper.updateMenu(menu);
        return getMenuByMenuSid(menu.getStoreSid(),menu.getMenuSid());
    }

    @Override
    public void removeMenu(String storeSid, String menuSid) {
        storeMapper.deleteMenu(new Menu(storeSid, menuSid));
    }

    @Override
    public Menu actionMenu(Menu menu) {
        storeMapper.updateMenu(menu);
        return getMenuByMenuSid(menu.getStoreSid(), menu.getMenuSid());
    }

    /*
       Order
    */

    @Override
    public List<Order> getOrders(String storSid) {
        return storeMapper.selectOrders(storSid);
    }

    public Order getOrderByOrderSid(String storeSid, String orderSid){
        Order order = new Order(storeSid, orderSid);
        return storeMapper.selectOrderByOrderSid(order);
    }

    @Override
    public Order addOrder(Order order) {
        order.setOrderSid(SecurityUtil.createNewSid(ORDER_UID_PREFIX));
        storeMapper.insertOrder(order);
        storeMapper.insertOrderMenu(order);
        return getOrderByOrderSid(order.getStoreSid(), order.getOrderSid());
    }

    @Override
    public void removeOrder(String storeSid, String orderSid) {
        Order order = new Order(storeSid, orderSid);

        storeMapper.deleteOrderMenu(storeSid);
        // TODO : Cancle pay
        storeMapper.deleteOrder(order);
    }

    @Override
    public List<Order> completeOrder(Order order) {
        storeMapper.updateOrder(order);
        storeMapper.deleteOrderMenu(order.getStoreSid());
        return getOrders(order.getStoreSid());
    }

    /*
       Common
    */

    @Override
    public Integer getCountNum(String tableName) {
        return storeMapper.selectCount(tableName);
    }
}
