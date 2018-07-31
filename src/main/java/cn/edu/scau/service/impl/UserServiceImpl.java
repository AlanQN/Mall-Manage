package cn.edu.scau.service.impl;

import cn.edu.scau.component.Page;
import cn.edu.scau.dao.UserMapper;
import cn.edu.scau.entity.User;
import cn.edu.scau.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User find(Map<String, Integer> request) {
        //获取id
        Integer id = request.get("id");
        User user = userMapper.findById(id);
        return user;
    }

    @Override
    public Map<String, Object> add(User user) {
        Map<String, Object> response = new HashMap<String, Object>();
        Integer errorCode = -1;
        //设置创建时间和更新时间
        user.setCreated(new Date());
        user.setUpdated(new Date());
        if (userMapper.findByUniqueField(user) != null) {    //判断唯一字段是否发生重复
            errorCode = 1;
        } else {
            userMapper.insert(user);
            errorCode = 0;
        }
        response.put("errorCode", errorCode);
        return response;
    }

    @Override
    public Map<String, Object> removeOne(Map<String, Integer> request) {
        Map<String, Object> response = new HashMap<String, Object>();
        //获取要移除用户的id
        Integer id = request.get("id");
        if (userMapper.removeOne(id) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Map<String, Object> removeMore(Map<String, Integer[]> request) {
        Map<String, Object> response = new HashMap<String, Object>();
        //获取要移除用户的编号
        Integer[] ids = request.get("ids");
        //移除指定的用户
        if (userMapper.removeMore(ids) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Map<String, Object> pause(Map<String, Integer> request) {
        Map<String, Object> response = new HashMap<String, Object>();
        //获取要禁用用户的id
        Integer id = request.get("id");
        //禁用用户
        if (userMapper.pause(id) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Map<String, Object> resume(Map<String, Integer> request) {
        Map<String, Object> response = new HashMap<String, Object>();
        //获取要启用用户的id
        Integer id = request.get("id");
        //启用用户
        if (userMapper.resume(id) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Map<String, Object> modify(User user) {
        Map<String, Object> response = new HashMap<String, Object>();
        //修改用户信息
        if (userMapper.findByUniqueField(user) == null && userMapper.update(user) > 0) {
            response.put("errorCode", 0);
        } else {
            response.put("errorCode", 1);
        }
        return response;
    }

    @Override
    public Map<String, Object> changepwd(User user) {
        Map<String, Object> response = new HashMap<String, Object>();
        //修改用户密码
        if (userMapper.update(user) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Map<String, Object> deleteOne(Map<String, Integer> request) {
        Map<String, Object> response = new HashMap<String, Object>();
        //获取要删除的用户编号
        Integer id = request.get("id");
        //删除用户
        if (userMapper.deleteOne(id) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Map<String, Object> deleteMore(Map<String, Integer[]> request) {
        Map<String, Object> response = new HashMap<String, Object>();
        //获取要删除用户的编号
        Integer[] ids = request.get("ids");
        //删除批量选定的用户
        if (userMapper.deleteMore(ids) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Map<String, Object> restoreOne(Map<String, Integer> request) {
        Map<String, Object> response = new HashMap<String, Object>();
        //获取要还原的用户编号
        Integer id = request.get("id");
        //还原用户
        if (userMapper.restoreOne(id) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Map<String, Object> restoreMore(Map<String, Integer[]> request) {
        Map<String, Object> response = new HashMap<String, Object>();
        //获取要还原用户的编号
        Integer[] ids = request.get("ids");
        //还原批量选定的用户
        if (userMapper.restoreMore(ids) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }

    @Override
    public Page<User> findNormalByPage(Page<User> page) {
        //查询用户总数
        int totalNum = userMapper.getNormalUserNum();
        //设置用户总数
        page.setTotalRecord(totalNum);
        //设置总页数和偏移量
        int totalPage = (int) Math.ceil((double) totalNum / page.getPageSize());
        int startIndex = page.getPageSize() * (page.getPageNum() - 1);
        page.setTotalPage(totalPage);
        page.setStartIndex(startIndex);
        //查询当前页的用户
        List<User> users = userMapper.findNormalByPage(page);
        //设置当前页需要显示的记录
        page.setRecords(users);
        //设置当前页的实际记录数
        page.setRecordNum(users.size());
        return page;
    }

    @Override
    public Page<User> findRemoveByPage(Page<User> page) {
        //查询用户总数
        int totalNum = userMapper.getRemoveUserNum();
        //设置用户总数
        page.setTotalRecord(totalNum);
        //设置总页数和偏移量
        int totalPage = (int) Math.ceil((double) totalNum / page.getPageSize());
        int startIndex = page.getPageSize() * (page.getPageNum() - 1);
        page.setTotalPage(totalPage);
        page.setStartIndex(startIndex);
        //查询当前页的用户
        List<User> users = userMapper.findRemoveByPage(page);
        //设置当前页需要显示的记录
        page.setRecords(users);
        //设置当前页的实际记录数
        page.setRecordNum(users.size());
        return page;
    }

    @Override
    public Page<User> fuzzyFindByField(Page<User> page) {
        //查询搜索记录总数
        int totalNum = userMapper.getSearchNum(page);
        //设置搜索记录总数
        page.setTotalRecord(totalNum);
        //设置总页数和偏移量
        int totalPage = (int) Math.ceil((double) totalNum / page.getPageSize());
        int startIndex = page.getPageSize() * (page.getPageNum() - 1);
        page.setTotalPage(totalPage);
        page.setStartIndex(startIndex);
        //查询符合搜索关键字的用户
        List<User> users = userMapper.fuzzyFindByField(page);
        //设置当前页需要显示的记录
        page.setRecords(users);
        //设置当前页的实际记录数
        page.setRecordNum(users.size());
        return page;
    }

}
