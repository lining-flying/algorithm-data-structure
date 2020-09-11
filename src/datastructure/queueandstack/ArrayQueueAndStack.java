package datastructure.queueandstack;

/**
 * 数组实现栈和队列
 */
public class ArrayQueueAndStack {

    /**
     * 数组实现双向列表
     * @param <T>
     */
    class DoubleArrayList<T>{

        private Object[] array ;
        private int pushIndex ;
        private int pollIndex ;
        private int size ;
        private int limit ;

        public DoubleArrayList(int limit) {
            this.limit = limit;
            this.array = new Object[limit];
        }

        public void addFirst(T ele){
            if(size == limit){
                throw new IllegalStateException("container is full ！");
            }
            array[pollIndex] = ele ;
            size++ ;
            pollIndex = preIndex(pollIndex);
        }

        public void addLast(T ele){
            if(size == limit){
                throw new IllegalStateException("container is full!");
            }
            array[pushIndex] = ele ;
            size++ ;
            pushIndex = nextIndex(pushIndex);
        }

        public T removeFirst(){
            if(size == 0){
                throw new IllegalStateException("container is empty!");
            }
            Object o = array[pollIndex + 1];
            pollIndex = nextIndex(pollIndex);
            size-- ;
            return (T) o ;
        }

        public T removeLast(){
            if(size == 0){
                throw new IllegalStateException("container is empty!");
            }
            Object o = array[pushIndex - 1];
            pushIndex = preIndex(pushIndex);
            size -- ;
            return (T) o ;
        }

        public int size(){
            return size ;
        }

        private int nextIndex(int pushIndex) {
            if(pushIndex == limit-1){
                return 0 ;
            }
            return ++pushIndex ;
        }

        private int preIndex(int pollIndex) {
            if(pollIndex==0){
                return limit-1 ;
            }
            return --pollIndex ;
        }
    }

    class ArrayQueue<T>{
        private DoubleArrayList<T> container ;

        public ArrayQueue(int limit) {
            this.container = new DoubleArrayList<>(limit);
        }

        public void push(T ele){
            this.container.addFirst(ele);
        }

        public T poll(){
            return this.container.removeLast();
        }

        public int size(){
            return this.container.size();
        }

        public int limit(){
            return this.container.limit ;
        }
    }

    class ArrayStack<T>{
        private DoubleArrayList<T> container ;

        public ArrayStack(int limit) {
            this.container = new DoubleArrayList<>(limit);
        }

        public void push(T ele){
            this.container.addFirst(ele);
        }

        public T pop(){
            return this.container.removeFirst();
        }

        public int size(){
            return this.container.size();
        }

        public int limit(){
            return this.container.limit ;
        }
    }


    //TODO test
    public static void main(String[] args) {

    }
}
