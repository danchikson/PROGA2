import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Клас BasicDataOperationUsingList реалізує операції з колекціями типу LinkedList для даних float.
 * 
 * <p>Методи класу:</p>
 * <ul>
 *   <li>{@link #executeDataOperations()} - Виконує комплекс операцій з даними.</li>
 *   <li>{@link #performArraySorting()} - Упорядковує масив елементів float.</li>
 *   <li>{@link #findInArray()} - Здійснює пошук елемента в масиві float.</li>
 *   <li>{@link #locateMinMaxInArray()} - Визначає найменше і найбільше значення в масиві.</li>
 *   <li>{@link #sortList()} - Сортує колекцію List з float.</li>
 *   <li>{@link #findInList()} - Пошук конкретного значення в списку.</li>
 *   <li>{@link #locateMinMaxInList()} - Пошук мінімального і максимального значення в списку.</li>
 * </ul>
 */
public class BasicDataOperationUsingList {
    private float floatValueToSearch;
    private Float[] floatArray;
    private List<Float> floatList;

    BasicDataOperationUsingList(float floatValueToSearch, Float[] floatArray) {
        this.floatValueToSearch = floatValueToSearch;
        this.floatArray = floatArray;
        this.floatList = new LinkedList<>(Arrays.asList(floatArray));
    }

    public void executeDataOperations() {
        // працюємо зі списком
        findInList();
        locateMinMaxInList();

        sortList();

        findInList();
        locateMinMaxInList();

        // працюємо з масивом
        findInArray();
        locateMinMaxInArray();

        performArraySorting();

        findInArray();
        locateMinMaxInArray();

        // зберігаємо відсортований масив
        DataFileHandler.writeArrayToFile(floatArray, BasicDataOperation.PATH_TO_DATA_FILE + ".sorted");
    }

    void performArraySorting() {
        long timeStart = System.nanoTime();

        Arrays.sort(floatArray);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування масиву float");
    }

    void findInArray() {
        long timeStart = System.nanoTime();

        int position = Arrays.binarySearch(this.floatArray, floatValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в масивi float");

        if (position >= 0) {
            System.out.println("Елемент '" + floatValueToSearch + "' знайдено в масивi за позицією: " + position);
        } else {
            System.out.println("Елемент '" + floatValueToSearch + "' відсутній в масиві.");
        }
    }

    void locateMinMaxInArray() {
        if (floatArray == null || floatArray.length == 0) {
            System.out.println("Масив є пустим або не ініціалізованим.");
            return;
        }

        long timeStart = System.nanoTime();

        float minValue = floatArray[0];
        float maxValue = floatArray[0];

        for (float currentValue : floatArray) {
            if (currentValue < minValue) {
                minValue = currentValue;
            }
            if (currentValue > maxValue) {
                maxValue = currentValue;
            }
        }

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмального i максимального значення в масивi");

        System.out.println("Найменше значення в масивi: " + minValue);
        System.out.println("Найбільше значення в масивi: " + maxValue);
    }

    void findInList() {
        long timeStart = System.nanoTime();

        int position = Collections.binarySearch(this.floatList, floatValueToSearch);

        PerformanceTracker.displayOperationTime(timeStart, "пошук елемента в List float");        

        if (position >= 0) {
            System.out.println("Елемент '" + floatValueToSearch + "' знайдено в LinkedList за позицією: " + position);
        } else {
            System.out.println("Елемент '" + floatValueToSearch + "' відсутній в LinkedList.");
        }
    }

    void locateMinMaxInList() {
        if (floatList == null || floatList.isEmpty()) {
            System.out.println("Колекція LinkedList є пустою або не ініціалізованою.");
            return;
        }

        long timeStart = System.nanoTime();

        float minValue = Collections.min(floatList);
        float maxValue = Collections.max(floatList);

        PerformanceTracker.displayOperationTime(timeStart, "визначення мiнiмального i максимального значення в List");

        System.out.println("Найменше значення в List: " + minValue);
        System.out.println("Найбільше значення в List: " + maxValue);
    }

    void sortList() {
        long timeStart = System.nanoTime();

        Collections.sort(floatList);

        PerformanceTracker.displayOperationTime(timeStart, "упорядкування LinkedList float");
    }
}
