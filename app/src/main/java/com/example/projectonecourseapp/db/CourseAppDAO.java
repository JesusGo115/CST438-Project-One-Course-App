package com.example.projectonecourseapp.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CourseAppDAO
{
    //User
    @Insert
    void addUser(User...users);

    @Query("select * from " + AppDatabase.USER_TABLE + " where mUserName = :username and mPassword = :password")
    User login(String username, String password);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT COUNT(*) FROM " + AppDatabase.USER_TABLE + " WHERE mUserName = :username")
    int countUser(String username);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserName = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserName = :username AND mPassword = :password")
    User getUserByUserAndPass(String username, String password);

    //********************************************

    //Course
    @Insert
    void addCourse(Course...courses);

    @Update
    void updateCourse(Course courses);

    @Delete
    void deleteCourse(Course course);

    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE)
    List<Course> getAllCourses();

    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE + " WHERE mTitle = :title")
    List<Course> getAllCoursesByTitle(String title);

    @Query("select * from " + AppDatabase.COURSE_TABLE + " where mTitle = :title")
    Course getCourseByTitle(String title);

    @Query("SELECT * FROM " + AppDatabase.COURSE_TABLE + " WHERE mCourseId = :courseId")
    List<Course> getCourseByCourseId(int courseId);

    @Query("select * from " + AppDatabase.COURSE_TABLE + " where username = :username")
    List<Course> getCoursesTaken(String username);

    //********************************************

    //GradeCategory
    @Insert
    void addGradeCategory(GradeCategory...gradeCategories);

    @Update
    void updateGradeCategory(GradeCategory...gradeCategories);

    @Delete
    void deleteGradeCategory(GradeCategory gradeCategory);

    @Query("SELECT * FROM " + AppDatabase.GRADE_CATEGORY_TABLE)
    List<GradeCategory> getAllGradeCategories();

    @Query("SELECT * FROM " + AppDatabase.GRADE_CATEGORY_TABLE + " WHERE mTitle = :title")
    List<GradeCategory> getAllGradeCategoriesByTitle(String title);

    @Query("SELECT * FROM " + AppDatabase.GRADE_CATEGORY_TABLE + " WHERE mCategoryId = :categoryId")
    GradeCategory getGradeCategoryByCategoryId(int categoryId);

    //********************************************

    //Assignment
    @Insert
    void addAssignment(Assignment...assignments);

    @Update
    void updateAssignment(Assignment...assignments);

    @Delete
    void deleteAssignment(Assignment assignment);

    @Query("SELECT * FROM " + AppDatabase.ASSIGNMENT_TABLE)
    List<Assignment> getAllAssignments();

    @Query("SELECT * FROM " + AppDatabase.ASSIGNMENT_TABLE + " WHERE mAssignmentId = :assignmentId")
    List<Assignment> getAssignmentByAssignmentId(int assignmentId);

    @Query("SELECT * FROM " + AppDatabase.ASSIGNMENT_TABLE + " WHERE mCategoryId = :categoryId")
    Assignment getAssignmentByCategoryId(int categoryId);

    @Query("SELECT * FROM " + AppDatabase.ASSIGNMENT_TABLE + " WHERE mCourseId = :courseId")
    Assignment getAssignmentByCourseId(String courseId);

    //********************************************

    //Grade
    @Insert
    void addGrade(Grade...grades);

    @Update
    void updateGrade(Grade...grades);

    @Delete
    void deleteGrade(Grade grade);

    @Query("SELECT * FROM " + AppDatabase.GRADE_TABLE)
    List<Grade> getAllGrades();

    @Query("SELECT * FROM " + AppDatabase.GRADE_TABLE + " WHERE mGradeId = :gradeId")
    Grade getGradeByGradeId(int gradeId);

    @Query("SELECT * FROM " + AppDatabase.GRADE_TABLE + " WHERE mAssignmentId = :assignmentId")
    Grade getGradeByAssignmentId(int assignmentId);

    @Query("SELECT * FROM " + AppDatabase.GRADE_TABLE + " WHERE mCourseId = :courseId")
    Grade getGradeByCourseId(int courseId);

    @Query("SELECT * FROM " + AppDatabase.GRADE_TABLE + " WHERE mStudentId = :studentId")
    Grade getGradeByStudentId(int studentId);

    //********************************************

    //Enrollment
    @Insert
    void addEnrollment(Enrollment...enrollments);

    @Update
    void updateEnrollment(Enrollment...enrollments);

    @Delete
    void deleteEnrollment(Enrollment enrollment);

    @Query("SELECT * FROM " + AppDatabase.ENROLLMENT_TABLE)
    List<Enrollment> getAllEnrollments();

    @Query("SELECT * FROM " + AppDatabase.ENROLLMENT_TABLE + " WHERE mEnrollmentId = :enrollmentId")
    Enrollment getEnrollmentByEnrollmentId(int enrollmentId);

    @Query("SELECT * FROM " + AppDatabase.ENROLLMENT_TABLE + " WHERE mCourseId = :courseId")
    Enrollment getEnrollmentByCourseId(int courseId);

    @Query("SELECT * FROM " + AppDatabase.ENROLLMENT_TABLE + " WHERE mStudentId = :studentId")
    Enrollment getEnrollmentByStudentId(int studentId);
}
