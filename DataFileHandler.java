import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataFileHandler {

    /**
     * Завантажує масив об'єктів Float з файлу.
     *
     * @param filePath Шлях до файлу з даними.
     * @return Масив об'єктів Float.
     */
    public static Float[] loadArrayFromFile(String filePath) {
        Float[] temporaryArray = new Float[1000];
        int currentIndex = 0;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = fileReader.readLine()) != null) {
                currentLine = currentLine.trim().replaceAll("^\\uFEFF", "");
                if (!currentLine.isEmpty()) {
                    float parsedValue = Float.parseFloat(currentLine);
                    temporaryArray[currentIndex++] = parsedValue;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        Float[] resultArray = new Float[currentIndex];
        System.arraycopy(temporaryArray, 0, resultArray, 0, currentIndex);

        return resultArray;
    }

    /**
     * Зберігає масив об'єктів Float у файл.
     *
     * @param floatArray Масив об'єктів Float.
     * @param filePath   Шлях до файлу для збереження.
     */
    public static void writeArrayToFile(Float[] floatArray, String filePath) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (float element : floatArray) {
                fileWriter.write(Float.toString(element));
                fileWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
