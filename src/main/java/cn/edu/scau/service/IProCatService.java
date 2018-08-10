package cn.edu.scau.service;

import cn.edu.scau.component.Info;
import cn.edu.scau.entity.ProCat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IProCatService {
    Info<List<ProCat>> selectByTag(Map<String ,Integer> request);
    Info<ProCat> insertBySome (ProCat record);
}
