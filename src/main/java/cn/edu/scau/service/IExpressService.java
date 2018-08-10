package cn.edu.scau.service;

import cn.edu.scau.component.Page;
import cn.edu.scau.dto.Result;
import cn.edu.scau.entity.Express;

import java.util.List;
import java.util.Map;

public interface IExpressService {

    /**
     * 添加快递
     * @param name
     * @return
     */
    public Result<String> insert(String name);

    public Result<String> delete(Integer[] ids);

    public Result<Express> get(Integer id);

    public Result<String> update(Express express);

    /**
     * 获取快递列表
     * @param pageNum 页
     * @param pageSize 数量
     * @return
     */
    Result<Page<Express>> getPage(Integer pageNum, Integer pageSize);

    /**
     * 搜索快递
     * @param pageNum 页
     * @param pageSize 数量
     * @param key 关键字
     * @return
     */
    Result<Page<Express>> search(Integer pageNum, Integer pageSize, String key);


    /**
     * 获取全部快递
     * @return
     */
    Result<List<Express>> getAll();
}
