package com.netinstructions.crawler;

import java.util.*;

public class Spider
{
  private static final int MAX_PAGES_TO_SEARCH = 30;
  public Set<String> pagesVisited = new HashSet<String>();		// set of urls that have been visited
  public Set<String> counter = new HashSet<String>(); 			// counter for counting number of times search was done
  public List<String> pagesToVisit = new LinkedList<String>(); 	// urls to visit
  public ArrayList foundAt = new ArrayList<String>(); 			// list of pages at which the word was found
  
  private int initialScore[] = new int[30];                      // array that contains initial score
  private int replicate[] = new int[30];							// array which is used to replicate initial score
  private int revised[] = new int[30];							// array which is used to store revided score(after changing score of url)
  private int topTen[] = new int[10];                            // contains top ten scores
  private int index[] = new int[30];								// contains the index of top score urls
  
  private int Paid;												// Amount paid factor of priority queue
  private int Key;												// Number of keywords and place, factor of priority queue
  private int Age;												// Age of page, factor of priority queue
  private int Link;												// Number of pages that link to it, factor of priority queue
  private int Total;												// array of total score of a url
  private int count[];											// array of number of times a keyword was searched
  private int replicateKey[];									// Used for replicating the array count
  private int topTenKey[];										// Contains the top ten times a word was searched
  private int indexKey[];										// index of top ten searched keywords
  private ArrayList<String> keywords = new ArrayList<String>();  // contains all the keywords that were searched

  /**
   * Our main launching point for the Spider's functionality. Internally it creates spider legs
   * that make an HTTP request and parse the response (the web page). 
   * It also implements other methods of heap that are required for priority queue and prints the top ten url.
   * 
   * 
   * @param url
   *            - The starting point of the spider
   * @param searchWord
   *            - The word or string that you are searching for
   *            
   */
  public void search(String url, String searchWord)
  { 
	  counter.add(searchWord);
      keywords.add(searchWord);
      while( this.foundAt.size() < MAX_PAGES_TO_SEARCH)
      {
          String currentUrl;
          SpiderLeg leg = new SpiderLeg();
          if(this.pagesToVisit.isEmpty())
          {
              currentUrl = url;
              this.pagesVisited.add(url);
          }
          else
          {
              currentUrl = this.nextUrl();// Lots of stuff happening here. Look at the crawl method in
                                          // SpiderLeg
          }
          leg.crawl(currentUrl);
          boolean success = leg.searchForWord(searchWord);
          if(success)
          {
              this.foundAt.add(currentUrl);          
          }
          this.pagesToVisit.addAll(leg.getLinks());
      }
      
      System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)");
      heap_Sort hpsort = new heap_Sort();
      PageRank rank = new PageRank();
      
      priorityGen(initialScore); // gets random score for 30 urls
      
      rank.initalPrint(foundAt); 					// prints unsorted urls
      rank.replicateTotal(initialScore, replicate);  // replicates array
      hpsort.BuildMaxHeap(initialScore);			    // build max heap
      rank.getsTopTenPriority(initialScore, topTen); // gets top ten score
      rank.getIndex(topTen, replicate, index); 		// gets index of score
      
      System.out.println("\nTop ten search results and their score ");
      finalPrint(foundAt, index, topTen);			// prints sorted array
  
        Scanner in = new Scanner(System.in);
		System.out.println("Do you want to change score? (Yes or No)"); // asks user if he wants to change score of a url
		String s  = in.nextLine();
		if (s.equals("Yes"))
		{   
    	       rank.askingUser(replicate);              // changes score 
           rank.replicateTotal(replicate, revised); 
           hpsort.BuildMaxHeap(replicate);				// build max heap
           rank.getsTopTenPriority(replicate, topTen);	// gets top ten priority scores
           rank.getIndex(topTen, revised, index);        // gets index of score
           System.out.println("\nTop ten search results and their score (Revised) ");
           finalPrint(foundAt, index, topTen);			// prints final list after changing score of url
		}
		else
		{
			System.out.println("finish search");
		}
     }


  /**
   * Returns the next URL to visit (in the order that they were found). We also do a check to make
   * sure this method doesn't return a URL that has already been visited.
   * 
   * @return
   */
  private String nextUrl()
  {
      String nextUrl;
      do
      {
          nextUrl = this.pagesToVisit.remove(0);
      } while(this.pagesVisited.contains(nextUrl)); // checks if the page has already been visited or not
      this.pagesVisited.add(nextUrl);
      return nextUrl;
  }
  
  
  /** Generates 30 random values for 4 condition of PageRank and enters the total score in an array.
   * 
   * @param B - Array in which total score of each url are saved.
   */
  public void priorityGen(int B[])
  {
	  Random rand = new Random();
	  
	  for (int i = 0; i<30; i++)
	  {
	  Paid = rand.nextInt(100)+1; 
	  Link = rand.nextInt(100)+1;
	  Age = rand.nextInt(100)+1;
	  Key = rand.nextInt(100)+1;
	  Total = Paid+Link+Age+Key;
	  B[i] = Total; // adds random score to the array
	  }
	  
  }
  
  /** Prints the top ten urls and their score
   * It is used by search method to print top ten urls
   * @param A - ArrayList which contains the urls
   * @param B - Contains the index of top ten score
   * @param C - Contains the scores of top ten url
   */
  public void finalPrint(ArrayList A, int B[], int C[])
  {
	  for (int i = 0; i<10; i++)
	  {
		  int j = B[i];
		  int k = C[i];
		  System.out.println((i+1)+") "+ A.get(j) + ", Score: "+ k );
	  }
	  
  }
  
  /** Prints the top ten searched words and the times they were searched
   * It is used by prinitUnique method to print top ten most searched words
   * @param A - ArrayList which contains the search words
   * @param B - Contains the index of top ten most searched words
   * @param C - Contains the number of times the words were searched
   */
  public void finalPrintKey(ArrayList A, int B[], int C[])
  {
	  Set<String> hash = new HashSet<String>(A);
	  ArrayList<String> list = new ArrayList<String>(hash);
	  if (C.length<10) // condition for if less than 10 unique keywords were searched
	  {
	  for (int i = 0; i<C.length; i++) 
	  {
		  int j = B[i];
		  int k = C[i];
		  System.out.println((i+1)+") "+ list.get(j) + ", Number of times it was searched:  "+ k );
	  }
	  }
	  else
	  {
		  for (int i = 0; i<10; i++)
		  {
			  int j = B[i];
			  int k = C[i];
			  System.out.println((i+1)+") "+ list.get(j) + ", Number of times it was searched: "+ k );
		  }
	  }
	  
  }
  /**
   *  Gets the top ten unique searched words and prints them according to the number of times they were searched.
   */
  public void printUnique()
  {
  	  count = new int[counter.size()];   //intializing
  	  topTenKey = new int[count.length];//intializing
  	  replicateKey = new int[counter.size()];//intializing
  	  
  	  PageRank rank = new PageRank();
  	  heap_Sort hpsort = new heap_Sort();
  	  
  	  rank.count(keywords, count); 																			// counitng number of time keywords were searched
  	  indexKey = new int[count.length];
        rank.replicateTotal(count, replicateKey);																// replicating count
        hpsort.BuildMaxHeap(count);																			// building max heap
        rank.getsTopTenPriorityKey(count, topTenKey);															//getting number of times most searched words were searched
        rank.getIndex(topTenKey, replicateKey, indexKey);														// gets index of most searched words
        System.out.println("\nTop 10 most searched keywords and number of times they were searched");
        finalPrintKey(keywords, indexKey, topTenKey);															// prints most searched words with the number of times they were searched
  }
}