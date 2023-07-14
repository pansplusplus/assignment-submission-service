package com.ass.system.model.assignment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Assignment {
    @Id
    @GeneratedValue
    private int assignmentId;
    private long courseId;

    private String assignmentName;

    private int assignmentCredits;
    //capitalisation MATTERS!
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    public Assignment() {
    }

    public int getAssignmentId() {
        return this.assignmentId;
    }

    public long getCourseId() {
        return this.courseId;
    }

    public String getAssignmentName() {
        return this.assignmentName;
    }

    public int getAssignmentCredits() {
        return this.assignmentCredits;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public void setAssignmentCredits(int assignmentCredits) {
        this.assignmentCredits = assignmentCredits;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Assignment)) return false;
        final Assignment other = (Assignment) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getAssignmentId() != other.getAssignmentId()) return false;
        if (this.getCourseId() != other.getCourseId()) return false;
        final Object this$assignmentName = this.getAssignmentName();
        final Object other$assignmentName = other.getAssignmentName();
        if (this$assignmentName == null ? other$assignmentName != null : !this$assignmentName.equals(other$assignmentName))
            return false;
        if (this.getAssignmentCredits() != other.getAssignmentCredits()) return false;
        final Object this$dueDate = this.getDueDate();
        final Object other$dueDate = other.getDueDate();
        if (this$dueDate == null ? other$dueDate != null : !this$dueDate.equals(other$dueDate)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Assignment;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getAssignmentId();
        final long $courseId = this.getCourseId();
        result = result * PRIME + (int) ($courseId >>> 32 ^ $courseId);
        final Object $assignmentName = this.getAssignmentName();
        result = result * PRIME + ($assignmentName == null ? 43 : $assignmentName.hashCode());
        result = result * PRIME + this.getAssignmentCredits();
        final Object $dueDate = this.getDueDate();
        result = result * PRIME + ($dueDate == null ? 43 : $dueDate.hashCode());
        return result;
    }

    public String toString() {
        return "Assignment(assignmentId=" + this.getAssignmentId() + ", courseId=" + this.getCourseId() + ", assignmentName=" + this.getAssignmentName() + ", assignmentCredits=" + this.getAssignmentCredits() + ", dueDate=" + this.getDueDate() + ")";
    }
}
