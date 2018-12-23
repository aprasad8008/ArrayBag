/* 
 * ArrayBag.java
 * 
 * Author:          Computer Science S-111 staff
 * Modified by:     Anusha Prasad, apsmileyface@gmail.com
 * Date modified:   7/20/16
 */

import java.util.*;

/**
 * An implementation of a Bag ADT using an array.
 */
public class ArrayBag implements Bag {
    /** 
     * The array used to store the items in the bag.
     */
    private Object[] items;
    
    /** 
     * The number of items in the bag.
     */
    private int numItems;
    
    public static final int DEFAULT_MAX_SIZE = 50;
    
    /**
     * Default, no-arg constructor - creates a new, empty ArrayBag with 
     * the default maximum size.
     */
    public ArrayBag() {
        items = new Object[DEFAULT_MAX_SIZE];
        numItems = 0;
    }
    
    /** 
     * A constructor that creates a new, empty ArrayBag with the specified
     * maximum size.
     */
    public ArrayBag(int maxSize) {
        if (maxSize <= 0)
            throw new IllegalArgumentException("maxSize must be > 0");
        items = new Object[maxSize];
        numItems = 0;
    }
    
    /** 
     * add - adds the specified item to the Bag.  Returns true on
     * success and false if there is no more room in the Bag.
     */
    public boolean add(Object item) {
        if (item == null)
            throw new IllegalArgumentException("item must be non-null");
        if (numItems == items.length)
            return false;              // no more room!
        else {
            items[numItems] = item;
            numItems++;
            return true;
        }
    }
    
    /** 
     * remove - removes one occurrence of the specified item (if any)
     * from the Bag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in the Bag.
     */
    public boolean remove(Object item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i].equals(item)) {
                // Shift the remaining items left by one.
                for (int j = i; j < numItems - 1; j++) 
                    items[j] = items[j + 1];
                items[numItems - 1] = null;
                
                numItems--;
                return true;
            }
        }
        
        return false;  // item not found
    }
    
    /**
     * contains - returns true if the specified item is in the Bag, and
     * false otherwise.
     */
    public boolean contains(Object item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i].equals(item))
                return true;
        }
        
        return false;
    }
    
    /**
     * containsAll - does this ArrayBag contain all of the items in
     * otherBag?  Returns false if otherBag is null or empty. 
     */
    public boolean containsAll(Bag otherBag) {
        if (otherBag == null || otherBag.numItems() == 0)
            return false;
        
        Object[] otherItems = otherBag.toArray();
        for (int i = 0; i < otherItems.length; i++) {
            if (!contains(otherItems[i]))
                return false;
        }
        return true;
    }
    
    /**
     * numItems - returns the number of items in the Bag.
     */
    public int numItems() {
        return numItems;
    }
    
    /**
     * grab - returns a reference to a randomly chosen in the Bag.
     */
    public Object grab() {
        if (numItems == 0)
            throw new NoSuchElementException("the bag is empty");
        int whichOne = (int)(Math.random() * numItems);
        return items[whichOne];
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[numItems];
        
        for (int i = 0; i < numItems; i++)
            copy[i] = items[i];
        
        return copy;
    }
    
    /**
     * toString - converts this ArrayBag into a readable String object.
     * Overrides the Object version of this method.
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < numItems; i++)
            str = str + " " + items[i];
        str = str + " }";
        
        return str;
    }
    
    //return the maximum number of items in the ArrayBag 
    public int capacity ()
    {
        return DEFAULT_MAX_SIZE;
    }
    
    //return true if the ArrayBag is empty, and false otherwise
    public boolean isEmpty ()
    {
        if (this.numItems == 0)
        {
            return true;
        }
        return false;
    }
    
    //return the number of times that the parameter item occurs in the called ArrayBag
    public int numOccur (Object item)
    {
        //Object[] object1 = this.toArray();
        int count = 0;
        for (int i = 0; i < items.length; i++)
        {
            if (items[i].equals(item))
            {
                count++;
            }
            //System.out.println(count);
        }
        return count;
    }
    
    //add to the called ArrayBag all of the items found in the parameter other
    public boolean addItems (Bag other)
    {
        Object[] otherItems = other.toArray();
        if (this.numItems == 0)
        {
            for (int i = 0; i < otherItems.length; i++)
            {
                add(otherItems[i]);
            }
        }
        return containsAll(other);
    }
    
    //determine if the called ArrayBag is equal to the parameter other
    public boolean equals (Bag other)
    {
        if (other == null)
        {
            return false;
        }
        if (other.numItems() != this.numItems())
        {
            return false;
        }
        if (other.numItems() == 0 && this.numItems() == 0)
        {
            return true;
        }
        else 
        {
            if(containsAll(other))
            {
                return true;
            }
            return false;
        }
    }
    
    //return an ArrayBag containing one occurrence of any item that is found in either the called object or the parameter other
    public Bag unionWith (Bag other){
        
        if (other == null)
        {
            throw new IllegalArgumentException();
        }
        ArrayBag temp = new ArrayBag(other.capacity() + this.capacity());
        
        Object[] otherItems = toArray();
        for (int i = 0; i < otherItems.length; i++) 
        {
            if (!temp.contains(otherItems[i]))
            {
                temp.add(otherItems[i]);
            }
        }
        
        Object[] otherItems1 = other.toArray();
        for (int i = 0; i < otherItems1.length; i++) 
        {
            if (!temp.contains(otherItems1[i])){
                temp.add(otherItems1[i]);
            }
        }
        return temp;
    }
    
    
    /* Test the ArrayBag implementation. */
    public static void main(String[] args) 
    {
        
        // Create a Scanner object for user input.
        Scanner in = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("Size of bag 1: ");
        int size = in.nextInt();
        Bag bag1 = new ArrayBag(size);
        in.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < size; i++) {
            System.out.print("item " + i + ": ");
            itemStr = in.nextLine();
            bag1.add(itemStr);
            System.out.println("bag 1 = " + bag1);
           
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
         
        
        
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per
        // line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++)
            System.out.println(items[i]);
        System.out.println();
        
        // Get an item to remove from bag2, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = in.nextLine();
        if (bag1.contains(itemStr))
            bag1.remove(itemStr);
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
         // Create an ArrayBag named bag2.
        System.out.print("Size of bag 2: ");
        int size2 = in.nextInt();
        Bag bag2 = new ArrayBag(size2);
        in.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag2, and print out bag2.
        String itemStr2;        
        for (int i = 0; i < size2; i++) {
            System.out.print("item " + i + ": ");
            itemStr2 = in.nextLine();
            bag2.add(itemStr2);
        }
        System.out.println("bag 2 = " + bag2);
        System.out.println();
        
        // Select a random item and print it.
        Object item2 = bag2.grab();
        System.out.println("grabbed " + item2);
        System.out.println();
        
        // Iterate over the objects in bag2, printing them one per
        // line.
        Object[] items2 = bag2.toArray();
        for (int i = 0; i < items2.length; i++)
            System.out.println(items2[i]);
        System.out.println();
        
        // Get an item to remove from bag2, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr2 = in.nextLine();
        if (bag2.contains(itemStr2))
            bag2.remove(itemStr2);
        System.out.println("bag 2 = " + bag2);
        System.out.println();
        
        //Tests on bags with methods written
        System.out.println(bag1.capacity());
        System.out.println(bag2.capacity());
        System.out.println(bag1.isEmpty());
        System.out.println(bag2.isEmpty());
        System.out.println(bag1.numOccur("1"));
        System.out.println(bag2.numOccur("5"));
        System.out.println(bag1.addItems(bag2));
        System.out.println(bag1.equals(bag2));
        System.out.println(bag1.unionWith(bag2));
        
        
        
    }
}