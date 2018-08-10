package cn.edu.scau.service;

import cn.edu.scau.dto.Result;
import cn.edu.scau.entity.Count;

import java.util.List;

public interface ICountService {

    /**
     *
     * @param start 开始时间
     * @param end 结束
     * @return
     */
    Result<List<Count>> getDate(String start , String end);
}
