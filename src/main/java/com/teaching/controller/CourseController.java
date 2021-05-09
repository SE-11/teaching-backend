package com.teaching.controller;

import com.teaching.pojo.Announce;
import com.teaching.pojo.Courseware;
import com.teaching.pojo.Homework;
import com.teaching.service.CourseService;
import com.teaching.service.CoursewareService;
import com.teaching.service.HomeworkService;
import com.teaching.vo.CourseTeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CoursewareService coursewareService;

    @Autowired
    private HomeworkService homeworkService;

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

            Courseware retCourseware = new Courseware();
            retCourseware.setCourseId(courseId);
            retCourseware.setPath(url);
            retCourseware.setOldFilename(file.getOriginalFilename());
            retCourseware.setNewFilename(file.getOriginalFilename());
            retCourseware.setId(coursewareService.save(retCourseware));

            map.put("status", "成功");
            map.put("fileInfo", retCourseware);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status", "失败");
        }
        return map;
    }

    @GetMapping("/course/courseware/listCourseware/{courseId}")
    public List<Courseware> listCourseware(@PathVariable("courseId") Integer courseId) {
        return coursewareService.listCoursewareByCourseId(courseId);
    }

    @PostMapping("/course/homework/upload")
    public Map<String, Object> uploadHomework(MultipartHttpServletRequest req) {
        Map<String, Object> retMap = new HashMap<>();

        MultipartFile file = req.getFile("file");
        String title = req.getParameter("title");
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        String type = req.getParameter("type");
        String oldFilename = file.getOriginalFilename();
        String newFilename = oldFilename;
        int courseId = Integer.parseInt(req.getParameter("courseId"));

        String folderPath = uploadPath + "courseres/" + courseId + "/homework";
        File folder = new File(folderPath);
        if(!folder.exists()) {
            folder.mkdirs();
        }

        try{
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/courseres/" + courseId + "/homework/" + file.getOriginalFilename();
            file.transferTo(new File(folder, file.getOriginalFilename()));
            Homework homework = new Homework(courseId, title, startTime, endTime, type, oldFilename, newFilename, url);
            homework.setId(homeworkService.save(homework));
            retMap.put("status", "成功");
            retMap.put("homeworkInfo", homework);
        } catch (IOException e) {
            e.printStackTrace();
            retMap.put("status", "失败");
        }

        return retMap;
    }

    @GetMapping("/course/homework/listHomework/{courseId}")
    public List<Homework> listHomework(@PathVariable("courseId") Integer courseId) {
        return homeworkService.listHomeworkByCourseId(courseId);
    }

}
