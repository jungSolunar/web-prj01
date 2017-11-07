package com.atto.server.db.mapper;

import com.atto.server.model.ft.Menu;
import com.atto.server.model.security.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by dhjung on 2017. 9. 19..
 */
@Mapper
public interface MenuResourceMapper {
    List<Resource> selectResources();
    Resource selectResource(String rscUid);
    Resource selectResourceByAclId(String rscAclId);
    List<Resource> selectResourcesByGroupId(String groupUid);

    // TEST CODE
    List<Resource> selectResourcesByUserUid(String userUid);

    void insertResource(Resource resource);
    void updateResource(Resource resource);
    void deleteResource(String rscUid);

    List<Menu> selectMenus();
    void insertMenu(Menu menu);
    void updateMenu(Menu menu);
    void deleteMenu(String menuUid);
}
