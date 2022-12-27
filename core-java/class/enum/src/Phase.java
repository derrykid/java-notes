import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum Phase {

    SOLID, LIQUID, GAS;

    public enum Transition{

        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);

        private final Phase from;
        private final Phase to;

        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        // values of Transition, MELT, BOIL etc
        private static final Map<Phase, Map<Phase, Transition>> m = Stream.of(values())
                .collect(Collectors.groupingBy(
                                transition -> transition.from,
                                () -> new EnumMap<>(Phase.class),
                                toMap(transition -> transition.to, transition -> transition, (x, y) -> y, () -> new EnumMap<>(Phase.class))
                        )
                );


        public static Transition from(Phase from, Phase to) {
            return m.get(from).get(to);
        }
    }

}
