import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        //Streams can be created from Collections, Lists, Sets,
        // ints, longs, doubles, arrays, lines of a file.

        //Streams can be
        //Intermediate: filter, map or sort return a stream so
        // we can chain multiple intermediate operations.

        //Terminal: forEach, collect or reduce are either void or return
        //a non-stream result

        //Order matters for large datasets: filter first, then multiple threads.

        //1. Integer Stream
        IntStream
                .range(1,10)  //Prints 1 to 9
                .forEach(System.out::print);

        System.out.println();

        //2. Integer Stream with skip
        IntStream
                .range(1,10) //Print numbers from 1 to 9
                .skip(5)
             //   .forEach(x -> System.out.println(x));  This is how we did it
                .forEach(System.out::println);  //How we do it

        //3. Integer Stream with sum
         System.out.println(
                 IntStream.range(1,5)
                         .sum()
         );

         //4. Stream.of, sorted and findFirst
        Stream.of("Ava","Aneri","Alberto")
                .sorted()
                .findFirst()  //Just print what you find first
                .ifPresent(System.out::println);  //it is used with findFirst

        //5. Stream from Array, sort, filter and print
        String[] names = {"Al", "Kushal", "Brent", "Sarika"};
        Arrays.stream(names)     //same as Stream.of(names)
                .filter(x -> x.startsWith("S"))
                .sorted()
                .forEach(System.out::println);

        //6. Average of squares of an int array
        Arrays.stream(new int[] {2,4,6,8,10})
                .map(x -> x*x)
                .average()
                .ifPresent(System.out::println);

        //If present - consumer?

        //7. Stream from List, filter and print
        List<String> people = Arrays.asList("Al", "Ankit", "Brent", "Sarika", "amada");
        people
                .stream()
                .map(String::toLowerCase)
                .filter(x -> x.startsWith("a"))
                .forEach(System.out::println);

        //8. Stream rows from text file, sort, filter, and print
        //Create a stream object of Strings and Files.lines is going to give us a Stream
        //of a String for each line of the file and to do that we have to pass in an argument of path
        //which is the path for the name of our file
        Stream<String> bands = Files.lines(Paths.get("bands.txt")); //lines needed to throw IOException
        bands
                .sorted()
                .filter(x -> x.length() > 13)
                .forEach(System.out::println);
        bands.close(); //it's important to close this to prevent memory leaks

        //9. Stream rows from text file and save to List
        List<String> bands2 = Files.lines(Paths.get("bands.txt"))
                .filter(x -> x.contains("a"))
                .collect(Collectors.toList());
        bands2.forEach(x -> System.out.println(x));

        //10. Stream rows from CSV file and count
        Stream<String> rows1 = Files.lines(Paths.get("data.txt"));
        int rowCount = (int)rows1
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .count();
        System.out.println(rowCount + " rows.");
        rows1.close();

        //11. Stream rows from CSV file, parse data from rows
        Stream<String> rows2 = Files.lines(Paths.get("data.txt"));
        rows2
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .forEach(x -> System.out.println(x[0] + " " + x[1] + " " + x[2]));
        rows1.close();

        //12.Stream rows from CSV file, store fields in HshMap
        Stream<String> rows3 = Files.lines(Paths.get("data.txt"));
        Map<String, Integer> map = new HashMap<>();
        map = rows3
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .collect(Collectors.toMap(
                        x -> x[0],
                        x -> Integer.parseInt(x[1])
                ));
        rows3.close();
        for(String key: map.keySet()){
            System.out.println(key + " " + map.get(key));
        }

        //13. Reduction - sum
        double total = Stream.of(7.3, 1.5, 4.8)
                //starting value  //running total  //b is the new element that we are passing
                .reduce(0.0, (Double a, Double b) -> a + b);
        System.out.println("Total = " + total);

        //14. Reduction - summary statistics (Only works with Integers)
        //Int stream
        //prints count, sum, min, average, max
        IntSummaryStatistics summary = IntStream.of(7,2,19,88,73,4,10)
                .summaryStatistics();
        System.out.println(summary);



    }
}