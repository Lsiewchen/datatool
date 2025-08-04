package Tools;

import Commands.Command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Receiver {
    private List<String> dataStore = new ArrayList<>();
    private Path path = Paths.get("./src/dataStore.txt"); //backwards compatible up to Java 8

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

    public void saveToFile() {
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

    public void add(String data1, String data2, String data3) { //sc
        if (isValidEmailFormat(data3)) {
            dataStore.add(convertTitleCase(data1) + " " +
                    convertTitleCase(data2) + " " + data3);
            saveToFile();
            System.out.println("add");
        }
        else {
            System.out.println("Invalid email address entered");
        }

    }

    public void add(int index, String data1, String data2, String data3) { //sc
        dataStore.add(index, convertTitleCase(data1) + " " +
                convertTitleCase(data2) + " " + data3);
        saveToFile();
    }

    public void update(int index, String data1, String data2, String data3) { //sc
        if (index < 0 || index > dataStore.size()-1) {
            System.out.println("Invalid index entered");
            return;
        }
        if (data3 != null && !isValidEmailFormat(data3)) {
            System.out.println("Invalid email address entered");
            return;
        }

        String retreivedLine = retrieveLine(index);
        String[] datas = retreivedLine.split(" ");
        String newData1 = convertTitleCase(data1);
        String newData2 = (data2 == null) ? datas[1] : convertTitleCase(data2);
        String newData3 = (data3 == null) ? datas[2] : data3;
        dataStore.set(index, newData1 + " "  + newData2 + " " + newData3);
        saveToFile();
        System.out.println("update");
    }

    public void delete(int index) { //yr
        if (index < 0 || index > dataStore.size()-1) {
            System.out.println("Invalid index entered");
            return;
        }
        dataStore.remove(index);
        saveToFile();
        System.out.println("delete");
    }

    public void undo(Stack<Command> history) { //yr
        history.pop().undo();
        System.out.println("undo");
    }

    public void list() { //yr
        System.out.println("List");
        for (int i = 0; i < dataStore.size(); i++) {
            System.out.printf("%02d. %s\n", i+1, dataStore.get(i));
        }
    }

    public boolean isValidEmailFormat(String email) {
        if (email == null) {
            return false;
        }
        Pattern pattern = Pattern.compile
                ("^\\w+(?:[.-]?\\w+)*@[a-zA-Z0-9]+(?:[.-]?[a-zA-Z0-9]+)*\\.[a-z]{2,3}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public String convertTitleCase(String title) {
        return  title.substring(0, 1).toUpperCase() + title.substring(1);
    }

    public String retrieveLine(int index) {
        return dataStore.get(index);
    }

    public int getDataStoreSize() {
        return dataStore.size();
    }
}
