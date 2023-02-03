package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // EntityManager
        // EntityManagerFactory
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unit");

        EntityManager manager = factory.createEntityManager();


        manager.getTransaction().begin();
        // start

        Product product = new Product();
        product.setName("Chocolate");
        manager.merge(product);
        System.out.println(product);


        // end


        manager.getTransaction().commit();
        manager.close();
    }
}