package com.zjzc.manage.core.results;

import com.zjzc.manage.core.model.vo.BaseSearchVo;

import java.util.List;

/**
 * 分页返回-封装
 * @author qinzihao
 *
 * @param <T>
 */
public class PageInfo<T> {
    /** 总数 */
    private int count;

    /** 页数 */
    private int page;

    /** 总页数 */
    private int pageCount;

    /** 列表 */
    private List<T> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     *  生成分页实体
     * @param svo 查询参数 查询的分页参数
     * @param paginInfo 分页实体
     * @param list  总查询list
     * @param totalNum 总条数
     * @return
     */
    public PageInfo<T> makePageInfo(BaseSearchVo svo, PageInfo<T> paginInfo, List list, int totalNum){
        if (list.size() > 0) {
            int totalPage = totalNum % svo.getSize() == 0 ? totalNum / svo.getSize(): totalNum / svo.getSize() + 1;
            paginInfo.setCount(totalNum);
            paginInfo.setPage(svo.getPage());
            paginInfo.setPageCount(totalPage);
        } else {
            paginInfo.setCount(0);
            paginInfo.setPage(svo.getPage());
            paginInfo.setPageCount(0);
        }
        return paginInfo;
    }
}