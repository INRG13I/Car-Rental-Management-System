package Repository;

import Domain.IEntity;

import java.util.ArrayList;

public class MemoryRepository<T extends IEntity> extends AbstractRepository<T> {

    @Override
    public void add(T entity) {
        if (findById(entity.getId()).equals(-1)) {
            entityList.add(entity);
        }
    }

    @Override
    public void delete(Integer id) {
        if (!findById(id).equals(-1)) {
            entityList.remove((int) findById(id));
        }
    }

    @Override
    public void update(Integer id, T entity) {
        if (!findById(id).equals(-1) && findById(entity.getId()).equals(-1)) {
            delete(id);
            add(entity);
        }
        if (id == entity.getId() && !findById(id).equals(-1)) {
            delete(id);
            add(entity);
        }
    }

    @Override
    public Integer findById(Integer id) {
        for (Integer i = 0; i < entityList.size(); i++) {
            if (entityList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ArrayList<T> getAll() {
        return new ArrayList<>(entityList);
    }
}
