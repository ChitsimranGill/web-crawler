package com.netinstructions.crawler;
import java.util.*;


public class heap_Sort 
{
       private static int heapSize;
       
       /** HeapSort algorithm to sort arrays
        * Sorts array in ascending order.
        * 
        * @param A - Array that needs to be sorted.
        */
       public static void heapSort(int A[])
       {
    	      BuildMaxHeap(A);
    	      for (int i = heapSize; i>0; i--)
    	      {
    	    	    swap(A,0,i);
    	    	    heapSize = heapSize - 1;
    	    	    MaxHeapify(A,0);
    	      }
       }
       
       /** Converts a unordered array into max heap
        * 
        * @param A - Array that needs to converted into max heap
        */
       public static void BuildMaxHeap(int A[])
       {
    	    heapSize = A.length-1;
    	    for (int i = heapSize/2; i>=0; i-- )
    	    {
    	    	 MaxHeapify(A,i);
    	    }
       }
       
       /** It makes sure that the max heap property is maintained.
        * 
        * @param A - Array whose element needs to be max heapified.
        * @param i - index of the element.
        */
       public static void MaxHeapify(int A[], int i)
       {
    	      int L = 2*i;
    	      int R = 2*i+1;
    	      int largest = i;
    	      if (L<= heapSize && A[L]>A[i])
    	      {
    	    	    largest = L;
    	      }
    	      if (R<=heapSize && A[R]>A[largest])
    	      {
    	    	    largest = R;
    	      }
    	      
    	      if (largest != i)
    	      {
    	    	   swap(A,i,largest);
    	    	   MaxHeapify(A,largest);
    	      }
       }
       
       /** Swaps two elements of an array
        * 
        * @param A - Array whose elements need to be swapped
        * @param i - First element who needs to be swapped
        * @param j - Second element who needs to be swapped
        */
       public static void swap(int A[], int i, int j)
       {
    	     int temp = A[i];
    	     A[i] = A[j];
    	     A[j] = temp;
       }
       
       /**
        * Extracts the max value from an heap
        * Gets the highest score
        * 
        * @param A - Array whose max value needs to be extracted
        * @return - returns the max value
        */
       public static int HeapExtractMax(int A[])
       {
    	     if (heapSize<0)
    	     {
    	    	   System.out.println("heap underflow");
    	     }
    	     int max = A[0];
    	     A[0] = A[heapSize];
    	     heapSize = heapSize-1;
    	     MaxHeapify(A,0);
    	     return max;
    	     
       }
       /**
        * Returns the max value of an heap
        * 
        * @param A - Array whose max value needs to be returned
        */
       public static int HeapMaximum(int A[])
       {
    	     return A[0];
       }
       
       /**
        *  Increases the value of an element of an heap
        *  
        * @param A - Array whose element needs to be changed
        * @param i - Index of the element
        * @param key - new value of element
        */
       public static void HeapIncreaseKey(int A[],int i,int key)
       {
    	     if (key<A[i])
    	     {
    	    	  System.out.println("new key is smaller than current key");
    	     }
    	     
    	     A[i] = key;
    	     int Parent = (int) Math.floor(i/2);
    	     if (i>0 && Parent< A[i])
    	     {
    	    	   swap(A,i,Parent);
    	    	   i=Parent;
    	     }
       }
       
       /**
        * Inserts a new element in the heap.
        * 
        * @param A - Array in which the element needs to be added.
        * @param key - Value of the element that needs to be added
        */
       public static void MaxHeapInsert(int A[], int key)
       {
    	     heapSize = heapSize+1;
    	     HeapIncreaseKey(A,heapSize,key);
       }
       
       
	
	
	
}
