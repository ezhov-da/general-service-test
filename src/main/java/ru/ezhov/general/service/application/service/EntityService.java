package ru.ezhov.general.service.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ezhov.general.service.infrastructure.repository.EntityRepository;
import ru.ezhov.general.service.infrastructure.repository.entity.EntityObject;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntityService {
    private static final Logger LOG = LoggerFactory.getLogger(EntityService.class);

    @Autowired
    private EntityRepository entityRepository;

    public void add(String name) {
        entityRepository.save(new EntityObject(name));
    }

    @Transactional
    public void addAll(List<String> data) {
        long start = System.currentTimeMillis();
        LOG.debug("method=addAll action=\"начало сохранения с количеством '{}'\"", data.size());

        List<EntityObject> entityObjects = data.stream().map(EntityObject::new).collect(Collectors.toList());
        entityRepository.saveAll(entityObjects);

        long end = System.currentTimeMillis();
        LOG.debug("method=addAll action=\"сохранение завершено. общее время сохранения '{}' ms\"", end - start);
    }

    public List<String> all() {
        return entityRepository.findAll().stream().map(EntityObject::getName).collect(Collectors.toList());
    }
}
