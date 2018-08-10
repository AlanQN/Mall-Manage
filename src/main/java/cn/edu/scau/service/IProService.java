package cn.edu.scau.service;

import cn.edu.scau.entity.Pro;
import cn.edu.scau.component.Info;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.List;

@Component
public interface IProService {
    Info<List<Pro>> selectByTag( Map<String ,Integer> request);
    Info<Pro> insertBySome (Pro record);
}
