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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
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
        try {
            LOG.debug("method=data action=\"получены данные\" data={}", data);

            entityService.addAll(Arrays.asList(data));

            return "OK";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @GetMapping(path = "data")
    public int data(@RequestParam("count") int count) throws Exception {
        LOG.debug("method=data action=\"получены данные\" count={}", count);

        IntStream range = IntStream.range(0, count);
        List<String> data = range.mapToObj(r -> r + "").collect(Collectors.toList());
        entityService.addAll(data);

        LOG.debug("method=data action=\"данные обработаны\"");

        return entityService.all().size();
    }
}
