package cn.edu.scau.service;

import cn.edu.scau.component.Icon;

import java.util.Map;

public interface IFileUploadService {

    /**
     * 上传头像
     * @param icon
     * @param realPath
     * @return
     */
    public Map<String, Object> saveIcon(Icon icon, String realPath);

}
