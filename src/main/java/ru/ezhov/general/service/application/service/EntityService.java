package ru.ezhov.general.service.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ezhov.general.service.infrastructure.repository.EntityRepository;
import ru.ezhov.general.service.infrastructure.repository.entity.EntityObject;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntityService {
    @Autowired
    private EntityRepository entityRepository;

    public void add(String name) {
        entityRepository.save(new EntityObject(name));
    }

    public List<String> all() {
        return entityRepository.findAll().stream().map(EntityObject::getName).collect(Collectors.toList());
    }
}
