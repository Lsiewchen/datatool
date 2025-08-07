package Receiver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Receiver class is in charge of performing operations associated with carrying out requests from the invoker.
 * It is able to load from a file containing employee data if available or write to a file if the file is unavailable.
 */
public class Receiver {
    /**
     * variable containing the employee data as an array list of Strings
     */
    private List<String> dataStore = new ArrayList<>();
    /**
     * variable containing the file path
     */
    private Path path = Paths.get("./src/dataStore.txt");

    /**
     * Constructor of the Receiver class, will attempt to load from file upon object initialization
     */
    public Receiver() {
        loadFromFile();
    }

    /**
     * Method to load from a file if it exists, else does nothing
     */
    private void loadFromFile() {
        if (Files.exists(path)) {
            try (BufferedReader br = Files.newBufferedReader(path)) {
                String line;
                while ((line = br.readLine()) != null) {
                    dataStore.add(line);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to store contents of Array List to file
     */
    public void storeToFile() {
        try (BufferedWriter bw = Files.newBufferedWriter(path,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String line : dataStore) {
                bw.write(line);
                bw.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to add data to Array List
     * @param data1 first data in payload provided
     * @param data2 second data in payload provided
     * @param data3 third data in payload provided
     */
    public void add(String data1, String data2, String data3) {
        dataStore.add(data1 + " " + data2 + " " + data3);
    }

    /**
     * Overloaded method to add data to Array List, used to undo delete operations
     * @param index index to add the data back
     * @param data1 first data to add back
     * @param data2 second data to add back
     * @param data3 third data to add back
     */
    public void add(int index, String data1, String data2, String data3) {
        dataStore.add(index,data1 + " " + data2 + " " + data3);
    }

    /**
     * Updates the data at the specified index in the data store
     * @param index the index of the entry to update
     * @param data1 the value for the first field (may or may not be new)
     * @param data2 the value for the second field (may or may not be new)
     * @param data3 the value for the third field (may or may not be new)
     */
    public void update(int index, String data1, String data2, String data3) {
        String retrievedLine = retrieveLine(index);
        String[] datas = retrievedLine.split(" ");
        String newData2 = (data2 == null) ? datas[1] : data2;
        String newData3 = (data3 == null) ? datas[2] : data3;
        dataStore.set(index, data1 + " "  + newData2 + " " + newData3);
    }

    /**
     * Deletes the entry at the specified index from the data store.
     * @param index the index of the entry to remove
     */
    public void delete(int index) {
        dataStore.remove(index);
    }

    /**
     * Prints all entries in the data store.
     * Each entry is prefixed with its 1-based index in two-digit format.
     */

    public void list() {
        System.out.println("List");
        for (int i = 0; i < dataStore.size(); i++) {
            System.out.printf("%02d. %s\n", i+1, dataStore.get(i));
        }
    }

    /**
     * Retrieves the line of data at the specified index.
     * @param index the index of the entry to retrieve
     * @return the data stored at the given index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public String retrieveLine(int index) throws IndexOutOfBoundsException {
        return dataStore.get(index);
    }

    /**
     * Returns the number of entries currently stored.
     * @return the size of the data store
     */
    public int getDataStoreSize() {
        return dataStore.size();
    }
}
