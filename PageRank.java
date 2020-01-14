package com.netinstructions.crawler;
import java.util.*;

public class PageRank 
{
	
	heap_Sort sort = new heap_Sort(); 
	/**
	 * Uses HeapExtractMax method to get top 10 scores of urls
	 * @param A - Array from which top ten valued need to be extracted from
	 * @param B - Array in which the top ten values are added
	 */
	public void getsTopTenPriority(int A[], int B[])
	{
		for (int i=0; i<10; i++)
		{
			B[i] = sort.HeapExtractMax(A); // using heapExtractMax to get top ten scores
		}
   	}
	
	/**
	 * Uses HeapExtractMax method to get most number of time search was done
	 * @param A - Array from which top ten valued need to be extracted from
	 * @param B - Array in which the top ten values are added
	 */
	public void getsTopTenPriorityKey(int A[], int B[])
	{
		for (int i=0; i<A.length; i++)
		{
			B[i] = sort.HeapExtractMax(A); // using heapExtractMax to get number of times most searched words were searched
		}
   	}
	
	/**
	 * Prints unsorted url
	 * 
	 * @param A - arrayList in which url are stored
	 */
	public void initalPrint(ArrayList A)
	{
		System.out.println("Unsorted URLs: ");
		for (int i = 0; i<30; i++)
		{
			System.out.println( (i+1) +") "+ A.get(i));
		}
	}
	
	/**
	 * Replicates an array
	 * Used before using HeapMaxExtract as the array gets changed after using it
	 * @param A - Array which needs to be replicated
	 * @param B - Array in which replicated values need to be stored in
	 */
	public void replicateTotal(int A[], int B[])
	{
		for(int i = 0; i<A.length; i++)
		{
			B[i] = A[i]; // replicating
		}		
	}
	
	/** Gets the index of top ten scores from the inital array
	 * 
	 * @param A - Array of top ten score
	 * @param B - Initial Array (duplicate version)
	 * @param C - Array in which index of top ten scores need to be stored in.
	 */
	public void getIndex(int A[], int B[], int C[])
	{
		int k = 0;
		Set<Integer> check = new HashSet<Integer>(); // set to make sure that index of any key value is not searched twice
		for (int j = 0; j<A.length; j++)
		{
		int key = A[j];
		if (check.contains(key) == false) // if check does not contain key then find its index
		{
			check.add(key);
		for (int i = 0; i<B.length; i++)
		{
			if(key == B[i]  && i<B.length)
			{
				C[k] = i; // adding index to an array
				k++;
			}
			}
		}
		}
	}
		
		
	
	/**
	 * Allows the user to change the value of each individual condition of the score
	 * 
	 * @param A - Array containing the score
	 */
	public void askingUser(int A[])
	{
		heap_Sort sort = new heap_Sort();
		Scanner in = new Scanner(System.in);
			System.out.println("Enter index number of URl: ");
		do
		{
			int index = in.nextInt();
			System.out.println("Enter score for money paid: ");
			int p = in.nextInt();
			System.out.println("Enter score for keywords: ");
			int k = in.nextInt();
			System.out.println("Enter score for age: ");
			int a = in.nextInt();
			System.out.println("Enter score for link: ");
			int l = in.nextInt();
			int total = p+k+a+l;
			sort.HeapIncreaseKey(A, index, total); // Uses HeapIncreaseKey to increase score
			System.out.println("Enter any alphabet to quit or enter next url index: ");
			 
		} while (in.hasNextInt());
		
		}
	
	/**
	 * Counts the number times a word was searched
	 * 
	 * @param A - ArrayList containing the words that were searched
	 * @param C - Array in which number of times a word was searched is stored
	 */
	public void count(ArrayList<String> A, int C[])
	{
		int count = 0;
		int i = 0;
		Set<String> check = new HashSet<String>(A); // makes sure number of searches of unique keywords are counted
		for (String s: check)
		{
			for (int j = 0; j<A.size(); j++)
			{
				if (s.equals(A.get(j)))
				{
					count++;
				}
			}
			C[i] = count;
			i++;
			count = 0; // adding the number of times a keyword was counted to an array
		}
	}
	}


