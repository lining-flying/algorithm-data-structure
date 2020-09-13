package linklist;

public class ListNodeWithRandom<T> {

    private T value ;

    private ListNodeWithRandom<T> next ;

    private ListNodeWithRandom<T> rand ;

    public ListNodeWithRandom(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ListNodeWithRandom<T> getNext() {
        return next;
    }

    public void setNext(ListNodeWithRandom<T> next) {
        this.next = next;
    }

    public ListNodeWithRandom<T> getRand() {
        return rand;
    }

    public void setRand(ListNodeWithRandom<T> rand) {
        this.rand = rand;
    }
}
