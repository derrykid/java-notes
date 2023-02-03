package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Jack");
        list.add("Mary");
        list.add("Jack");

        Set<String> party = new HashSet<>();
        party.add("Jack");
        party.add("Mary");
        party.add("Jack");

        System.out.println("Set: " + party);
        System.out.println("List: " + list);

        party.iterator();
        for (String each : party) {
            System.out.println(each);
        }
    }
}