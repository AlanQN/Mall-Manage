package cn.edu.scau.dao;

import cn.edu.scau.entity.Pro;

import java.util.List;
import cn.edu.scau.entity.ProCat;
import org.springframework.stereotype.Component;

@Component
public interface ProCatMapper {
    List<ProCat> selectByTag(Integer tag);
    int insertBySome(ProCat record);
}
