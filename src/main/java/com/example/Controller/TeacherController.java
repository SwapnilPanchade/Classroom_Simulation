package com.example.Controller;

import com.example.Entity.Teacher;
import com.example.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.context.event.EventPublicationInterceptor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getAllTeachers(){
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable long id){
        return teacherService.getTeacherById(id);
    }

    @PostMapping
    public String addTeacher(@RequestBody Teacher teacher){
        teacherService.addTeacher(teacher);
        return "Teacher is added successfully";
    }

    @PostMapping("/multiple")
    public String addMultiple(@RequestBody List<Teacher> teachers){
        teacherService.addMultiple(teachers);
        return "Teachers are added successfully";
    }

    @PutMapping
    public String updateTeacher(@RequestBody Teacher teacher){
        teacherService.updateTeacher(teacher);
        return "teacher is updated succesfully";
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable long id){
        teacherService.deleteTeacher(id);
        return "teacher is deleted successfully";
    }
}
