package src.persistance;

public interface GenericDAO<T> {
     boolean insert(T t);
     T getById(int id);
     boolean updateById(T t);
     boolean deleteById(int id);
}
