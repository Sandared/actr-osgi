package io.jatoms.actr.whiteboard.example;

import org.osgi.service.component.annotations.Component;

@Component
public class DefaultStringGenerator implements StringGenerator {

    @Override
    public String getGeneratedString() {
        return "Default";
    }
    
}