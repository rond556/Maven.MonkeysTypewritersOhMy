package io.zipcoder;
import java.util.Random;

import static java.lang.Thread.sleep;


/**
 * Modify the run function so that the monkeys each grab the next word and write it to the copy.
 */
public class UnsafeCopier extends Copier {

    public UnsafeCopier(String toCopy) {
        super(toCopy);
    }

    public void run() {
        Random random = new Random();
        Integer randomSleeper = random.nextInt((100 - 50)+1) +50;
        try {
            while (this.stringIterator.hasNext()) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.stringIterator.next()).append(" ");
                sleep(randomSleeper);
                System.out.println("*****" + Thread.currentThread().getName() + "*****");
                copied += sb.toString();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        copied = copied.trim();
    }
}
