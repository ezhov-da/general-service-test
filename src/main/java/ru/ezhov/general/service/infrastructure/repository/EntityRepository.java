package ru.ezhov.general.service.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ezhov.general.service.infrastructure.repository.entity.EntityObject;

public interface EntityRepository extends JpaRepository<EntityObject, Long> {
}
