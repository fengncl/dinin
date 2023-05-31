package com.ttttt.core.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Data
@Schema(description = "User")
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    @Schema(description = "User ID")
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 50)
    @Schema(description = "Name")
    private String name;
    @Basic
    @Column(name = "email", nullable = true, length = 255)
    @Schema(description = "Email")
    private String email;
    @Basic
    @Column(name = "phone", nullable = true, length = 11)
    @Schema(description = "Phone number")
    private String phone;
}
