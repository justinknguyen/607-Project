package course.ensf607.assignment6.course;

import course.ensf607.assignment6.student.Student;
import course.ensf607.assignment6.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/course")
@CrossOrigin
public class CourseController {

    private final CourseService courseService;

    private final StudentService studentService;

    @Autowired
    public CourseController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping
    public void registerNewCourse(@RequestBody Course course) {
        courseService.addNewCourse(course);
    }

    @PutMapping("{courseId}/students/{studentId}")
    public Course enrollStudentToCourse(@PathVariable Long courseId,
                                         @PathVariable Long studentId) {
        Course course = courseService.getCourseById(courseId);
        Student student = studentService.getStudentById(studentId);
        course.enrolledStudents(student);
        courseService.updateCourse(course);
        return course;
    }

    @DeleteMapping(path = "{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId){
        courseService.deleteCourse(courseId);
    }

    @GetMapping(path = "{courseName}")
    public Optional<Course> searchCourse(@PathVariable("courseName") String courseName){
        return courseService.searchCourse(courseName);
    }

    @PutMapping(path = "{courseId}")
    public void updateCourseName(@PathVariable("courseId") Long courseId,
                                 String courseName){
        courseService.updateCourseName(courseId, courseName);
    }
}
