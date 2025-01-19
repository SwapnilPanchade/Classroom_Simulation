package com.example.Controller;

import com.example.Entity.Classroom;
import com.example.Service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {

//    @Autowired
//    private Classroom classroom;
//
    private final ClassroomService classroomService;
    public ClassroomController(ClassroomService classroomService){
        this.classroomService = classroomService;
    }

    @GetMapping
    public List<Classroom> getAllClassroom(){
        return classroomService.findAll();
    }
    @GetMapping("/{id}")
    public Classroom getClassById(@PathVariable long id){
        return classroomService.getClassById(id);
    }

    @PostMapping
    public String addClassroom(@RequestBody Classroom classroom){
        return classroomService.addClassRoom(classroom);
    }

    @PostMapping
    public String addMultipleClassrooms(@RequestBody List<Classroom> classrooms){
        return classroomService.addMultipleClassRoom(classrooms);
    }


}
