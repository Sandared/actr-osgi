package io.jatoms.actr.whiteboard.example;

import com.zakgof.actr.IActorRef;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;



@Component
public class Example {

    // "io.jatoms.actr" is defined by the ComponentPropertyType @Actr
    @Reference(target = "(io.jatoms.actr=MyActor)")
    IActorRef<PrinterActor> printer;

    @Activate
    void activate(){
        System.out.println("Starting Example on Thread " + Thread.currentThread().getId());
        printer.tell(PrinterActor::printHello);
    }
    
}