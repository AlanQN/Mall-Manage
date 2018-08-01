package cn.edu.scau.service.impl;

import cn.edu.scau.component.Page;
import cn.edu.scau.dao.ExpressMapper;
import cn.edu.scau.dto.Result;
import cn.edu.scau.entity.Express;
import cn.edu.scau.service.IExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpressServiceImpl implements IExpressService {

    @Autowired
    private ExpressMapper expressMapper;
    @Autowired
    private Result<String> expressStringResult;
    @Autowired
    private Result<Express> expressResult;
    @Autowired
    private Result<Page<Express>> expressPageResult;
    @Autowired
    private Page<Express> expressPage;

    @Override
    public Result<String> insert(String name) {
        if (expressMapper.selectByName(name) == null) {
            expressMapper.insert(name);
            expressStringResult.setSuccess(true);
            expressStringResult.setData("成功");
            expressStringResult.setError(null);
        } else {
            expressStringResult.setSuccess(false);
            expressStringResult.setData(null);
            expressStringResult.setError("已存在");
        }
        return expressStringResult;
    }

    @Override
    public Result<String> delete(Integer[] ids) {
        Integer i = expressMapper.deleteMore(ids);
        if (i == 0) {
            expressStringResult.setSuccess(false);
            expressStringResult.setData(null);
            expressStringResult.setError("失败");
        } else {
            expressStringResult.setSuccess(true);
            expressStringResult.setData("删除" + i + "个快递");
            expressStringResult.setError(null);
        }
        return expressStringResult;
    }

    @Override
    public Result<Express> get(Integer id) {
        Express express = expressMapper.selectByPrimaryKey(id);
        if (express == null) {
            expressResult.setSuccess(false);
            expressResult.setData(null);
            expressResult.setError("不存在");
        } else {
            expressResult.setSuccess(true);
            expressResult.setData(express);
            expressResult.setError(null);
        }
        return expressResult;
    }

    @Override
    public Result<String> update(Express express) {
        if (expressMapper.selectByName(express.getExpressName()) != null || expressMapper.updateByPrimaryKey(express) == 0) {
            expressStringResult.setSuccess(false);
            expressStringResult.setData(null);
            expressStringResult.setError("存在同名的快递");
        } else {
            expressStringResult.setSuccess(true);
            expressStringResult.setData("成功");
            expressStringResult.setError(null);
        }
        return expressStringResult;
    }


    @Override
    public Result<Page<Express>> getPage(Integer pageNum, Integer pageSize) {
        expressPage.setPageNum(pageNum);
        expressPage.setPageSize(pageSize);
        expressPage.setKeyword(null);
        //查询总数
        Integer totalNum = expressMapper.getTotal(expressPage);
        expressPage.setTotalRecord(totalNum);
        //设置总页数和偏移量
        Integer totalPage = (int) Math.ceil((double) totalNum / pageSize);
        Integer startIndex = pageSize * (pageNum - 1);
        expressPage.setTotalPage(totalPage);
        expressPage.setStartIndex(startIndex);
        //查询当前界面快递
        List<Express> expressList = expressMapper.findRecords(expressPage);

        if (expressList != null && expressList.size() > 0) {
            expressPageResult.setSuccess(true);
            expressPage.setRecordNum(expressList.size());
            expressPage.setRecords(expressList);
            expressPageResult.setData(expressPage);
            expressPageResult.setError(null);
        }else {
            expressPageResult.setSuccess(false);
            expressPageResult.setData(null);
            expressPageResult.setError("获取失败");
        }
        return expressPageResult;
    }


    /**
     * @param pageNum  页
     * @param pageSize 数量
     * @param key      关键字
     * @return
     */
    @Override
    public Result<Page<Express>> search(Integer pageNum, Integer pageSize, String key) {
        expressPage.setPageNum(pageNum);
        expressPage.setPageSize(pageSize);
        expressPage.setKeyword(key);
        //查询总数
        Integer totalNum = expressMapper.getTotal(expressPage);
        expressPage.setTotalRecord(totalNum);
        //设置总页数和偏移量
        Integer totalPage = (int) Math.ceil((double) totalNum / pageSize);
        Integer startIndex = pageSize * (pageNum - 1);
        expressPage.setTotalPage(totalPage);
        expressPage.setStartIndex(startIndex);
        //查询当前界面快递
        List<Express> expressList = expressMapper.findRecords(expressPage);

        if (expressList != null && expressList.size() > 0) {
            expressPageResult.setSuccess(true);
            expressPage.setRecordNum(expressList.size());
            expressPage.setRecords(expressList);
            expressPageResult.setData(expressPage);
            expressPageResult.setError(null);
        }else {
            expressPageResult.setSuccess(false);
            expressPageResult.setData(null);
            expressPageResult.setError("获取失败");
        }

        return expressPageResult;
    }

}
