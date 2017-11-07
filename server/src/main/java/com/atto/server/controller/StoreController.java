package com.atto.server.controller;

import com.atto.server.model.ft.Menu;
import com.atto.server.model.ft.Order;
import com.atto.server.model.ft.Store;
import com.atto.server.model.util.PagingParam;
import com.atto.server.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import static com.atto.server.constant.SecurityConstant.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dhjung on 2017. 10. 30..
 */
@RestController
public class StoreController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private StoreService storeService;

    /*
       Store
    */

    @RequestMapping(value = "/stores", method = RequestMethod.GET)
    public Map<String, Object> getStoreList(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(value = "size", defaultValue = "5", required=false) int size,
                                        @RequestParam(value = "startIdx", defaultValue = "0", required=false) int startIdx,
                                        @RequestParam(value = "orderCol", defaultValue = "STORE_NAME", required=false) String orderCol,
                                        @RequestParam(value = "order", defaultValue = "ASC", required=false) String order) {

        PagingParam pagingParam = new PagingParam(size, startIdx, orderCol, order);

        logger.trace("[REQUEST] " + request.getMethod() + " " + request.getRequestURI() + " Params: " + pagingParam.toString() );

        List<Store> storeList = storeService.getStores(pagingParam);
        int storeCount = storeService.getCountNum(STORE_TABLE_NAME);

        Map<String, Object> resp = new HashMap<>();
        resp.put("total", storeCount);
        resp.put("stores", storeList);

        return resp;
    }

    @RequestMapping(value = "/stores/{storeSid}", method = RequestMethod.GET)
    public Store getStore(HttpServletRequest request, HttpServletResponse response, @PathVariable String storeSid) {

        logger.trace("[REQUEST] GET " + request.getRequestURI());

        return storeService.getStoreByStoreSid(storeSid);
    }

    @RequestMapping(value = "/stores", method = RequestMethod.POST)
    public Store postStore(HttpServletRequest request, HttpServletResponse response, @RequestBody Store store) {

        logger.trace("[REQUEST] " + request.getMethod() + " " + request.getRequestURI());

        return storeService.addStore(store);
    }

    @RequestMapping(value = "/stores/{storeSid}", method = RequestMethod.PUT)
    public Store putStore(HttpServletRequest request, HttpServletResponse response, @PathVariable String storeSid, @RequestBody Store store) {

        logger.trace("[REQUEST] " + request.getMethod() + " " + request.getRequestURI());

        store.setStoreSid(storeSid);

        return storeService.modifyStore(store);
    }

    @RequestMapping(value = "/stores/{storeSid}", method = RequestMethod.DELETE)
    public void deleteStore(HttpServletRequest request, HttpServletResponse response, @PathVariable String storeSid) {

        logger.trace("[REQUEST] " + request.getMethod() + " " + request.getRequestURI());

        storeService.removeStore(storeSid);
    }

    /*
       Menu
    */

    @RequestMapping(value = "/stores/{storeSid}/menus", method = RequestMethod.GET)
    public Map<String, Object> getMenuList(HttpServletRequest request, HttpServletResponse response, @PathVariable String storeSid) {

        logger.trace("[REQUEST] " + request.getMethod() + " " + request.getRequestURI());

        Map<String, Object> resp = new HashMap<>();
        resp.put("total", storeService.getCountNum(MENU_TABLE_NAME));
        resp.put("menus", storeService.getMenus(storeSid));

        return resp;
    }

    @RequestMapping(value = "/stores/{storeSid}/menus/{menuSid}", method = RequestMethod.GET)
    public Menu getMenu(HttpServletRequest request, HttpServletResponse response, @PathVariable String storeSid, @PathVariable String menuSid) {

        logger.trace("[REQUEST] GET " + request.getRequestURI());

        return storeService.getMenuByMenuSid(storeSid, menuSid);
    }

    @RequestMapping(value = "/stores/{storeSid}/menus", method = RequestMethod.POST)
    public Menu postMenu(HttpServletRequest request, HttpServletResponse response, @PathVariable String storeSid, @RequestBody Menu menu) {

        logger.trace("[REQUEST] " + request.getMethod() + " " + request.getRequestURI());

        menu.setStoreSid(storeSid);

        return storeService.addMenu(menu);
    }

    @RequestMapping(value = "/stores/{storeSid}/menus/{menuSid}", method = RequestMethod.PUT)
    public Menu putMenu(HttpServletRequest request, HttpServletResponse response, @PathVariable String storeSid, @PathVariable String menuSid, @RequestBody Menu menu) {

        logger.trace("[REQUEST] " + request.getMethod() + " " + request.getRequestURI());

        menu.setStoreSid(storeSid);
        menu.setMenuSid(menuSid);

        return storeService.modifyMenu(menu);
    }

    @RequestMapping(value = "/stores/{storeSid}/menus/{menuSid}", method = RequestMethod.DELETE)
    public void deleteMenu(HttpServletRequest request, HttpServletResponse response, @PathVariable String storeSid, @PathVariable String menuSid) {

        logger.trace("[REQUEST] " + request.getMethod() + " " + request.getRequestURI());

        storeService.removeMenu(storeSid, menuSid);
    }

    @RequestMapping(value = "/stores/{storeSid}/menus/{menuSid}/action", method = RequestMethod.POST)
    public Menu actionMenu(HttpServletRequest request, HttpServletResponse response, @PathVariable String storeSid, @PathVariable String menuSid, @RequestBody Map<String, Boolean> action) {

        logger.trace("[REQUEST] " + request.getMethod() + " " + request.getRequestURI());

        Menu menu = new Menu(storeSid, menuSid);
        menu.setVisible(action.get("active"));

        return storeService.modifyMenu(menu);
    }

    /*
       Order
    */

    @RequestMapping(value = "/stores/{storeSid}/orders", method = RequestMethod.GET)
    public Map<String, Object> getOrders(HttpServletRequest request, HttpServletResponse response, @PathVariable String storeSid) {

        logger.trace("[REQUEST] " + request.getMethod() + " " + request.getRequestURI());

        Map<String, Object> resp = new HashMap<>();
        resp.put("total", storeService.getCountNum(ORDER_TABLE_NAME));
        resp.put("orders", storeService.getOrders(storeSid));

        return resp;
    }

    @RequestMapping(value = "/stores/{storeSid}/orders", method = RequestMethod.POST)
    public Order postOrder(HttpServletRequest request, HttpServletResponse response, @PathVariable String storeSid, @RequestBody Order order) {

        logger.trace("[REQUEST] " + request.getMethod() + " " + request.getRequestURI());

        return storeService.addOrder(order);
    }

    @RequestMapping(value = "/stores/{storeSid}/orders/{orderSid}", method = RequestMethod.DELETE)
    public void deleteOrder(HttpServletRequest request, HttpServletResponse response, @PathVariable String storeSid, @PathVariable String orderSid) {

        logger.trace("[REQUEST] " + request.getMethod() + " " + request.getRequestURI());

        storeService.removeOrder(storeSid, orderSid);
    }

    @RequestMapping(value = "/stores/{storeSid}/orders/{orderSid}", method = RequestMethod.POST)
    public List<Order> actionOrder(HttpServletRequest request, HttpServletResponse response, @PathVariable String storeSid, @PathVariable String orderSid, @RequestBody Map<String, Boolean> complete) {

        logger.trace("[REQUEST] " + request.getMethod() + " " + request.getRequestURI());

        Order order = new Order(storeSid, orderSid);
        order.setComplete(complete.get("complete"));

        return storeService.completeOrder(order);
    }
}
