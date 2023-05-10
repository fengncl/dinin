package com.dinin.core.db.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Schema(description = "评论")
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    @Schema(description = "id")
    private int id;
    @Basic
    @Column(name = "c_id", nullable = false)
    @Schema(description = "餐厅id")
    private int cId;
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
}
