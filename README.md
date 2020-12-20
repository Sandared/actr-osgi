[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/Sandared/actr-osgi)

# actr-osgi
Integration of OSGi DS with https://github.com/zakgof/actr (Interesting new approach on actor systems)

Be careful: This is just a super simple proof of concept and is not meant for any real usage right now

## What I try to do:
Integrate an Actor framework into OSGi in that it is easily possible to 
* create Actors
* reference them via DS 

In order to create an Actor a DS Component must implement the `Actor` marker interface (no methods to implement, just needed to get picked up by the whiteboard)
```java
@Component
public class PrinterActorImpl implements PrinterActor, Actor{

    @Override
    public void printHello() {
        System.out.println("Hello Actor World on Thread " + Thread.currentThread().getId());
    }
    
}
```

This actor can then be asynchronously called from a normal component like this:
```java
@Component
public class Example {

    @Reference(target = "(actr=PrinterActorImpl)")
    IActorRef<PrinterActor> printer;

    @Activate
    void activate(){
        System.out.println("Starting Example on Thread " + Thread.currentThread().getId());
        printer.tell(PrinterActor::printHello);
    }
    
}
```

## Project Setup
In order to set this project up either [![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/Sandared/actr-osgi) and let GitPod do the work, or just 
* clone the project
* cd libs/actr -> mvn clean install 
* cd ../.. -> mvn clean install

If you want to run the example you need a [Karaf Distribution](https://karaf.apache.org/download.html) (at the time of writing version 4.3.0). Either download it or if you are using GitPod this was already done for you (it even should have started on workspace creation ;))
In your Karaf type the following commands (after you built the project):
* feature:repo-add mvn:io.jatoms/featurerepo/1.0-SNAPSHOT/xml
* feature:install actr-osgi 

Now there should be some output on the console like this:
```
Starting Example on Thread 53
Hello Actor World on Thread 241
```
