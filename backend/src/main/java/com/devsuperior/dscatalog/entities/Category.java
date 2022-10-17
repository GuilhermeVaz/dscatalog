package com.devsuperior.dscatalog.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updateAt;

    public Set<Product> getProducts() {
        return products;
    }

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    @PrePersist
    public void prePersist(){
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        updateAt = Instant.now();
    }


    //serve para comparar objetos pelo id
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Category)) {
            return false;
        } else {
            Category category = (Category)o;
            return Objects.equals(this.getId(), category.getId()) && Objects.equals(this.getName(), category.getName());
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.getId(), this.getName()});
    }
}