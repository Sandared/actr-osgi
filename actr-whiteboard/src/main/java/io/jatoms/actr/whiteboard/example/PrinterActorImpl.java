package io.jatoms.actr.whiteboard.example;

import java.util.Map;
import java.util.Map.Entry;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.jatoms.actr.whiteboard.api.Actor;
import io.jatoms.actr.whiteboard.api.Actr;

@Actr("MyActor")
@Component
public class PrinterActorImpl implements PrinterActor, Actor {

    @Reference
    StringGenerator generator;

    @Activate
    void activate(Map<String, Object> config){
        for (Entry<String, Object> entry : config.entrySet()) {
            System.out.println(entry.getKey() + " :: " + entry.getValue());
        }
    }

    @Override
    public void printHello() {
        System.out.println("Hello Actor World on Thread " + Thread.currentThread().getId());
        System.out.println("StringGenerator generated: " + generator.getGeneratedString());
    }
    
}