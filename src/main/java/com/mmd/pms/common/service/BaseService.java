package com.mmd.pms.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmd.pms.common.dao.BaseDao;
import com.mmd.pms.common.entity.BaseEntity;
import com.mmd.pms.common.page.PageParam;
import com.mmd.pms.util.StringUtils;

import java.util.List;

/**
 * 所有服务类的超接口
 */
public interface BaseService<D extends BaseDao<T>, T extends BaseEntity> {

    /**
     * 根据ID查询一条数据
     * @param id
     * @return
     */
    T queryById (String id) ;

    /**
     * 查询列表
     * @param entity
     * @return
     */
    List<T> queryList (T entity) ;



    /**
     * 分页查询列表 pageHelper PageInfo 分页参数，起始页码 每一页的大小， 排序
     * @param entity
     * @param pageParam
     * @return
     */
    PageInfo<T> queryListWithPage (T entity, PageParam pageParam) ;

    /**
     * 保存数据 id为空时，执行新增，id不为空，修改
     * @param entity
     * @return
     */
    Boolean saveOrUpdate (T entity);

    /**
     * 删除数据
     * @param entity
     * @return
     */
    Boolean delete (T entity);

}
