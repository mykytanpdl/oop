package lab3to5.bank.business.model;

import java.util.List;

public interface Repository<T, ID>
{
    T save(T object);
    T findById(ID id);
    void delete(T object);
    void update(T object);
    List<T> findAll();

}
