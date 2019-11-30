package io.zipcoder;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class MonkeyTypewriter {
    public static void main(String[] args) throws InterruptedException {

        String introduction = "It was the best of times,\n" +
                "it was the worst of times,\n" +
                "it was the age of wisdom,\n" +
                "it was the age of foolishness,\n" +
                "it was the epoch of belief,\n" +
                "it was the epoch of incredulity,\n" +
                "it was the season of Light,\n" +
                "it was the season of Darkness,\n" +
                "it was the spring of hope,\n" +
                "it was the winter of despair,\n" +
                "we had everything before us,\n" +
                "we had nothing before us,\n" +
                "we were all going direct to Heaven,\n" +
                "we were all going direct the other way--\n" +
                "in short, the period was so far like the present period, that some of\n" +
                "its noisiest authorities insisted on its being received, for good or for\n" +
                "evil, in the superlative degree of comparison only.";

        // Do all of the Monkey / Thread building here
        // For each Copier(one safe and one unsafe), create and start 5 monkeys copying the introduction to
        // A Tale Of Two Cities.
        UnsafeCopier unsafeCopier = new UnsafeCopier(introduction);
        SafeCopier safeCopier = new SafeCopier(introduction);
        List<Thread> unsafeThreadList = new ArrayList<>();
        List<Thread> safeThreadList = new ArrayList<>();


        final Integer numberOfMonkeys = 5;

        /*for (int i = 0; i < numberOfMonkeys; i++) {
            unsafeThreadList.add(new Thread(unsafeCopier));
        }
        for (Thread t : unsafeThreadList) {
            t.start();
        }*/

        for (int i = 0; i < numberOfMonkeys; i++) {
            safeThreadList.add(new Thread(safeCopier));
        }
        for (Thread t : safeThreadList) {
            t.start();
        }

        try{
            for(Thread t : safeThreadList){
                t.join();
            }
        } catch ( InterruptedException e){
            System.out.println("MAIN INTERRUPTED");
        }


        // This wait is here because main is still a thread and we want the main method to print the finished copies
        // after enough time has passed.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("MAIN INTERRUPTED");
        }
        System.out.println(safeCopier.copied);
        if(pageMatcher(safeCopier.copied,introduction)) {
            System.out.println("It's a match!");
        } else {
            System.out.println("Not a match...");
        }

        // Print out the copied versions here.
    }

        public static Boolean pageMatcher(String copy, String original){
            return copy.equals(original);
        }
    }
