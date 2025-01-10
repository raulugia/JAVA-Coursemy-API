package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.dto.CourseDTO;
import com.coursemy_backend.coursemy.dto.StudentDTO;
import com.coursemy_backend.coursemy.dto.TeacherDTO;
import com.coursemy_backend.coursemy.entities.Course;
import com.coursemy_backend.coursemy.entities.Student;
import com.coursemy_backend.coursemy.entities.Teacher;
import com.coursemy_backend.coursemy.exception.EntityNotFound;
import com.coursemy_backend.coursemy.repository.CourseRepository;
import com.coursemy_backend.coursemy.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImp implements StudentService{
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImp(StudentRepository studentRepository, PasswordEncoder passwordEncoder, CourseRepository courseRepository){
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> new StudentDTO(student.getId(), student.getFirstName(), student.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentDTO getById(long id) {
        Optional<Student> student = studentRepository.findById(id);

        if(student.isPresent()){
            Student dbStudent = student.get();
            return new StudentDTO(dbStudent.getId(), dbStudent.getFirstName(), dbStudent.getLastName());
        }
        throw  new EntityNotFound("Student not found");
    }

    @Override
    @Transactional
    public List<CourseDTO> getStudentCourses(long id){
        Optional<Student> existingStudent = studentRepository.findById(id);

        if(existingStudent.isPresent()){
            Student dbStudent = existingStudent.get();

            return dbStudent.getCourses().stream()
                    .map(course -> new CourseDTO(course.getName(), course.getDescription(), course.getImageUrl(), new TeacherDTO(course.getTeacher().getId(), course.getTeacher().getFirstName(), course.getTeacher().getLastName())))
                    .collect(Collectors.toList());
        }

        throw new EntityNotFound("Student not found");
    }

    @Transactional
    @Override
    public Student createStudent(Student student) {
        if(!validatePassword(student.getPassword())){
            throw new IllegalArgumentException("Password must be 6-20 characters long, contain at least one uppercase letter, and at least one lowercase letter.");
        }

        String password = passwordEncoder.encode(student.getPassword());
        student.setPassword(password);

        return studentRepository.save(student);
    }

    public boolean validatePassword(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z]).{6,20}$";
        return password != null && password.matches(regex);
    }

    @Transactional
    @Override
    public String enrollCourse(long studentId, long courseId){
        Optional<Student> existingStudent = studentRepository.findById(studentId);
        if(!existingStudent.isPresent()){
            throw new EntityNotFound("Student not found");
        }

        Optional<Course> existingCourse = courseRepository.findById(courseId);
        if(!existingCourse.isPresent()){
            throw new EntityNotFound("Course not found");
        }

        Student dbStudent = existingStudent.get();
        Course dbCourse = existingCourse.get();

        if(!dbCourse.getStudents().contains(dbStudent)){
            dbCourse.getStudents().add(dbStudent);
            dbStudent.getCourses().add(dbCourse);
            courseRepository.save(dbCourse);
            studentRepository.save(dbStudent);

            return "Enrolled successfully";
        }

        return "Enrollment failed";
    }

    @Transactional
    @Override
    public Student updateStudent(long id, Student student) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        System.out.println("Student " + existingStudent.toString());
        if(existingStudent.isPresent()){
            Student dbStudent = existingStudent.get();
            if(student.getFirstName() != null){
                dbStudent.setFirstName(student.getFirstName());
            }
            if(student.getLastName() != null){
                dbStudent.setLastName(student.getLastName());
            }
            if(student.getEmail() != null){
                dbStudent.setEmail(student.getEmail());
            }

            return studentRepository.save(dbStudent);
        }
        return null;
    }

    @Override
    @Transactional
    public String removeById(long id) {
        Optional<Student> existingStudent = studentRepository.findById(id);

        if(existingStudent.isPresent()){
            studentRepository.deleteById(id);
            return "Student deleted successfully";
        }
        return "Error";
    }

    @Override
    @Transactional
    public String removeEnrollment(long courseId, long studentId){
        Optional<Student> existingStudent = studentRepository.findById(studentId);

        if(existingStudent.isPresent()){
            Student dbStudent = existingStudent.get();
            Optional<Course> existingCourse = courseRepository.findById(courseId);

            if(existingCourse.isPresent()){
                Course dbCourse = existingCourse.get();
                if(dbCourse.getStudents().contains(dbStudent)){
                    dbCourse.getStudents().remove(dbStudent);
                    dbStudent.getCourses().remove(dbCourse);

                    courseRepository.save(dbCourse);
                    studentRepository.save(dbStudent);

                    return "Disenrolled Successfully";
                }
            }
            throw new EntityNotFound("Course not found");
        }
        throw new EntityNotFound("Student not found");
    }
}
