package com.example.Service;

import com.example.DAO.TeacherRepo;
import com.example.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

//    private final TeacherRepo teacherRepo;
//
//    @Autowired
//    public TeacherService(TeacherRepo teacherRepo){
//        this.teacherRepo = teacherRepo;
//    }
//

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers =  teacherRepo.findAll();
        if(teachers == null || teachers.isEmpty()){
            throw new IllegalArgumentException("teachers not found");
        }
        return teachers;
    }

    public Teacher getTeacherById(long id) {
        return teacherRepo.findById(id).get();
    }

    @Transactional
    public void addTeacher(Teacher teacher) {
        teacherRepo.save(teacher);
    }

    @Transactional
    public void addMultiple(List<Teacher> teachers) {
        teacherRepo.saveAll(teachers);
    }

    @Transactional
    public void updateTeacher(Teacher teacher) {
        Teacher t1 = teacherRepo.findById(teacher.getId()).orElseThrow(() -> new IllegalArgumentException("Teacher with this id not found"));
        t1.setName(teacher.getName());
        t1.setClassrooms(teacher.getClassrooms());
        t1.setStudents(teacher.getStudents());
    }

    @Transactional
    public void deleteTeacher(long id){
        teacherRepo.deleteById(id);
    }
}
