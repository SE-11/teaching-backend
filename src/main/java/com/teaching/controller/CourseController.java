package com.teaching.controller;

import com.teaching.pojo.Announce;
import com.teaching.pojo.Course;
import com.teaching.service.CourseService;
import com.teaching.vo.CourseTeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Value("${tc.upload-path}")
    private String uploadPath;

    @GetMapping("/course/{id}")
    public CourseTeacherVO getCourseById(@PathVariable("id") Integer id) {
        CourseTeacherVO courseTeacherVO = courseService.getCourseInfoByCourseId(id);
        return courseTeacherVO;
    }

    @PostMapping("/course/addAnnounce")
    public Announce saveAnnounce(@RequestBody Announce announce) {
        int annId = courseService.saveAnnounce(announce);
        return courseService.getAnnById(annId);
    }

    @GetMapping("/course/{id}/listAnn")
    public List<Announce> listAnnByCourseId(@PathVariable("id") Integer id) {
        return courseService.listAnnByCourseId(id);
    }

    @PostMapping("/course/courseware/upload")
    public Map<String, Object> uploadCourseware(HttpServletRequest req, MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd");
        int courseId = Integer.parseInt(req.getParameter("courseId"));

        // 创建文件夹
//        String format = simpleDateFormat.format(new Date());
        String folderPath = uploadPath + "courseres/" + courseId + "/courseware";
        File folder = new File(folderPath);
        if(!folder.exists()) {
            folder.mkdirs();
        }

        try {
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/courseres/" + courseId + "/courseware/" +file.getOriginalFilename();
            file.transferTo(new File(folder, file.getOriginalFilename()));
            map.put("status", "成功");
            map.put("url", url);
            System.out.println(map.toString());
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status", "失败");
        }
        return map;
    }
}
