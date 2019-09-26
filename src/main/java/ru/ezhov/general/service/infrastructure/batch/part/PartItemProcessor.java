package ru.ezhov.general.service.infrastructure.batch.part;

import org.springframework.batch.item.ItemProcessor;
import ru.ezhov.general.service.domain.model.Part;

public class PartItemProcessor implements ItemProcessor<String, Part> {
    @Override
    public Part process(String s) throws Exception {
        return new Part(s);
    }
}
