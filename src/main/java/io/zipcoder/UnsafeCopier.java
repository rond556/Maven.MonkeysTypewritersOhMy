package io.zipcoder;
import static java.lang.Thread.sleep;


/**
 * Modify the run function so that the monkeys each grab the next word and write it to the copy.
 */
public class UnsafeCopier extends Copier {

    public UnsafeCopier(String toCopy) {
        super(toCopy);
    }

    public void run() {

        try {
            sleep(500);
            while (this.stringIterator.hasNext()) {
                StringBuilder sb = new StringBuilder();
//                System.out.println("***** UNSAFE " + Thread.currentThread().getName() + "*****\n");
                sb.append(this.stringIterator.next()).append(" ");
                copied += sb.toString();
//                System.out.println(copied);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
