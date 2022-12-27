import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        // this won't work, make it optional
//        String color = getDogName("Julien");
        Optional<String> color = getDogName("Julien");
        System.out.println(color);

        // use Optional.get() method, it'll return the value inside it
        String colour = getDogName("Jax").get(); // this throws 'NoSuchElementException'
        System.out.println(colour);

        // Scenario A
        // use Optional in condition
        if (color.isEmpty()) {          // true
            System.out.println("Yes");
        }
        if (color.isPresent()){         // it's not present, so it's false
            System.out.println("yest");
        }
        if (color.equals(Optional.empty())){  // Yes, it's Optional.empty
            System.out.println("Of course");
        }


        /*
        *  Scenario A in nutshell
        *  The above might have too many lines of code, the below simply make it simple
        *  orElse() : show the value if present, else: show the default value
        * */
        Optional<String> colorOfDog = getDogName("Yolo");
        System.out.println(colorOfDog.orElse("default value"));


    }



    static Optional<String> getDogName(String name){
        Map<String, String> dogMap = new HashMap<>();
        dogMap.put("Jack", "Black");
        dogMap.put("Will", "Grey");
        dogMap.put("Annie", "Gold");

        String color = dogMap.get(name);
        return color == null ? Optional.empty() : Optional.of(color);

    }
}
