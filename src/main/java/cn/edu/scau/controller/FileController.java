package cn.edu.scau.controller;

import cn.edu.scau.file.FilePathConfig;
import cn.edu.scau.service.IFileUploadService;
import cn.edu.scau.component.Icon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    IFileUploadService fileUploadService;

    /**
     * 上传头像
     * @param icon
     * @return
     */
    @RequestMapping("/upload/user/headicon")
    @ResponseBody
    public Map<String, Object> uploadHeadicon(@RequestBody Icon icon, HttpServletRequest request) {
        //获取实际路径
        String realPath = request.getSession().getServletContext().getRealPath(FilePathConfig.USER_ICON_BASE_PATH);
        return fileUploadService.saveIcon(icon, realPath);
    }

}
