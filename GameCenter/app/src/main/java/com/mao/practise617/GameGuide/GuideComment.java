package com.mao.practise617.GameGuide;

public class GuideComment {
    private int commentId;
    private int guideId;
    private String commenterName;
    private String commentContent;

    // 无参数构造函数
    public GuideComment() {
    }

    // 带参数的构造函数
    public GuideComment(int commentId, int guideId, String commenterName, String commentContent) {
        this.commentId = commentId;
        this.guideId = guideId;
        this.commenterName = commenterName;
        this.commentContent = commentContent;
    }

    // getter 和 setter 方法
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getGuideId() {
        return guideId;
    }

    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Override
    public String toString() {
        return "GuideComment{" +
                "commentId=" + commentId +
                ", guideId=" + guideId +
                ", commenterName='" + commenterName + '\'' +
                ", commentContent='" + commentContent + '\'' +
                '}';
    }
}