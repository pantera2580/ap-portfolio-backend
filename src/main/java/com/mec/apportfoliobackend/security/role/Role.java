package com.mec.apportfoliobackend.security.role;

import com.mec.apportfoliobackend.security.user.User;
import com.mec.apportfoliobackend.security.util.RoleName;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "name", nullable = false)
    private RoleName name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public Role() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
