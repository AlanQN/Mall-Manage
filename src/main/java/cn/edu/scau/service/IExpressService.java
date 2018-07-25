package cn.edu.scau.service;

import cn.edu.scau.component.Page;
import cn.edu.scau.entity.Express;

import java.util.Map;

public interface IExpressService {

    public Map<String, Object> insert(String name);

    public Map<String, Object> delete(Integer id);

    public Map<String, Object> get(Integer id);

    public Map<String, Object> update(Express express);

    public Map<String,Object> search(String string);

    public Map<String, Object> getAll();

    Page<Express> getPage(Page<Express> page);

    Map<String,Object> deleteMore(Integer[] ids);

}
