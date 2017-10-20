package com.atto.server.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atto.server.db.mapper.MenuResourceMapper;
import com.atto.server.model.Menu;
import com.atto.server.model.Resource;
import com.atto.server.service.ResourceService;
import com.atto.server.util.SecurityContext;
import com.atto.server.util.TimeUtil;

/**
 * Created by dhjung on 2017. 9. 6..
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuResourceMapper menuResourceMapper;

    @Override
    public List<Resource> getResources() {
        return menuResourceMapper.selectResources();
    }

    @Override
    public Resource addResource(Resource resource) {
//        resource.setCreateUid(SecurityContext.get().getUserUid());
//        resource.setCreateDtm(TimeUtil.getCurrentDateString());
        try {
            menuResourceMapper.insertResource(resource);
        } catch (Exception e) {
            // TODO Handle Exception Case
            e.printStackTrace();
            return null;
        }
        return resource;
    }

    @Override
    public List<Resource> addResources(List<Resource> resources) {
        /* TODO implements if it needs */
        return null;
    }

    @Override
    public Resource modifyResource(Resource resource) {
        Resource storedRsc = menuResourceMapper.selectResource(resource.getRscUid());
//        resource.setCreateUid(storedRsc.getCreateUid());
//        resource.setCreateDtm(storedRsc.getCreateDtm());
//
//        resource.setUpdateUid(SecurityContext.get().getUserUid());
//        resource.setUpdateDtm(TimeUtil.getCurrentDateString());

        try {
            menuResourceMapper.updateResource(resource);
        } catch (Exception e) {
            // TODO Handle Exception Case
            e.printStackTrace();
            return null;
        }
        return resource;
    }

    @Override
    public List<Resource> modifyResources(List<Resource> resources) {
        /* TODO implements if it needs */
        return null;
    }

    @Override
    public void removeResource(String rscUid) {
        try {
            menuResourceMapper.deleteResource(rscUid);
        }catch (Exception e){
            // TODO Handle Exception Case
            e.printStackTrace();
        }
    }

    @Override
    public void removeResources(List<Resource> resources) {
        /* TODO implements if it needs */
    }

    @Override
    public List<Menu> getMenus() {
        return menuResourceMapper.selectMenus();
    }

    @Override
    public Menu addMenu(Menu menu) {
        try {
            menuResourceMapper.insertMenu(menu);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return menu;
    }

    @Override
    public List<Menu> addMenus(List<Menu> menus) {
        return null;
    }

    @Override
    public Menu modifyMenu(Menu menu) {
        try {
            menuResourceMapper.updateMenu(menu);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return menu;
    }

    @Override
    public List<Menu> modifyMenus(List<Menu> menus) {
        return null;
    }

    @Override
    public void removeMenu(String menuUid) {
        try {
            menuResourceMapper.deleteMenu(menuUid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeMenus(List<Menu> menus) {

    }
}
