package ru.ezhov.general.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ezhov.general.service.application.service.EntityService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController()
public class EntityController {

    @Autowired
    EntityService entityService;

    @GetMapping(path = "add")
    public List<String> add(@RequestParam(name = "name") @NotNull String name) {
        entityService.add(name);
        return entityService.all();
    }

    @GetMapping(path = "all")
    public List<String> all() {
        return entityService.all();
    }
}
