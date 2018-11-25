package com.fh.shop.api.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年10月30日 18:15
 */
public class DatatableResult<T>  implements Serializable {

    private static final long serialVersionUID = 3141927590392690927L;

    private Long recordsFiltered;
    private Long recordsTotal;
    private List<T> data = new ArrayList<T>();

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
