package com.example.Service;

import com.example.DAO.ClassroomRepo;
import com.example.DAO.StudentRepo;
import com.example.DAO.TeacherRepo;
import com.example.Entity.Classroom;
import com.example.Entity.Student;
import com.example.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClassroomRepo classroomRepo;
    @Autowired
    private TeacherRepo teacherRepo;

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo){
        this.studentRepo = studentRepo;
    }

    public List<Student> allStudents() {
        List<Student> students = studentRepo.findAll();
        if(students == null || students.isEmpty()){
            throw new IllegalArgumentException("Students not found");
        }
        return students;
    }

    public Student getStudentById(long id){
        return studentRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student with id not find"));
    }

    @Transactional
    public Student addStudent(Student student){
//        classroomService.findAll();
//        teacherService.getAllTeachers();

        Classroom classroom = classroomRepo.findById(student.getClassroom().getId())
                .orElseThrow(() -> new IllegalArgumentException("Classroom not found with id: " + student.getClassroom().getId()));
        student.setClassroom(classroom);

        // Validate Teachers
        List<Teacher> teachers = new ArrayList<>();
        for (Teacher teacher : student.getTeachers()) {
            Teacher existingTeacher = teacherRepo.findById(teacher.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Teacher not found with id: " + teacher.getId()));
            teachers.add(existingTeacher);
        }
        student.setTeachers(teachers);

     return  studentRepo.save(student);
//        return "The student is added successfully";
    }

    @Transactional
    public void updateStudent(Student student){
        Student student1 = studentRepo.findById(student.getId()).orElseThrow(() -> new IllegalArgumentException("The student does not exist with this ID provide proper id"));
        student1.setClassroom(student.getClassroom());
        student1.setName(student.getName());
        student1.setTeachers(student.getTeachers());
        studentRepo.save(student1);
    }

}
