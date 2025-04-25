import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;


/**
 * This program reads numbers from a file,
 * stores them in an ArrayList, calculates and shows the
 * mean, median, and mode.
 *
 * @author Johnnatan Yasin Medina
 * @version 1.0
 * @since 2025-04-23
 */
public final class ArrayFile {
    /**
     * This is to satisfy the style checker.
    * @exception IllegalStateException Utility class.
    * @see IllegalStateException
     */
    private ArrayFile() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * Main Method.
     *
     * @param args Unused.
     */
    public static void main(final String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java ArrayFile <filename>");
            return;
        }

        String fileName = args[0];
        ArrayList<Integer> numberList = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextInt()) {
                numberList.add(fileScanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return;
        }

        if (numberList.isEmpty()) {
            throw new IllegalStateException("The file is empty"
             + " or contains no integers.");
        }

        // Copy to fixed-length array
        int[] numbers = new int[numberList.size()];
        for (int i = 0; i < numberList.size(); i++) {
            numbers[i] = numberList.get(i);
        }

        // Display original numbers
        System.out.println("Numbers from file:");
        for (int number : numbers) {
            System.out.println(number);
        }

        // Sort the array
        Arrays.sort(numbers);

        System.out.println("Sorted list of numbers: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        String input;

        // calculate and display results
        double mean = calcMean(numbers);
        double median = calcMedian(numbers);
        ArrayList<Integer> mode = calcMode(numbers);

        System.out.printf("Mean: %.2f%n", mean);
        System.out.printf("Median: %.2f%n", median);
        System.out.print("Mode: ");
        if (mode.isEmpty()) {
            System.out.println("No mode");
        } else {
            for (int m : mode) {
                System.out.print(m + " ");
            }
            System.out.println();
        }
    }

    /**
     * calculates the mean of an array of integers.
     *
     * @param arr The array of integers
     * @return The mean as a double
     */
    public static double calcMean(final int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return (double) sum / arr.length;
    }

    /**
     * calculates the median of an array of integers.
     * Assumes the array is already sorted.
     *
     * @param arr The sorted array of integers
     * @return The median as a double
     */
    public static double calcMedian(final int[] arr) {
        int n = arr.length;
        if (n % 2 == 0) {
            return (arr[n / 2 - 1] + arr[n / 2]) / 2.0;
        } else {
            return arr[n / 2];
        }
    }

    /**
     * calculates the mode(s) of a sorted array of integers.
     * If all values occur only once, returns an empty list.
     *
     * @param arr The sorted array of integers
     * @return A list of integers that are the mode(s)
     */
    public static ArrayList<Integer> calcMode(final int[] arr) {
        ArrayList<Integer> modes = new ArrayList<>();
        int maxCount = 0;

        for (int i = 0; i < arr.length; i++) {
            int count = 1;
            while (i + 1 < arr.length && arr[i] == arr[i + 1]) {
                count++;
                i++;
            }

            if (count > maxCount) {
                maxCount = count;
                modes.clear();
                modes.add(arr[i]);
            } else if (count == maxCount && count > 1) {
                modes.add(arr[i]);
            }
        }

        return modes;
    }
}
