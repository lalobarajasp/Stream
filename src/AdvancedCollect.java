import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdvancedCollect {
    public static void main(String[] args) {

        //Advanced Collect
        System.out.println("hola");
        //Joining
        String[] gridnames = {"Eduardo", "Manuel", "Carlos", "Aura", "Josue"};
        Stream.of(gridnames)
                .sorted()
                .forEach(System.out::println);

    }
}
