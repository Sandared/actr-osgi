package io.jatoms.actr.whiteboard.impl;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.zakgof.actr.Actr;
import com.zakgof.actr.IActorRef;
import com.zakgof.actr.IActorSystem;

import org.osgi.annotation.bundle.Capability;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import io.jatoms.actr.whiteboard.api.Actor;

@Capability(namespace = "osgi.service", attribute = {"objectClass=com.zakgof.actr.IActorRef"})
@Component
public class ActrWhiteboard {

    private Map<String, ServiceRegistration<IActorRef>> actors = new ConcurrentHashMap<>();
    private IActorSystem system = Actr.newSystem("default");
    private BundleContext context;

    @Activate
    void activate(BundleContext context) {
        this.context = context;
    }

    @Deactivate
    void deactivate(){
        system.shutdown();
    }

    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY)
    void addActor(Actor actor, Map<String, Object> properties) {
        IActorRef<Actor> actorRef = system.actorOf(() -> actor);

        String actorName = actor.getClass().getName();
        Dictionary<String, Object> props = copyProps(properties);

        if(context == null){
            context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
        }
        ServiceRegistration<IActorRef> registration = context.registerService(IActorRef.class, actorRef, props);

        actors.put(actorName, registration);
    }

    void removeActor(Actor actor){
        String actorName = actor.getClass().getName();
        ServiceRegistration<IActorRef> registration = actors.remove(actorName);
        registration.unregister();
    }

    private Dictionary<String, Object> copyProps(Map<String, Object> properties){
        Dictionary<String, Object> props = new Hashtable<>();
        for (Entry<String, Object> entry : properties.entrySet()) {
            if(!entry.getKey().startsWith("service") && !entry.getKey().startsWith("component") && !entry.getKey().startsWith("objectClass")){
                props.put(entry.getKey(), entry.getValue());
            }
        }
        return props;
    }
    
}