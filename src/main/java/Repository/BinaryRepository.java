package Repository;

import Domain.IEntity;

import java.io.*;

public class BinaryRepository<T extends IEntity> extends MemoryRepository<T> {

    private String fileName;

    public BinaryRepository(String fileName) {
        this.fileName = fileName;
        loadFile();
    }

    private void loadFile() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                super.add((T) input.readObject());
            }
        } catch (FileNotFoundException fileNotFoundException) {
            try {
                new File(fileName).createNewFile();
            } catch (IOException ioException) {

            }

        } catch (IOException ioException) {

        } catch (ClassNotFoundException classNotFoundException) {

        }
    }

    private void saveFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (T entity : entityList) {
                output.writeObject(entity);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            try {
                new File(fileName).createNewFile();
            } catch (IOException ioException) {

            }

        } catch (IOException ioException) {

        }
    }

    @Override
    public void add(T entity) {
        super.add(entity);
        saveFile();
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
        saveFile();
    }

    @Override
    public void update(Integer id, T entity) {
        super.update(id, entity);
        saveFile();
    }
}
