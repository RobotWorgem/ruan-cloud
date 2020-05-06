package com.ruan.polestar.event.listenex2;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ListenerRegistry2 {

    private Map<String, Set<Class<?>>> listenerClasses = new HashMap<>();

    public ListenerRegistry2(String eventType) {
        this.listenerClasses.put(eventType, new HashSet<>());
    }

    public void addListener(String eventType, Class<?> listenerClass) {
        Set<Class<?>> users = listenerClasses.get(eventType);
        users.add(listenerClass);
    }

    public void notifyListener(String eventType, Source2 source) throws ReflectiveOperationException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> returnList = new ArrayList<Future<String>>();


        Set<Class<?>> classes = listenerClasses.get(eventType);
        for (Class<?> listener : classes) {
            Class<?> partypes[] = new Class[1];
            partypes[0] = MyEvent2.class;
            Constructor<?> ct = listener.getConstructor(partypes);
            Object arglist[] = new Object[1];
            arglist[0] = new MyEvent2(source);
            CallableEventListener eventlistener = (CallableEventListener) ct.newInstance(arglist);

            Future<String> future = executorService.submit(eventlistener);
            returnList.add(future);
        }

        for (Future<String> fs : returnList) {
            try {
                while (!fs.isDone()) {
                    ;
                }
                System.out.println("Source - " + fs.get());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }
        }
    }
}
