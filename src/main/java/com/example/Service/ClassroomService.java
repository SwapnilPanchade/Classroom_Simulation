package com.example.Service;

import com.example.DAO.ClassroomRepo;
import com.example.Entity.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    private final ClassroomRepo classroomRepo;

    @Autowired
    public ClassroomService(ClassroomRepo classroomRepo) {
        this.classroomRepo = classroomRepo;
    }

    public List<Classroom> findAll() {
        return classroomRepo.findAll();
    }

    public String addClassRoom(Classroom classroom) {
        classroomRepo.save(classroom);
        return "Class room added Successfully";
    }

    public String addMultipleClassRoom(List<Classroom> classrooms) {
        classroomRepo.saveAll(classrooms);
        return "Classrooms successfully added";
    }

    public Classroom getClassById(long id) {
        return classroomRepo.findById(id).get();
    }
}
