package basic;

public class ConcurrentSolution {

    private volatile static int flag = 1 ;

    public static void main(String[] args) {

        final Object obj = new Object() ;

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    synchronized (obj) {
                        if (flag == 1) {
                            System.out.print(1);
                            flag = 2;
                            obj.notify();
                        } else {
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (obj) {
                        if (flag == 2) {
                            System.out.print(2);
                            flag = 1;
                            obj.notify();
                        } else {
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        threadA.start();
        threadB.start();

    }
}
