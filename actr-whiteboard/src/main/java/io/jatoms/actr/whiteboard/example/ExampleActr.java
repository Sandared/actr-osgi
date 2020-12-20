package io.jatoms.actr.whiteboard.example;

import org.osgi.service.component.annotations.Component;

import io.jatoms.actr.whiteboard.api.Actr;

@Component
public class ExampleActr implements Ping, Actr {

    @Override
    public void printPing() {
        
    }
    
}