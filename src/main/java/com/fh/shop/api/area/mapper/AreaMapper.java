package com.fh.shop.api.area.mapper;

import com.fh.shop.api.area.po.Area;

import java.util.List;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月09日 00:04
 */
public interface AreaMapper {

    List<Area> areaList(Integer id);
}
