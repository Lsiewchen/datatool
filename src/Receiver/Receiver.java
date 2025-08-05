package Receiver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Receiver {
    private List<String> dataStore = new ArrayList<>();
    private Path path = Paths.get("./src/dataStore.txt");

    public Receiver() {
        loadFromFile();
    }

    private void loadFromFile() {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                dataStore.add(line);
            }
        }
        catch (NoSuchFileException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public void add(String data1, String data2, String data3) {
        dataStore.add(data1 + " " + data2 + " " + data3);
    }

    public void add(int index, String data1, String data2, String data3) {
        dataStore.add(index,data1 + " " + data2 + " " + data3);
    }

    public void update(int index, String data1, String data2, String data3) {
        String retrievedLine = retrieveLine(index);
        String[] datas = retrievedLine.split(" ");
        String newData2 = (data2 == null) ? datas[1] : data2;
        String newData3 = (data3 == null) ? datas[2] : data3;
        dataStore.set(index, data1 + " "  + newData2 + " " + newData3);
    }

    public void delete(int index) {
        dataStore.remove(index);
    }

    public void list() {
        System.out.println("List");
        for (int i = 0; i < dataStore.size(); i++) {
            System.out.printf("%02d. %s\n", i+1, dataStore.get(i));
        }
    }

    public String retrieveLine(int index) throws IndexOutOfBoundsException{
        return dataStore.get(index);
    }

    public int getDataStoreSize() {
        return dataStore.size();
    }
}
