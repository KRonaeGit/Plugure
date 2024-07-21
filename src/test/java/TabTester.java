import kronaegit.plugure.useful.command.TabTree;
import kronaegit.plugure.util.Mapper;

import java.util.List;
import java.util.Map;

public class TabTester {
    public static void main(String[] args) {
        TabTree main = TabTree.by(Mapper.tree()
            .add("coding", TabTree.by(
                Mapper.tree()
                    .add("9999", null)
                    .add("9998", null)
                    .add("9777", null)
                    .add("8776", null)
                    .add("8678", null)
                )
            ).add("hellomotherfucker", null)
        );
        // main = coding(9999/9998/9777/8776/8678)/hellomotherfucker()
        System.out.println(main.complete(List.of())); // hope: codinig, hellomotherfucker
        System.out.println(main.complete(List.of(""))); // hope: codinig, hellomotherfucker
        System.out.println(main.complete(List.of("h"))); // hope: hellomotherfucker
        System.out.println(main.complete(List.of("c"))); // hope: codinig
        System.out.println(main.complete(List.of("coding"))); // hope: codinig
        System.out.println(main.complete(List.of("coding", ""))); // hope: 9999, 9998, 9777, 8776, 8678 | BUT EMPTY LIST PRINTED
        System.out.println(main.complete(List.of("coding", "9"))); // hope: 9999, 9998, 9777 | BUT EMPTY LIST PRINTED
    }
}
