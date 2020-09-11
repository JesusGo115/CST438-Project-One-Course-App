package com.example.projectonecourseapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDatabase.ASSIGNMENT_TABLE)
public class Assignment
{

    @PrimaryKey
    private int mAssignmentId;

    private String mDetails;
    private String mMaxScore;
    private String mEarnedScore;
    private String mAssignedDate;
    private String mDueDate;
    private int mCategoryId;
    private String mCourseId;

    public Assignment(String details, String maxScore, String earnedScore, String assignedDate, String dueDate) {
        this.mDetails = details;
        this.mMaxScore = maxScore;
        this.mEarnedScore = earnedScore;
        this.mAssignedDate = assignedDate;
        this.mDueDate = dueDate;
    }

    public int getAssignmentId() {
        return mAssignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        mAssignmentId = assignmentId;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String details) {
        mDetails = details;
    }

    public String getMaxScore() {
        return mMaxScore;
    }

    public void setMaxScore(String maxScore) {
        mMaxScore = maxScore;
    }

    public String getEarnedScore() {
        return mEarnedScore;
    }

    public void setEarnedScore(String earnedScore) {
        mEarnedScore = earnedScore;
    }

    public String getAssignedDate() {
        return mAssignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        mAssignedDate = assignedDate;
    }

    public String getDueDate() {
        return mDueDate;
    }

    public void setDueDate(String dueDate) {
        mDueDate = dueDate;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(int categoryId) {
        mCategoryId = categoryId;
    }

    public String getCourseId() {
        return mCourseId;
    }

    public void setCourseId(String courseId) {
        mCourseId = courseId;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "mDetails='" + mDetails + '\'' +
                ", mMaxScore='" + mMaxScore + '\'' +
                ", mEarnedScore='" + mEarnedScore + '\'' +
                ", mAssignedDate='" + mAssignedDate + '\'' +
                ", mDueDate='" + mDueDate + '\'' +
                ", mCategoryId='" + mCategoryId + '\'' +
                ", mCourseId='" + mCourseId + '\'' +
                '}';
    }
}
