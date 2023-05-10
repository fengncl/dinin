package com.dinin.core.db.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Schema(description = "用户")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    @Schema(description = "用户id")
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 50)
    @Schema(description = "姓名")
    private String name;
    @Basic
    @Column(name = "email", nullable = true, length = 255)
    @Schema(description = "邮箱")
    private String email;
    @Basic
    @Column(name = "phone", nullable = true, length = 11)
    @Schema(description = "手机号")
    private String phone;
}
