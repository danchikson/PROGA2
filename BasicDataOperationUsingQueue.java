import java.util.Queue;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class BasicDataOperationUsingQueue {
    private float floatValueToSearch;
    private Float[] floatArray;
    private Queue<Float> floatQueue;

    BasicDataOperationUsingQueue(float floatValueToSearch, Float[] floatArray) {
        this.floatValueToSearch = floatValueToSearch;
        this.floatArray = floatArray;
        this.floatQueue = new PriorityQueue<>(Arrays.asList(floatArray));
    }

    public void runDataProcessing() {
        findInQueue();
        locateMinMaxInQueue();
        performQueueOperations();

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

    private void findInQueue() {
        long timeStart = System.nanoTime();
        boolean elementExists = this.floatQueue.contains(floatValueToSearch);
        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в PriorityQueue float");

        if (elementExists) {
            System.out.println("Елемент '" + floatValueToSearch + "' знайдено в PriorityQueue");
        } else {
            System.out.println("Елемент '" + floatValueToSearch + "' відсутній в PriorityQueue.");
        }
    }

    private void locateMinMaxInQueue() {
        if (floatQueue == null || floatQueue.isEmpty()) {
            System.out.println("Черга є пустою або не ініціалізованою.");
            return;
        }

        long timeStart = System.nanoTime();

        float minValue = Collections.min(floatQueue);
        float maxValue = Collections.max(floatQueue);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмального i максимального значення в PriorityQueue");

        System.out.println("Найменше значення в PriorityQueue: " + minValue);
        System.out.println("Найбільше значення в PriorityQueue: " + maxValue);
    }

    private void performQueueOperations() {
        if (floatQueue == null || floatQueue.isEmpty()) {
            System.out.println("Черга є пустою або не ініціалізованою.");
            return;
        }

        Float headElement = floatQueue.peek();
        System.out.println("Головний елемент черги (peek): " + headElement);

        headElement = floatQueue.poll();
        System.out.println("Видалений елемент черги (poll): " + headElement);

        headElement = floatQueue.peek();
        System.out.println("Новий головний елемент черги: " + headElement);
    }
}
