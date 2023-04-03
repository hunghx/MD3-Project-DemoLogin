package ra.bussiness.design;


import java.util.List;

public interface ICRUD<T,E> {
    List<T> readFromFile();
    boolean writeToFile(List<T> list);
    boolean create(T t);
    T inputData();
    boolean  update(E e);
    boolean delete(E e);
    List<T> searchByName(String name);
    T findById(E e);
    List<T> getAll();
}
