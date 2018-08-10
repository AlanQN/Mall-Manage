package cn.edu.scau.dao;

import org.springframework.stereotype.Component;
import cn.edu.scau.entity.Pro;

import java.util.List;

@Component
public interface ProMapper {
    List<Pro> selectByTag(Integer tag);
    int insertBySome(Pro record);
}
