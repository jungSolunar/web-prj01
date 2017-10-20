package com.atto.server.service;

import java.util.List;

import com.atto.server.model.Menu;
import com.atto.server.model.Resource;

/**
 * Created by dhjung on 2017. 9. 5..
 */
public interface ResourceService {

    /* Resources */
    List<Resource> getResources();
    Resource addResource(Resource resource);
    List<Resource> addResources(List<Resource> resources);
    Resource modifyResource(Resource resource);
    List<Resource> modifyResources(List<Resource> resources);
    void removeResource(String rscUid);
    void removeResources(List<Resource> resources);

    /* Menus */
    List<Menu> getMenus();
    Menu addMenu(Menu menu);
    List<Menu> addMenus(List<Menu> menus);
    Menu modifyMenu(Menu menu);
    List<Menu> modifyMenus(List<Menu> menus);
    void removeMenu(String menuUid);
    void removeMenus(List<Menu> menus);

}
