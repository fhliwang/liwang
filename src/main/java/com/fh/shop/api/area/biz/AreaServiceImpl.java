package com.fh.shop.api.area.biz;

import com.fh.shop.api.area.mapper.AreaMapper;
import com.fh.shop.api.area.po.Area;
import com.fh.shop.api.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月09日 00:02
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaMapper areaMapper;

    public ServerResponse areaList(Integer id) {
        List<Area> areas = areaMapper.areaList(id);
        return ServerResponse.success(areas);
    }
}
