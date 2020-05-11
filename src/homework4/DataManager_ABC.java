package homework4;

public class DataManager_ABC {
    private final Object monitor = new Object();
    private static volatile String currentOperation = "prepare";

    public void sendData() {
        synchronized (monitor) {
            try {
                while (!currentOperation.equals("send")) {
                    monitor.wait();
                }
                System.out.print("B");
                currentOperation = "process";
                monitor.notifyAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void prepareData() {
        synchronized (monitor) {
            try {
                while (!currentOperation.equals("prepare")) {
                    monitor.wait();
                }
                System.out.print("A");
                currentOperation = "send";
                monitor.notifyAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void processData() {
        synchronized (monitor) {
            try {
                while (!currentOperation.equals("process")) {
                    monitor.wait();
                }
                System.out.print("C");
                currentOperation = "prepare";
                monitor.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DataManager_ABC dm = new DataManager_ABC();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    dm.prepareData();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    dm.sendData();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    dm.processData();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

    }

}
