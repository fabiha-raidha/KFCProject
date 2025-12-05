package class_controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileRepository<T extends Serializable> implements Repository<T> {
    private final String fileName;
    private final List<T> dataList;

    public BinaryFileRepository(String fileName) {
        this.fileName = fileName;
        this.dataList = new ArrayList<>();
        loadFromFile();
    }

    @Override
    public void add(T item) {
        dataList.add(item);
        saveAll();
    }

    @Override
    public void update(T item) {
        saveAll();
    }

    @Override
    public void delete(T item) {
        dataList.remove(item);
        saveAll();
    }

    @Override
    public List<T> getAll() {
        return dataList;
    }

    @Override
    public void saveAll() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (T item : dataList) {
                oos.writeObject(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving to file: " + fileName);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File f = new File(fileName);
        if (!f.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                try {
                    dataList.add((T) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}