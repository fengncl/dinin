package com.dinin.core.db.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;


@Schema(description = "餐厅")
@Entity
@Data
public class Canteen {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = 255)
    @Schema(description = "餐厅id")
    private Integer id;
    @Basic
    @Column(name = "name", nullable = true, length = 255)
    @Schema(description = "餐厅名")
    private String name;
    @Basic
    @Column(name = "phone", nullable = true, length = 255)
    @Schema(description = "手机号")
    private String phone;
    @Basic
    @Column(name = "postal_code", nullable = true, length = 10)
    @Schema(description = "邮编")
    private String postalCode;
    @Basic
    @Column(name = "grade", nullable = true, precision = 0)
    @Schema(description = "评分")
    private Double grade;
}
