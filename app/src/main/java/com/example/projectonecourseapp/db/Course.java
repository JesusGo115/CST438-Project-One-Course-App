package com.example.projectonecourseapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    public Course() {

    }

    public Course(String course_name) {
        this.mTitle = course_name;
    }

    public Course(String instructor, String title, String description, String startDate, String endDate) {
        this.mInstructor = instructor;
        this.mTitle = title;
        this.mDescription = description;
        this.mStartDate = startDate;
        this.mEndDate = endDate;
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
