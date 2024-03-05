import java.util.LinkedList;

public interface Repository<T> {
    void rAdd(T object);
    void rRemove(T object);
    void rUpdate(T object,int num,String column); // Think it as replace for set

    LinkedList<T> rList();
}
