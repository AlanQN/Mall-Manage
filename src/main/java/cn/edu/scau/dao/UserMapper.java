package cn.edu.scau.dao;

import cn.edu.scau.component.Page;
import cn.edu.scau.entity.User;

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
     * 移除单个用户
     * @param id
     * @return
     */
    public int removeOne(Integer id);

    /**
     * 批量移除用户
     * @param ids
     * @return
     */
    public int removeMore(Integer[] ids);

    /**
     * 删除单个用户
     * @param id 用户编号
     * @return int 删除个数
     */
    public int deleteOne(Integer id);

    /**
     * 批量删除用户
     * @param ids 用户编号
     * @return int 删除个数
     */
    public int deleteMore(Integer[] ids);

    /**
     * 从删除列表中还原单个用户
     * @param id 用户编号
     * @return int 还原个数
     */
    public int restoreOne(Integer id);

    /**
     * 从删除列表中批量还原用户
     * @param ids 用户编号
     * @return int 还原个数
     */
    public int restoreMore(Integer[] ids);

    /**
     * 禁用用户
     * @param id
     * @return
     */
    public int pause(Integer id);

    /**
     * 启用用户
     * @param id
     * @return
     */
    public int resume(Integer id);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    public User findById(Integer id);

    /**
     * 根据指定字段模糊查找用户
     * @param page
     * @return
     */
    public List<User> fuzzyFindByField(Page page);

    /**
     * 获取搜索记录总数
     * @param page
     * @return
     */
    public int getSearchNum(Page<User> page);

    /**
     * 根据唯一的字段查找用户（邮箱、手机号和名称）
     * @param user
     * @return
     */
    public User findByUniqueField(User user);

    /**
     * 分页查找用户
     * @param page
     * @return List<User> 用户
     */
    public List<User> findNormalByPage(Page page);

    /**
     * 获取用户总数
     * @return
     */
    public int getNormalUserNum();

    /**
     * 分页查找已移除用户
     * @param page
     * @return List<User> 用户
     */
    public List<User> findRemoveByPage(Page page);

    /**
     * 获取已移除用户总数
     * @return
     */
    public int getRemoveUserNum();

}
