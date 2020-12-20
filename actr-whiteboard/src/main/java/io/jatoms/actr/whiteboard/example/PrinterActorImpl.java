package io.jatoms.actr.whiteboard.example;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.jatoms.actr.whiteboard.api.Actor;
import io.jatoms.actr.whiteboard.api.Actr;

@Actr("MyActor")
@Component
public class PrinterActorImpl implements PrinterActor, Actor {
    @Reference
    StringGenerator generator;

    @Override
    public void printHello() {
        System.out.println("Hello Actor World on Thread " + Thread.currentThread().getId());
        System.out.println("StringGenerator generated: " + generator.getGeneratedString());
    }
}