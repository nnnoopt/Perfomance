import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {

        String input = args[0];

        try {
            List<Integer> nums = readNumbersFromFile(input);
            int min = calculateMinMoves(nums);
            System.out.println(min);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> readNumbersFromFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<Integer> numbers = new ArrayList<>();
        for (String line : lines) {
            numbers.add(Integer.parseInt(line));
        }
        return numbers;
    }

    private static int calculateMinMoves(List<Integer> nums) {
        Collections.sort(nums);
        int median = nums.get(nums.size() / 2);
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        return moves;
    }
}