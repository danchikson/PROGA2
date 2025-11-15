import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Клас BasicDataOperationUsingSet реалізує операції з множиною HashSet для float.
 */
public class BasicDataOperationUsingSet {
    private float floatValueToSearch;
    private Float[] floatArray;
    private Set<Float> floatSet;

    BasicDataOperationUsingSet(float floatValueToSearch, Float[] floatArray) {
        this.floatValueToSearch = floatValueToSearch;
        this.floatArray = floatArray;
        this.floatSet = new HashSet<>(Arrays.asList(floatArray));
    }

    public void executeDataAnalysis() {
        // спочатку аналізуємо множину
        findInSet();
        locateMinMaxInSet();
        analyzeArrayAndSet();

        // потім масив
        findInArray();
        locateMinMaxInArray();

        performArraySorting();

        findInArray();
        locateMinMaxInArray();

        DataFileHandler.writeArrayToFile(floatArray, BasicDataOperation.PATH_TO_DATA_FILE + ".sorted");
    }

    private void performArraySorting() {
        long timeStart = System.nanoTime();

        Arrays.sort(floatArray);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування масиву float");
    }

    private void findInArray() {
        long timeStart = System.nanoTime();

        int position = Arrays.binarySearch(this.floatArray, floatValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в масивi float");

        if (position >= 0) {
            System.out.println("Елемент '" + floatValueToSearch + "' знайдено в масивi за позицією: " + position);
        } else {
            System.out.println("Елемент '" + floatValueToSearch + "' відсутній в масиві.");
        }
    }

    private void locateMinMaxInArray() {
        if (floatArray == null || floatArray.length == 0) {
            System.out.println("Масив є пустим або не ініціалізованим.");
            return;
        }

        long timeStart = System.nanoTime();

        float minValue = floatArray[0];
        float maxValue = floatArray[0];

        for (float currentValue : floatArray) {
            if (currentValue < minValue) minValue = currentValue;
            if (currentValue > maxValue) maxValue = currentValue;
        }

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмального i максимального значення в масивi");

        System.out.println("Найменше значення в масивi: " + minValue);
        System.out.println("Найбільше значення в масивi: " + maxValue);
    }

    private void findInSet() {
        long timeStart = System.nanoTime();

        boolean elementExists = this.floatSet.contains(floatValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в HashSet float");

        if (elementExists) {
            System.out.println("Елемент '" + floatValueToSearch + "' знайдено в HashSet");
        } else {
            System.out.println("Елемент '" + floatValueToSearch + "' відсутній в HashSet.");
        }
    }

    private void locateMinMaxInSet() {
        if (floatSet == null || floatSet.isEmpty()) {
            System.out.println("HashSet є пустим або не ініціалізованим.");
            return;
        }

        long timeStart = System.nanoTime();

        float minValue = Collections.min(floatSet);
        float maxValue = Collections.max(floatSet);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмального i максимального значення в HashSet");

        System.out.println("Найменше значення в HashSet: " + minValue);
        System.out.println("Найбільше значення в HashSet: " + maxValue);
    }

    private void analyzeArrayAndSet() {
        System.out.println("Кiлькiсть елементiв в масивi: " + floatArray.length);
        System.out.println("Кiлькiсть елементiв в HashSet: " + floatSet.size());

        boolean allElementsPresent = true;
        for (float element : floatArray) {
            if (!floatSet.contains(element)) {
                allElementsPresent = false;
                break;
            }
        }

        if (allElementsPresent) {
            System.out.println("Всi елементи масиву наявні в HashSet.");
        } else {
            System.out.println("Не всi елементи масиву наявні в HashSet.");
        }
    }
}
