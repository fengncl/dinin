package com.ttttt.core.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(description = "餐厅")
@Entity
@Data
@Table(name = "canteen")
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
