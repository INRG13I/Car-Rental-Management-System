package Repository;

import Domain.IEntity;
import Domain.IEntityFactory;

import java.io.*;

public class TextRepository<T extends IEntity> extends MemoryRepository<T> {

    String fileName;
    IEntityFactory<T> entityFactory;

    public TextRepository(String fileName, IEntityFactory<T> entityFactory) {
        this.fileName = fileName;
        this.entityFactory = entityFactory;
        loadFile();
    }

    private void loadFile() {
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            String line = input.readLine();
            while (line != null && line.length() != 0) {
                super.add(entityFactory.stringToEntity(line));
                line = input.readLine();
            }

        } catch (FileNotFoundException fileNotFoundException) {
            try {
                new File(fileName).createNewFile();
            } catch (IOException ioException) {

            }

        } catch (IOException ioException) {

        }
    }

    private void saveFile() {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(fileName))) {
            for (T entity : entityList) {
                output.write(entityFactory.entityToString(entity));
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
