package ru.ezhov.general.service.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ezhov.general.service.application.service.EntityService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController()
public class EntityResource {
    private static final Logger LOG = LoggerFactory.getLogger(EntityResource.class);


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

    @PostMapping(path = "data")
    public String data(@RequestBody String data) {
        LOG.debug("method=data action=\"получены данные\" data={}", data);

        return "OK";
    }
}
