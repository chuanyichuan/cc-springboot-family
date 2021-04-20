package cc.kevinlu.ccspringbootwar;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        int i = 0;
        try {
            while (true) {
                i++;
                //                System.out.println("i = " + i);
                new HoldThread().start();
            }
        } catch (Exception e) {
        } catch (Error e) {
            long free = java.lang.Runtime.getRuntime().freeMemory();
            long total = java.lang.Runtime.getRuntime().totalMemory();
            StringBuffer buf = new StringBuffer();
            buf.append("[Mem: used ").append((total - free) >> 20).append("M free ").append(free >> 20)
                    .append("M total ").append(total >> 20).append("M]");
            System.out.println(buf.toString());
        }
    }

    static class HoldThread extends Thread {
        CountDownLatch cdl = new CountDownLatch(1);

        public HoldThread() {
            this.setDaemon(true);
        }

        @Override
        public void run() {
            try {
                byte[] b = new byte[1024 * 1024 * 2];
                cdl.await();
            } catch (InterruptedException e) {
            }
        }
    }
}
