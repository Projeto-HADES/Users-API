package br.com.users.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "adm_id")
    private AdmEntity admId;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "email", length = 255, nullable = false, unique = true) @Email
    private String email;

    @Column(name = "cpf", length = 255, nullable = false, unique = true)
    private String cpf;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "phone", length = 255, nullable = false)
    private String phone;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "street", length = 255, nullable = false)
    private String street;

    @Column(name = "neighborhood", length = 255, nullable = false)
    private String neighborhood;

    @Column(name = "complement", length = 255, nullable = false)
    private String complement;

    @Column(name = "number", length = 255, nullable = false)
    private String number;

    @Column(name = "role", length = 255, nullable = false)
    private String role;
}
