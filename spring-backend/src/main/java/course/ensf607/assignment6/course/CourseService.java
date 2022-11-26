package course.ensf607.assignment6.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addNewCourse(Course course) {
        Optional<Course> courseByName = courseRepository.findByName(course.getName());
        if (courseByName.isPresent()) {
            throw new IllegalStateException("Course already exist!");
        }
        courseRepository.save(course);
    }

    public void updateCourse(Course course) {
        courseRepository.save(course);
    }

    public Course getCourseById(Long courseId) {
        Optional<Course> courseById = courseRepository.findById(courseId);
        if (!courseById.isPresent()) {
            throw new IllegalStateException("Course does'nt exist!");
        }
        return courseById.get();
    }

    public void deleteCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)){
            throw new IllegalStateException(
                    "course id " + courseId + "does not exist"
            );
        }
        courseRepository.deleteById(courseId);
    }

    public Optional<Course> searchCourse(String courseName) {
        Optional<Course>courseByCourseName = courseRepository.findByName(courseName);
        return courseByCourseName;
    }
    @Transactional
    public void updateCourseName(Long courseId, String courseName) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException(
                        "Course id " + courseId + "does not exist"));
        if (courseName.equals(course.getName())){
            throw new IllegalArgumentException("Changing to same course name");
        }
        if (courseName != null && courseName.length() > 0){
            course.setName(courseName);
        }
    }
}
