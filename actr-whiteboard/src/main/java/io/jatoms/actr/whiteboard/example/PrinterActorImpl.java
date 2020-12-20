package io.jatoms.actr.whiteboard.example;

import org.osgi.service.component.annotations.Component;

import io.jatoms.actr.whiteboard.api.Actor;

@Component
public class PrinterActorImpl implements PrinterActor, Actor{

    @Override
    public void printHello() {
        System.out.println("Hello Actor World on Thread " + Thread.currentThread().getId());
    }
    
}