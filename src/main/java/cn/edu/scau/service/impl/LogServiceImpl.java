package cn.edu.scau.service.impl;

import cn.edu.scau.dao.LogMapper;
import cn.edu.scau.entity.Log;
import cn.edu.scau.component.Page;
import cn.edu.scau.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 删除单条日志
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> deleteOne(Map<String, Integer> request) {
        Map<String, Object> response = new HashMap<String, Object>();
        //获取要删除日志的编号
        Integer id = request.get("id");
        //删除日志
        if (logMapper.deleteOne(id) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    /**
     * 批量删除日志
     * @param ids
     * @return
     */
    @Override
    public Map<String, Object> deleteMore(Integer[] ids) {
        Map<String, Object> response = new HashMap<String, Object>();
        //删除批量选定的日志
        if (logMapper.deleteMore(ids) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    /**
     * 查找日志
     * @param page
     * @return
     */
    public Page<Log> serach(Page<Log> page) {
        //查询搜索记录总数
        int totalNum = logMapper.getSearchNum(page);
        //设置搜索记录总数
        page.setTotalRecord(totalNum);
        //设置总页数和偏移量
        int totalPage = (int) Math.ceil((double) totalNum / page.getPageSize());
        int startIndex = page.getPageSize() * (page.getPageNum() - 1);
        page.setTotalPage(totalPage);
        page.setStartIndex(startIndex);
        //查询日志
        List<Log> logs = logMapper.findRecords(page);
        //设置当前页需要显示的记录
        page.setRecords(logs);
        //设置当前页的实际记录数
        page.setRecordNum(logs.size());
        return page;

    }

}
