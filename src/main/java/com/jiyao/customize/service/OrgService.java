package com.jiyao.customize.service;

public interface OrgService {

    /**
     * 获取组织列表
     * @param pageNo 当前页码
     * @param pageSize 每页的数量
     * @return 组织ID
     */
    String queryOrgList(int pageNo,int pageSize);
}
