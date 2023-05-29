package com.ttttt.core.db.entity;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Schema(description = "评论")
@Table(name = "comment")
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    @Schema(description = "id")
    private int id;
    @Basic
    @Column(name = "c_id", nullable = false)
    @Schema(description = "餐厅id")
    private Integer cId;
    @Basic
    @Column(name = "u_id", nullable = true)
    @Schema(description = "用户id")
    private Integer uId;
    @Basic
    @Column(name = "content", nullable = true, length = 300)
    @Schema(description = "评论内容")
    private String content;
    @Basic
    @Column(name = "grade", nullable = true, precision = 0)
    @Schema(description = "评分")
    private Double grade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", cId=" + cId +
                ", uId=" + uId +
                ", content='" + content + '\'' +
                ", grade=" + grade +
                '}';
    }
}
