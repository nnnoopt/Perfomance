public class Task1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }

        StringBuilder path = new StringBuilder();

        int currentIndex = 0;

        int startIndex = currentIndex;

        while (true) {
            path.append(array[currentIndex]);

            currentIndex = (currentIndex + m - 1) % n;

            if (currentIndex == startIndex) {
                break;
            }
        }

        System.out.println(path);
    }
}