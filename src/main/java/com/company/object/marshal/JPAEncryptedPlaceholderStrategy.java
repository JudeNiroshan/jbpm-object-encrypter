package com.company.object.marshal;

import org.drools.persistence.jpa.marshaller.JPAPlaceholderResolverStrategy;
import org.kie.api.runtime.Environment;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

public class JPAEncryptedPlaceholderStrategy extends JPAPlaceholderResolverStrategy {
    public JPAEncryptedPlaceholderStrategy(Environment env) {
        super(env);
    }

    public JPAEncryptedPlaceholderStrategy(EntityManagerFactory emf) {
        super(emf);
    }

    public JPAEncryptedPlaceholderStrategy(String persistenceUnit, ClassLoader cl) {
        super(persistenceUnit, cl);
    }

    public JPAEncryptedPlaceholderStrategy(String name, String persistenceUnit, ClassLoader cl) {
        super(name, persistenceUnit, cl);
    }

    @Override
    public byte[] marshal(Context context, ObjectOutputStream os, Object object) throws IOException {
        System.out.println("********************* JUDE ADDED START *********************");
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = null;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.printf("Field name: %s, Field value: %s%n", name, value);
        }
        System.out.println("********************* JUDE ADDED END *********************");

        return super.marshal(context, os, object);
    }
}
