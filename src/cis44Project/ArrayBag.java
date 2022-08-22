package cis44Project;

import java.util.Arrays;

/*
 * DIFFERENT METHODS COMPARED TO ArrayBag.java
 * mergeSort()
 * quickSort()
 * 
 * retrieve()
 * 
 * removeEntry(int givenIndex)
 * 
 */

public final class ArrayBag<T extends Comparable<? super T>> implements BagInterface<T>
{
	private final T[] bag; 
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;

	/** Creates an empty bag whose initial capacity is 25. */
	public ArrayBag() 
	{
		//sets number of bug
		this(DEFAULT_CAPACITY);
	} // end default constructor

	/** Creates an empty bag having a given capacity.
	    @param desiredCapacity  The integer capacity desired. */
	//constructor
	public ArrayBag(int desiredCapacity)
	{
      if (desiredCapacity <= MAX_CAPACITY)
      {
         // The cast is safe because the new array contains null entries
         @SuppressWarnings("unchecked")
         T[] tempBag = (T[])new Comparable[desiredCapacity]; // Unchecked cast
         bag = tempBag;
         numberOfEntries = 0;
         initialized = true;
      }
      else
         throw new IllegalStateException("Attempt to create a bag " +
                                         "whose capacity exceeds " +
                                         "allowed maximum.");
	} // end constructor
   
	/** Adds a new entry to this bag.
       @param newEntry  The object to be added as a new entry.
       @return  True if the addition is successful, or false if not. */
	//exam will ask to make add method
	public boolean add(T newEntry)
	{
		checkInitialization();
      boolean result = true;
      if (isArrayFull())
      {
         result = false;
         
      }
      else
      {  // Assertion: result is true here
         bag[numberOfEntries] = newEntry;
         numberOfEntries++;
         
      } // end if
      
      return result;
	} // end add
   
	/** Retrieves all entries that are in this bag.
       @return  A newly allocated array of all the entries in this bag. */
	public T[] toArray()
	{
		checkInitialization();
      
      // The cast is safe because the new array contains null entries.
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast

      for (int index = 0; index < numberOfEntries; index++) 
      {
         result[index] = bag[index];
      } // end for
      
      return result;
      // Note: The body of this method could consist of one return statement,
      // if you call Arrays.copyOf
	} // end toArray

	/** Sees whether this bag is empty.
       @return  True if this bag is empty, or false if not. */
	public boolean isEmpty()
	{
      return numberOfEntries == 0;
	} // end isEmpty

	/** Gets the current number of entries in this bag.
       @return  The integer number of entries currently in this bag. */
	public int getCurrentSize()
	{
      return numberOfEntries;
	} // end getCurrentSize

	/** Counts the number of times a given entry appears in this bag.
       @param anEntry  The entry to be counted.
       @return  The number of times anEntry appears in this ba. */
	public int getFrequencyOf(T anEntry)
	{
		checkInitialization();
      int counter = 0;

      for (int index = 0; index < numberOfEntries; index++) 
      {
         if (anEntry.equals(bag[index]))
         {
            counter++;
         } // end if
      } // end for

      return counter;
	} // end getFrequencyOf

	/** Tests whether this bag contains a given entry.
       @param anEntry  The entry to locate.
       @return  True if this bag contains anEntry, or false otherwise. */
   public boolean contains(T anEntry)
	{
		checkInitialization();
      return getIndexOf(anEntry) > -1; // or >= 0
	} // end contains
   
	/** Removes all entries from this bag. */
	public void clear() 
	{
      while (!isEmpty())
         remove();
	} // end clear
	
	/** Removes one unspecified entry from this bag, if possible.
       @return  Either the removed entry, if the removal
                was successful, or null. */
	public T remove()
	{
		checkInitialization();
      T result = removeEntry(numberOfEntries - 1);
      return result;
	} // end remove
	
	/** Removes one occurrence of a given entry from this bag.
       @param anEntry  The entry to be removed.
       @return  True if the removal was successful, or false if not. */
	public boolean remove(T anEntry)
	{
		checkInitialization();
      int index = getIndexOf(anEntry);
      T result = removeEntry(index);         
      return anEntry.equals(result);
	} // end remove   
	
   // Returns true if the array bag is full, or false if not.
	private boolean isArrayFull()
	{
		return numberOfEntries >= bag.length;
	} // end isArrayFull
   
 	// Locates a given entry within the array bag.
	// Returns the index of the entry, if located,
	// or -1 otherwise.
   // Precondition: checkInitialization has been called.
	private int getIndexOf(T anEntry)
	{
		int where = -1;
		boolean found = false;      
		int index = 0;
      
      while (!found && (index < numberOfEntries))
		{
			if (anEntry.equals(bag[index]))
			{
				found = true;
				where = index;
			} // end if
         index++;
		} // end while
      
      // Assertion: If where > -1, anEntry is in the array bag, and it
      // equals bag[where]; otherwise, anEntry is not in the array.
      
		return where;
	} // end getIndexOf
   
   // Removes and returns the entry at a given index within the array.
   // If no such entry exists, returns null.
   // Precondition: 0 <= givenIndex < numberOfEntries.
   // Precondition: checkInitialization has been called.
	public T removeEntry(int givenIndex)
	{
		T result = null;
      
		if (!isEmpty() && (givenIndex >= 0))
		{
         result = bag[givenIndex];          // Entry to remove
         int lastIndex = numberOfEntries - 1;
         bag[givenIndex] = bag[lastIndex];  // Replace entry to remove with last entry
         bag[lastIndex] = null;             // Remove reference to last entry
         numberOfEntries--;
		} // end if
      
      return result;
	} // end removeEntry
   
	public void display()
	{
		for(int i=0; i< numberOfEntries; i++)
			System.out.print(bag[i] + "  ");
		System.out.println();
	}
	
	//returns the element based on the int index in the parameter
	public T retrieve(int index)
	{
		if(this.isEmpty())
		{
			return null;
		} else
		{
	
		if(index < 0)
			return bag[0];
		if(index > this.getCurrentSize())
			return bag[(this.getCurrentSize() - 1)];
		
		return bag[index];
		}
	}
	
	public String toString()
	{
		String str = "[";
		for(int i = 0; i < this.getCurrentSize(); i++)
		{
			if(i != 0)
				str += ", ";
			str += bag[i];
		}
		str += "]";
		return str;
	}
	
	//insertion sort
	private static<T extends Comparable <? super T>>
	void insertionSort(T[] a, int n)
	{
		insertionSort(a, 0, n - 1);
	}
	
	private static <T extends Comparable<? super T>>
	void insertionSort(T[] a, int first, int last)
	{
		if(first < last)
		{
			insertionSort(a, first, last - 1);
			insertInOrder(a[last], a, first, last - 1);
		}
	}
	
	private static<T extends Comparable<? super T>>
	void insertInOrder(T element, T[] a, int begin, int end)
	{
		if (element.compareTo(a[end]) >= 0)
			a[end + 1] = element;
		else if (begin < end)
		{
			a[end + 1] = a[end];
			insertInOrder(element, a, begin, end - 1);
		}
		else
		{
			a[end + 1] = a[end];
			a[end] = element;
		}
	}
	
	//merge sort
	public void mergeSort()
	{
		mergeSort(bag, this.getCurrentSize());
	}
	
	//helper methods for merge sort
	private static <T extends Comparable<? super T>>
		void mergeSort(T[]a, int n)
	{
		mergeSort(a,0,n-1);
	}
	
	private static <T extends Comparable<? super T>>
		void mergeSort(T[]a, int first, int last) 
	{
		//@SuppressWarning("unchecked")
		T[] tempArray = (T[]) new Comparable<?>[a.length];
		mergeSort(a, tempArray, first, last);
	}
	private static <T extends Comparable<? super T>>
		void mergeSort(T[] a, T[] tempArray, int first, int last) 
	{
		if(first<last) {
			int mid = first +(last -first)/2;
			mergeSort(a,first,mid);
			mergeSort(a, mid +1, last);
			
				if(a[mid].compareTo(a[mid+1])>0)
					merge(a, tempArray, first, mid, last);
		}
	}
	private static <T extends Comparable<? super T>>
		void merge(T[]a, T[] tempArray, int first, int mid, int last) 
	{
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;
		
		int index = beginHalf1;
		for(;(beginHalf1<=endHalf1)&&(beginHalf2<=endHalf2); index++) {
			if(a[beginHalf1].compareTo(a[beginHalf2])<0) {
				tempArray[index]=a[beginHalf1];
				beginHalf1++;
			}else {
				tempArray[index] = a[beginHalf2];
				beginHalf2++;
			}
		}
		for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
			tempArray[index]= a[beginHalf1];
		for(; beginHalf2 <= endHalf2; beginHalf2++, index++)
			tempArray[index]=a[beginHalf2];
		for(index=first;index<=last;index++)
			a[index]=tempArray[index];
	}	
		
	//quick sort
	public void quickSort()
	{
		quickSort(bag, this.getCurrentSize());
	}
	
	//helper methods for merge sort
	private static<T extends Comparable<? super T>>
		void quickSort(T[] array, int n)
	{
		quickSort(array, 0, n-1);
	}
	private static <T extends Comparable<? super T>>
		void quickSort(T[]a, int first, int last)
		{
			final int MIN_SIZE = 3;
			if(last-first+ 1< MIN_SIZE) {
				insertionSort(a, first, last);
			}else {
				int pivotIndex = partition(a, first, last);
				quickSort(a, first, pivotIndex-1);
				quickSort(a, pivotIndex + 1, last);
			}
		}
	private static <T extends Comparable<? super T>>
		int partition(T[]a, int first, int last) {
		
		int mid = first + (last - first)/2;
		sortFirstMiddleLast(a, first, mid, last);
		swap(a,mid,last-1);
		int pivotIndex=last-1;
		T pivotValue = a[pivotIndex];
		int indexFromLeft = first + 1;
		int indexFromRight = last - 2;
		boolean done = false;
		while(!done) {
			while(a[indexFromLeft].compareTo(pivotValue)<0)
				indexFromLeft++;
			while(a[indexFromRight].compareTo(pivotValue)>0)
				indexFromRight--;
			assert a[indexFromLeft].compareTo(pivotValue)>= 0 &&
				   a[indexFromRight].compareTo(pivotValue)<=0;
			if(indexFromLeft<indexFromRight) {
				swap(a, indexFromLeft, indexFromRight);
				indexFromLeft++;
				indexFromRight--;
			}
			else
				done= true;
			}
		swap(a,pivotIndex, indexFromLeft);
		pivotIndex = indexFromLeft;
		return pivotIndex;
		}
	private static <T extends Comparable<? super T>>
		void sortFirstMiddleLast(T[]a, int first, int mid, int last) {
		order(a, first, mid);
		order(a, mid,last);
		order(a, first, mid);
	}
	private static <T extends Comparable<? super T>>
		void order(T[]a, int i, int j)
		{
			if(a[i].compareTo(a[j])>0)
				swap(a,i,j);
		}
	private static void swap(Object[] array, int i, int j) {
		Object temp = array[i];
		array[i]= array[j];
		array[j] = temp;
	}
	
   // Throws an exception if this object is not initialized.
   private void checkInitialization()
   {
      if (!initialized)
         throw new SecurityException("ArrayBag object is not initialized properly.");
   } // end checkInitialization
} // end ArrayBag




