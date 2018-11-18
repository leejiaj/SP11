/**
 * @author Leejia James
 * @author Vishwanath D C
 *
 * Short Project 11: Divide and conquer, Enumeration
 * Implemented the expected O(n) algorithm for the k largest elements (select)
 * of an array, and compared its performance with the algorithm using
 * priority queues that we designed for the same problem on streams.
 * Used k=n/2 (median), and tried large values of n: 16M, 32M, 64M, 128M, 256M.
 *
 * Ver 1.0: 2018/11/17
 */

package lxj171130;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Driver {
    public static Random random = new Random();
    public static int numTrials = 1;
    public static void main(String[] args) throws Exception {
	int n = 100;  int choice = 1;//1 + random.nextInt(2);
	int k = n / 2;
	if(args.length > 0) { n = Integer.parseInt(args[0]); }
	if(args.length > 1) { choice = Integer.parseInt(args[1]); }
        Integer[] arr = new Integer[n];
        for(int i=0; i<n; i++) {
	    arr[i] = i;
	}
	Shuffle.shuffle(arr);
	Shuffle.printArray(arr, "");
	List<Integer> list = Arrays.asList(arr);
	Timer timer = new Timer();
	switch(choice) {
	case 1: // O(n) algorithm for finding k largest elements
		Selection.select(arr, k);
//		for(int i=arr.length-k; i<=arr.length-1; i++) {
//			System.out.print(arr[i]+ " ");
//		}
//		System.out.println();
	    break;
	case 2: // Algorithm using priority queues to find k largest elements of stream
		BinaryHeap<Integer> bh = BinaryHeap.kLargest(list.iterator(), k);
		//bh.printBinaryHeap();
	    break;
	}
	timer.end();

	System.out.println("Choice: " + choice + "\n" + timer);
    }
	

/** Timer class for roughly calculating running time of programs
     *  @author rbk
     *  Usage:  Timer timer = new Timer();
     *          timer.start();
     *          timer.end();
     *          System.out.println(timer);  // output statistics
     */

    public static class Timer {
        long startTime, endTime, elapsedTime, memAvailable, memUsed;
        boolean ready;

        public Timer() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public void start() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public Timer end() {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            memAvailable = Runtime.getRuntime().totalMemory();
            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
            ready = true;
            return this;
        }

        public long duration() { if(!ready) { end(); }  return elapsedTime; }

        public long memory()   { if(!ready) { end(); }  return memUsed; }

	public void scale(int num) { elapsedTime /= num; }
	
        public String toString() {
            if(!ready) { end(); }
            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
        }
    }
    
    /** @author rbk : based on algorithm described in a book
     */


    /* Shuffle the elements of an array arr[from..to] randomly */
    public static class Shuffle {
	
	public static void shuffle(int[] arr) {
	    shuffle(arr, 0, arr.length-1);
	}

	public static<T> void shuffle(T[] arr) {
	    shuffle(arr, 0, arr.length-1);
	}

	public static void shuffle(int[] arr, int from, int to) {
	    int n = to - from  + 1;
	    for(int i=1; i<n; i++) {
		int j = random.nextInt(i);
		swap(arr, i+from, j+from);
	    }
	}

	public static<T> void shuffle(T[] arr, int from, int to) {
	    int n = to - from  + 1;
	    Random random = new Random();
	    for(int i=1; i<n; i++) {
		int j = random.nextInt(i);
		swap(arr, i+from, j+from);
	    }
	}

	static void swap(int[] arr, int x, int y) {
	    int tmp = arr[x];
	    arr[x] = arr[y];
	    arr[y] = tmp;
	}
	
	static<T> void swap(T[] arr, int x, int y) {
	    T tmp = arr[x];
	    arr[x] = arr[y];
	    arr[y] = tmp;
	}

	public static<T> void printArray(T[] arr, String message) {
	    printArray(arr, 0, arr.length-1, message);
	}

	public static<T> void printArray(T[] arr, int from, int to, String message) {
	    System.out.print(message);
	    for(int i=from; i<=to; i++) {
		System.out.print(" " + arr[i]);
	    }
	    System.out.println();
	}
    }

}

