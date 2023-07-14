package com.ass.system.model.submission;

import java.util.Date;

public class Submission {
    private long submissionId;
    private long assignmentId;
    private long authorId;

    private Date submissionDate;

    private String submissionContent;

    public Submission() {
    }

    public long getSubmissionId() {
        return this.submissionId;
    }

    public long getAssignmentId() {
        return this.assignmentId;
    }

    public long getAuthorId() {
        return this.authorId;
    }

    public Date getSubmissionDate() {
        return this.submissionDate;
    }

    public String getSubmissionContent() {
        return this.submissionContent;
    }

    public void setSubmissionId(long submissionId) {
        this.submissionId = submissionId;
    }

    public void setAssignmentId(long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public void setSubmissionContent(String submissionContent) {
        this.submissionContent = submissionContent;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Submission)) return false;
        final Submission other = (Submission) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getSubmissionId() != other.getSubmissionId()) return false;
        if (this.getAssignmentId() != other.getAssignmentId()) return false;
        if (this.getAuthorId() != other.getAuthorId()) return false;
        final Object this$submissionDate = this.getSubmissionDate();
        final Object other$submissionDate = other.getSubmissionDate();
        if (this$submissionDate == null ? other$submissionDate != null : !this$submissionDate.equals(other$submissionDate))
            return false;
        final Object this$submissionContent = this.getSubmissionContent();
        final Object other$submissionContent = other.getSubmissionContent();
        if (this$submissionContent == null ? other$submissionContent != null : !this$submissionContent.equals(other$submissionContent))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Submission;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $submissionId = this.getSubmissionId();
        result = result * PRIME + (int) ($submissionId >>> 32 ^ $submissionId);
        final long $assignmentId = this.getAssignmentId();
        result = result * PRIME + (int) ($assignmentId >>> 32 ^ $assignmentId);
        final long $authorId = this.getAuthorId();
        result = result * PRIME + (int) ($authorId >>> 32 ^ $authorId);
        final Object $submissionDate = this.getSubmissionDate();
        result = result * PRIME + ($submissionDate == null ? 43 : $submissionDate.hashCode());
        final Object $submissionContent = this.getSubmissionContent();
        result = result * PRIME + ($submissionContent == null ? 43 : $submissionContent.hashCode());
        return result;
    }

    public String toString() {
        return "Submission(submissionId=" + this.getSubmissionId() + ", assignmentId=" + this.getAssignmentId() + ", authorId=" + this.getAuthorId() + ", submissionDate=" + this.getSubmissionDate() + ", submissionContent=" + this.getSubmissionContent() + ")";
    }
}
