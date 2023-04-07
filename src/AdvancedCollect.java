import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

public class AdvancedCollect {
    public static void main(String[] args) throws IOException {

        //Advanced Collect

        //Joining
        List<String> str = Arrays.asList("Geeks", "for", "Geeks");
        // Convert the string list into String
        // using Collectors.joining() method
        String chString
                = str.stream().collect(Collectors.joining());
        System.out.println(chString);

        //toSet()
        //toSet() returns a Collector that accumulates the input elements into a new Set.
        Stream<String> s = Stream.of("Today",
                "I can tell you",
                "how beautiful",
                "you are");
        Set<String> mySet = s.collect(Collectors.toSet());
        System.out.println(mySet);

        //toCollection
        //It's used to create a Collection using Collector.
        // It returns a Collector that accumulates the input elements into a new Collection, in the order in which they are passed.
        Stream<String> sadSong = Stream.of("Dancing", "on", "my own");
        Collection<String> names = sadSong.collect(Collectors
                .toCollection(TreeSet::new));
        System.out.println(names);

        //SummarizingDouble()
        //summarizingDouble() is a static method of the Collectors class which returns a Collector.
        // This Collector summarizes the statistics for the results that are obtained by applying
        // the passed ToDoubleFunction implementation to the input elements.

        //The following statistics are provided on the elements of the stream:
        //The count of all the elements
        //The cumulative sum of the elements
        //The minimum element
        //The maximum element
        //The average of all the elements
        List<Double> doubleList = Arrays.asList(23.43, 23.32, 8.76567);
        System.out.println("Contents of the list - " + doubleList);

        Stream<Double> doubleStream = doubleList.stream();
        DoubleSummaryStatistics doubleSummaryStatistics = doubleStream.collect(Collectors.summarizingDouble(e -> e));
        System.out.println("Summary statistics of the stream - " + doubleSummaryStatistics);

        //PartitioningBy
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // method to split the stream of elements into
        // 2 parts, greater than 3 and less than 3.
        Map<Boolean, List<Integer>> map = numbers
                .collect(Collectors.partitioningBy(num -> num > 3));

        System.out.println("Elements in stream partitioned by less than equal to 3: \n" + map); //{false=[1, 2, 3], true=[4, 5, 6, 7, 8, 9, 10]}

        //GroupingBy
        //groupingBy() offers advanced partitioning – where we can partition the stream into more than just two groups.
        List<Integer> inputList = Arrays.asList(1, 2, 3, 4, 5);
        int midIndex = (inputList.size() - 1) / 2;

        Map<Boolean, List<Integer>> mapping = inputList.stream()
                .collect(Collectors.groupingBy(mapi -> inputList.indexOf(mapi) > midIndex));
        System.out.println(mapping);

        List<List<Integer>> lists = new ArrayList<>(mapping.values());
        System.out.println("The first sublist is " + lists.get(0));
        System.out.println("The first sublist is " + lists.get(1));

        //Parallel Stream
        //meant for utilizing multiple cores of the processor. Using parallel streams, we can divide the code into multiple
        //streams that are executed in parallel on separate cores and the final result is the combination of the individual outcomes.

        //Use parallel streams in cases where no matter what is the order of execution,
        //the result of one element does not affect the other
        File fileName = new File("bands.txt");
        // Create a Stream of string type
        // using the lines() method to
        // read one line at a time from the text file
        Stream<String> text = Files.lines(fileName.toPath());
        text.parallel().forEach(System.out::println);
        text.close();

        //Infinite Streams (Unbounded Streams)
        //It helps to perform operations while the elements are still getting generated.
        // We might not know beforehand how many elements we’ll need. Unlike using list or map, where all the elements are already populated.
        //
        //There are two ways to generate infinite streams: generate and iterate
        //Generate
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        //Iterate
//        Stream<Integer> evenNumStream = Stream.iterate(2, i -> i * 2);
//
//        List<Integer> collect = evenNumStream
//                .limit(5)
//                .collect(Collectors.toList())
//                .forEach(System.out::println);
        //Como imprimir una collection?

        //File Operations
        //File Write Operation
        //File Read Operation

        //Improvements of Java 9
        //takeWhile
        Stream.of(1,2,3,4,5,6,7,8,9,0,9,8,7,6,5,4,3,2,1,0)
                .takeWhile(x -> x <= 5)
                .forEach(System.out::println);

        System.out.println();

        //dropWhile
        Stream.of(1,2,3,4,5,6,7,8,9,0,9,8,7,6,5,4,3,2,1,0)
                .dropWhile(x -> x <= 5)
                .forEach(System.out::println);

        System.out.println();

        //iterate
        Stream.iterate(1, i -> i < 256, i -> i * 2)
                .forEach(System.out::println);

        //ofNullable
        //It doesn't throw a NullPointerExceptions even though it is the input
        Integer number = null;
        Stream<Integer> result = Stream.ofNullable(number);





    }
}
