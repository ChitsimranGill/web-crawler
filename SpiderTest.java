package com.netinstructions.crawler;
import java.util.*;

public class SpiderTest
{
	/**
	 * This is our test. It creates a spider (which creates spider legs) and crawls the web.
	 * And performs the two major objectives
	 * 
	 * @param args
	 *            - not used
	 */
	public static void main(String[] args)
	{


		Spider spider = new Spider();
		SpiderLeg leg = new SpiderLeg();
		Scanner in = new Scanner(System.in);

		System.out.println("Enter url to search"); // Performing the main two objectives
		do {
			String s = in.nextLine();
			System.out.println("Enter word to search: ");
			String s1 = in.nextLine();
			spider.search(s, s1);
			spider.foundAt.clear();
			spider.pagesToVisit.clear();
			spider.pagesVisited.clear();
			System.out.println("Enter any number to quit or enter next url: ");
		} while (!(in.hasNextInt()));
		spider.printUnique();

		heap_Sort hp = new heap_Sort();
		int A[] = {2,5,10,17,8,9,10,90,65,78};
		System.out.println("Input Array");
		System.out.println(Arrays.toString(A));
		
		System.out.println("Testing HeapMaximum"); // Testing HeapMaximum
		System.out.println("Using Heap maximum");
		hp.HeapMaximum(A);
		System.out.println(hp.HeapMaximum(A));
		
		System.out.println("Testing HeapIncreaseKey");// Testing HeapIncreaseKey
		System.out.println("Increasing the value of element at index 2 to 45");
		hp.HeapIncreaseKey(A, 3, 45);
		System.out.println(Arrays.toString(A));
		
		System.out.println("Testing HeapSort");// Testing HeapSort
		System.out.println("Sorting array using heap sort");
		hp.heapSort(A);
		System.out.println("Printing sorted array");
		System.out.println(Arrays.toString(A));
		
		System.out.println("Testing MaxHeapInsert");// Testing MaxHeapInsert
		System.out.println("Using Max Heap Insert to insert 100");
		hp.MaxHeapInsert(A, 100);
		System.out.println("Sorting array againg after inserting");
		hp.heapSort(A);
		System.out.println("Printing sorted array after inserting");
		System.out.println(Arrays.toString(A));
		
		System.out.println("Testing BuildMaxHeap");//Testing BuildMaxHeap
		System.out.println("Using BuildMaxHeap");
		hp.BuildMaxHeap(A);
		System.out.println("Printing max heap");// Printing max heap
		System.out.println(Arrays.toString(A));
		
		System.out.println("Testing HeapExtractMax");// Testing HeapExtractMax
		for (int i = 0; i<5; i++)
		{
			System.out.println("Extracted value: " + hp.HeapExtractMax(A));
		}
		System.out.println("Testing Finished");// finished

	}
}
