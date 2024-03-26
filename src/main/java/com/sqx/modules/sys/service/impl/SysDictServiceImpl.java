package com.sqx.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqx.common.utils.PageUtils;
import com.sqx.modules.sys.entity.SysDictEntity;
import com.sqx.modules.sys.dao.SysDictDao;
import com.sqx.modules.sys.service.SysDictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {

    @Override
    public PageUtils queryPage(Integer page,Integer limit,String name,String parentId,String type,Integer status) {
        IPage<SysDictEntity> pages = baseMapper.selectPage(new Page<>(page,limit),
                new QueryWrapper<SysDictEntity>()
                        .like(StringUtils.isNotBlank(name),"name", name)
                        .eq(StringUtils.isNotBlank(parentId),"parent_id",parentId)
                        .eq(status!=null && status!=0,"status",status)
                        .eq(StringUtils.isNotBlank(type),"type",type).orderByDesc("order_num"));

        return new PageUtils(pages);
    }

}
