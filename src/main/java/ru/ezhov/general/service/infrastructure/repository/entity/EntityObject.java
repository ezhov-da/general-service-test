package ru.ezhov.general.service.infrastructure.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EntityObject {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;

    public EntityObject() {
    }

    public EntityObject(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EntityObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return this.name;
    }
}
