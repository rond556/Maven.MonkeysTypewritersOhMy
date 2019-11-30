package io.zipcoder;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Make this extend the Copier like `UnsafeCopier`, except use locks to make sure that the actual intro gets printed
 * correctly every time.  Make the run method thread safe.
 */
public class SafeCopier extends Copier{
    public SafeCopier(String toCopy) {
        super(toCopy);
    }

    public void run() {
        Random random = new Random();
        Integer randomSleeper = random.nextInt((100 - 50)+1) +50;
        try {
            while (this.stringIterator.hasNext()) {
                synchronized (stringIterator) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(this.stringIterator.next()).append(" ");
                    sleep(randomSleeper);
                    System.out.println("*****" + Thread.currentThread().getName() + "*****");
                    copied += sb.toString();
                }
            }
        } catch (InterruptedException e) {
        }
        copied = copied.trim();
    }
 }