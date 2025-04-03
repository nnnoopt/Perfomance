import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task2 {
    public static void main(String[] args) {
        double centerX = 0, centerY = 0, radius = 0;

        try (BufferedReader reader1 = new BufferedReader(new FileReader(args[0]))) {
            String line = reader1.readLine();
            String[] centerData = line.split(" ");
            centerX = Double.parseDouble(centerData[0]);
            centerY = Double.parseDouble(centerData[1]);
            radius = Double.parseDouble(reader1.readLine());
        } catch (IOException | NumberFormatException e) {
            System.err.println("Ошибка при чтении файла с кругом: " + e.getMessage());
            return;
        }

        try (BufferedReader reader2 = new BufferedReader(new FileReader(args[1]))) {
            String line;
            while ((line = reader2.readLine()) != null) {
                String[] pointData = line.split(" ");
                double pointX = Double.parseDouble(pointData[0]);
                double pointY = Double.parseDouble(pointData[1]);

                double distance = Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));

                if (Math.abs(distance - radius) < 1e-6) {
                    System.out.println(0);
                } else if (distance < radius) {
                    System.out.println(1);
                } else {
                    System.out.println(2);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Ошибка при чтении файла с точками: " + e.getMessage());
        }
    }
}
