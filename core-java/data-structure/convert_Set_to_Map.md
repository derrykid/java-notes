[Convert set to map use stream](https://www.techiedelight.com/convert-set-to-map-java/)
We have a Dog class. It has `getName` and `getAge` methods.

We can use Dog instances to practice convert set to maps:
```java
          Map<String, Integer> dogList = new HashMap<>();                       
          Set<Dog> dogSet = new HashSet<>();                                    
          dogSet.add(new Dog("Peggy", 11));                                     
          dogSet.add(new Dog("Black", 8));                                      
          dogSet.add(new Dog("Judy", 3));                                       
                                                                                
          dogList = dogSet.stream().collect(Collectors.toMap(k -> k.getName(), v   -> v.getAge()));
      
          // get dog ages    
          Set<Map.Entry<String, Integer>> entries = dogList.entrySet();    
          for (Map.Entry<String, Integer> per: entries) {    
              System.out.println(per.getKey() + " is " + per.getValue());       
          }  
```
