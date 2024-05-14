package Repository;

import Domain.IEntity;

import java.util.ArrayList;

public abstract class AbstractRepository<T extends IEntity> implements IRepository<T> {

    protected ArrayList<T> entityList = new ArrayList<>();
}
