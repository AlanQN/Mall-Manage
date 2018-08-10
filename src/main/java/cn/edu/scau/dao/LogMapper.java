package cn.edu.scau.dao;

import cn.edu.scau.entity.Log;
import cn.edu.scau.component.Page;

import java.util.List;

public interface LogMapper {

    /**
     * 删除单条日志
     * @param id
     * @return
     */
    public int deleteOne(Integer id);

    /**
     * 删除批量选定的日志
     * @param ids
     * @return
     */
    public int deleteMore(Integer[] ids);

    /**
     * 获取记录总数
     * @param page
     * @return
     */
    public int getSearchNum(Page page);

    /**
     * 查找日志
     * @param page
     * @return
     */
    public List<Log> findRecords(Page<Log> page);

    /**
     * 添加日志
     * @param log
     * @return
     */
    public int insert(Log log);

    /**
     * 获取浏览量
     * @return
     */
    public int getTotalViewNum();

    Log selectByPrimaryKey(Integer id);

}