package com.example.projectonecourseapp.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = AppDatabase.COURSE_TABLE)
public class Course
{

    @PrimaryKey(autoGenerate = true)
    private int mCourseId;

    private String mInstructor;
    private String mTitle;
    private String mDescription;
    private String mStartDate;
    private String mEndDate;

    // display courses taken by this username at the landing page
    @NonNull
    private String username;

    public Course() {

    }

    public Course(String course_name, String course_id, String instructor, String start_date, String end_date, String username) {
        this.mDescription = course_name;
        this.mTitle = course_id;
        this.mInstructor = instructor;
        this.mStartDate = start_date;
        this.mEndDate = end_date;
        this.username = username;
    }

    public int getCourseId() {
        return mCourseId;
    }

    public void setCourseId(int courseId) {
        mCourseId = courseId;
    }

    public String getInstructor() {
        return mInstructor;
    }

    public void setInstructor(String instructor) {
        mInstructor = instructor;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Course Info {" +
                "mInstructor='" + mInstructor + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mStartDate='" + mStartDate + '\'' +
                ", mEndDate='" + mEndDate + '\'' +
                '}';
    }
}
