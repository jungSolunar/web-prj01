package com.atto.server.db.mapper;

import com.atto.server.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PermissionMapper {

    // used at AuthorityManager
	Boolean hasPermissionByUser(Permission permission);

	// uses at PermissionService

	List<PermDomain> selectPermDomains();
	String selectPermDomainsByAclId(String rscAclId);
	List<String> selectRscUidByPermission(Map<String, String> permission);
	List<Resource> selectRscByPermission(Map<String, String> permission);
	List<String> selectGroupUids();

	String selectPermDomainNameByRscUid(String rscUid);

	void insertPermission(UserGroupResource userGroupResource);

	void deletePermissionsByGroupUid(String groupUid);
	void deletePermission(UserGroupResource userGroupResource);
	void deletePermissions(List<UserGroupResource> userGroupResourceList);

	void insertPermDomain(PermDomain permDomain);
	void updatePermDomain(PermDomain permDomain);
	void deletePermDomain(String permDomainId);

	void insertRscPermDomain(RscPermDomain rscPermDomain);
	void updateRscPermDomain(RscPermDomain rscPermDomain);
	void deleteRscPermDomain(RscPermDomain rscPermDomain);

}