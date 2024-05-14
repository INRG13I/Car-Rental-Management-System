package Domain;

import java.io.Serializable;

public interface IEntity extends Serializable {

    static final long serialVersionUID = 1L;

    Integer getId();

    void setId(Integer id);
}
