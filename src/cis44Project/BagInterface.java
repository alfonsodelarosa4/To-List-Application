package cis44Project;


public interface BagInterface<T>
{
	/*
	 * Retrieves object based on index passed
	 * Parameter: desired index
	 * Returns: object desired
	 */
	public T retrieve (int index);
	
	/*
	 * Sorts list of objects with merge sort
	 */
	public void mergeSort();
	
	
	/*
	 * Sorts list of objects with quick sort
	 */
	public void quickSort();
	
	
	/** Gets the current number of entries in this bag.
		 @return  The integer number of entries currently in the bag. */
	public int getCurrentSize();
	
	/** Sees whether this bag is empty.
		 @return  True if the bag is empty, or false if not. */
	public boolean isEmpty();
	
	/** Adds a new entry to this bag.
	    @param newEntry  The object to be added as a new entry.
	    @return  True if the addition is successful, or false if not. */
	public boolean add(T newEntry);

	/** Removes one unspecified entry from this bag, if possible.
       @return  Either the removed entry, if the removal.
                was successful, or null. */
	public T remove();
   
	/** Removes one occurrence of a given entry from this bag.
       @param anEntry  The entry to be removed.
       @return  True if the removal was successful, or false if not. */
   public boolean remove(T anEntry);
	
	/** Removes all entries from this bag. */
	public void clear();
	
	/** Counts the number of times a given entry appears in this bag.
		 @param anEntry  The entry to be counted.
		 @return  The number of times anEntry appears in the bag. */
	public int getFrequencyOf(T anEntry);
	
	/** Tests whether this bag contains a given entry.
		 @param anEntry  The entry to locate.
		 @return  True if the bag contains anEntry, or false if not. */
	public boolean contains(T anEntry);
	public void display();
	
	/** Removes an entry given the index
	 @param givenIndex to locate
	 @return  Entry of array */
	public T removeEntry(int givenIndex);
}
   
