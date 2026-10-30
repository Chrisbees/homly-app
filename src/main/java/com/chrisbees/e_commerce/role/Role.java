package com.chrisbees.e_commerce.role;

import java.util.List;

import com.chrisbees.e_commerce.common.BaseEntity;
import com.chrisbees.e_commerce.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@SuperBuilder  
@Table(name = "ROLES")
public class Role extends BaseEntity{

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
