package cn.edu.scau.dao;

import cn.edu.scau.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * UserDao接口
 */
public interface UserMapper {

    /**
     * 添加用户
     * @param user 用户
     * @return int 影响行数
     */
    public int insert(User user);

    /**
     * 更新用户信息
     * @param user 用户
     * @return int 影响行数
     */
    public int update(User user);

    /**
     * 删除单个用户
     * @param id 用户编号
     * @return int 删除个数
     */
    public int deleteOne(Integer id);

    /**
     * 批量删除用户
     * @param idList 用户编号
     * @return int 删除个数
     */
    public int deleteMore(List<Integer> idList);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    public User find(Integer id);

    /**
     * 根据名称模糊查找用户
     * @param username 用户名称
     * @return List<User> 符合条件的用户
     */
    public List<User> fuzzyFindByName(String username);

    /**
     * 查找创建时间在preDate和endDate之间的用户
     * @param preDate 开始时间
     * @param endDate 结束时间
     * @return List<User> 符合条件的用户
     */
    public List<User> fuzzyFindByTime(Date preDate, Date endDate);

    /**
     * 根据手机号模糊查找用户
     * @param phone 手机号
     * @return List<User> 符合条件的用户
     */
    public List<User> fuzzyFindByPhone(String phone);

    /**
     * 根据id模糊查找用户
     * @param id
     * @return
     */
    public List<User> fuzzyFindById(String id);

    /**
     * 根据邮箱查找用户
     * @param emial 邮箱
     * @return
     */
    public User findByEmail(String emial);

    /**
     * 根据手机号查找用户
     * @param phone 邮箱
     * @return
     */
    public User findByPhone(String phone);

    /**
     * 根据名称查找用户
     * @param name 邮箱
     * @return
     */
    public User findByName(String name);

    /**
     * 分页查找用户
     * @param page 当前页号
     * @param rows 一页包含的记录数
     * @return List<User> 用户
     */
    public List<User> findByPage(Integer page, Integer rows);

}
