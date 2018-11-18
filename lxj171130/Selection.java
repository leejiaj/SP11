package lxj171130;

import java.util.Random;

public class Selection<T extends Comparable<? super T>> {	
	public static Random random = new Random();
	
	//k largest elements of array
	public static<T extends Comparable<? super T>> void select(T[] arr, int k) {
		//result in arr[arr.length-k..arr.length-1]
		select(arr, 0, arr.length, k);
	}

	// Find kth largest element of arr[p..p+n-1]
	private static<T extends Comparable<? super T>> int select(T[] arr, int p, int n, int k) {
		if(n<14) {
			insertionSort(arr, p, p+n-1);
			return p + n - 1 - k;
		}
		else {
			// returns index
			int q = randomizedPartition(arr, p, p + n - 1);
			int left = q - p;
			int right = n - left - 1;
			
			if(right >= k) {
				return select(arr, q+1, right, k);
			}
			else if(right + 1 == k) {
				return q;
			}
			else {
				return select(arr, p, left, k - right - 1);
			}
		}
	}

	private static<T extends Comparable<? super T>> int randomizedPartition(T[] arr, int p, int r) {
		int i = random.nextInt(r + 1 - p) + p;
		T temp = arr[i];
		arr[i] = arr[r];
		arr[r] = temp;
		return partition(arr, p, r);
	}

	private static<T extends Comparable<? super T>> int partition(T[] arr, int p, int r) {
		T x = arr[r];
		int i = p - 1;
		//LI: arr[p..i] <= x, arr[i+1..j-1] > x, arr[j..r-1] is unprocessed,
		//arr[r] = x
		for(int j=p; j<=r-1; j++) {
			if(arr[j].compareTo(x) <= 0) {
				i++;
				T temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;				
			}
		}

		T temp = arr[i+1];
		arr[i+1] = arr[r];
		arr[r] = temp;
		
		return i+1;
	}

	private static<T extends Comparable<? super T>> void insertionSort(T[] arr, int p, int r) {
        for (int i=p+1;i<=r;i++) {
            T tmp = arr[i];
            int j = i-1;
            while (j>=p && tmp.compareTo(arr[j])<0) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = tmp;
        }
	}

}
