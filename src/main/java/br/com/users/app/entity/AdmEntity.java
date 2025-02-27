package br.com.users.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "adms")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AdmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "email", length = 255, nullable = false) @Email
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "role", length = 255, nullable = false)
    private String role;

    public AdmEntity(UUID admId) {}
}
