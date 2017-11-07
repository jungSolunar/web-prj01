package com.atto.server.db.mapper;

import com.atto.server.model.security.PermDomain;
import com.atto.server.model.security.Permission;
import com.atto.server.model.security.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PermissionMapper {

    // used at AuthorityManager
	Boolean hasPermissionByUser(Permission permission);

	// uses at PermissionService

	List<Permission> selectPermissionsByUserUid(String userUid);


}