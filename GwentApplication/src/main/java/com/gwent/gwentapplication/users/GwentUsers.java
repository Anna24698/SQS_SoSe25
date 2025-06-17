package com.gwent.gwentapplication.users;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

//Flyway Compose DB-name
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class GwentUsers {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "username", nullable = false, length = 200)
    private String username;


    @Column(name = "password", nullable = false, length = 60)
    private String password;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "userid", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "id"))
    private List<GwentRoles> roles = new ArrayList<>();


}
