package cn.edu.scau.service;

import cn.edu.scau.entity.Log;
import cn.edu.scau.component.Page;

import java.util.Map;

public interface ILogService {

    /**
     * 删除单条日志
     * @param request
     * @return
     */
    public Map<String, Object> deleteOne(Map<String, Integer> request);

    /**
     * 批量删除日志
     * @param request
     * @return
     */
    public Map<String, Object> deleteMore(Map<String, Integer[]> request);

    /**
     * 查找日志
     * @param page
     * @return
     */
    public Page<Log> serach(Page<Log> page);

}
