public class LinkedBag<T> implements BagInterface<T> {
    private Node firstNode;
    private int numberOfEntries;

    public LinkedBag(){
        firstNode = null;
        numberOfEntries = 0;
    }// end constructor

    /** Gets the number of entries currently in this bag.
     * @return True if this bag is empty, or false if not. */
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }// end getCurrentSize

    /** Sees whether this bag is empty.
     * @return True if this bag is empty, or false if not. */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }// end isEmpty

    /** Adds a new entry to this bag.
     @param newEntry The object to be added as a new entry
     @return True if the addition is successful, or false if not. */
    @Override
    public boolean add(T newEntry) {
        // Add to beginning of chain:
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;       // Make new node reference rest of chain
                                        // (firstNode is null if chain is empty)
        firstNode = newNode;            // New node is at beginning of chain
        numberOfEntries++;
        return true;
    }

    /** Removes one unspecified entry from this bag, if possible.
    @return Either the removed entry, if the removal was successful, or null. */
    @Override
    public T remove() {
        T result = null;
        if (firstNode != null) {
            result = firstNode.getData();
            firstNode = firstNode.getNextNode(); // Remove first node from chain
            numberOfEntries--;
        } // end if
        return result;
    } // end remove

    /** Removes one occurrence of a given entry from this bag,
     if possible.
     @param anEntry The entry to be removed.
     @return True if the removal was successful, or false
     otherwise. */
    @Override
    public boolean remove(T anEntry) {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);
        if (nodeN != null) {
            // Replace located entry with entry in first node
            nodeN.setData(firstNode.getData());
            // Remove first node
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
            result = true;
        } // end if
        return result;
    } // end remove

    /** Removes all entries from this bag.*/
    @Override
    public void clear() {
        while(!isEmpty())
            remove();
    }// end clear

    /** Counts the number of times a given entry appears in this bag.
     * @param anEntry The entry to be counted.
     * @return The number of times anEntry appears in this bag. */
    @Override
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        int counter = 0;
        Node currentNode = firstNode;
        while((counter < numberOfEntries) && (currentNode != null)){
            if(anEntry.equals(currentNode.getData()))
                frequency++;
            counter++;
            currentNode = currentNode.getNextNode();
        }// end while
        return frequency;
    }// end getFrequencyOf

    /** Tests whether this bag contains a given entry.
     * @param anEntry The entry to find.
     * @return True if the bag contains anEntry, or false otherwise. */
    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while(!found && (currentNode != null)){
            if(anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        }// end while
        return found;
    }// end contains

    @Override
    public BagInterface<T> union(BagInterface<T> aBag) {
        BagInterface<T> bagUnion = new LinkedBag();
        Node currentNode = this.firstNode;
        while(currentNode != null){
            bagUnion.add(currentNode.getData());
            currentNode = currentNode.getNextNode();
        }
        T[] aBagArray = aBag.toArray();
        for(int i = 0; i < aBagArray.length; i++){
            bagUnion.add(aBagArray[i]);
        }
        return bagUnion;
    }

    @Override
    public BagInterface<T> intersection(BagInterface<T> aBag) {
        BagInterface<T> bagIntersect = new LinkedBag<T>();
        T[] array1 = this.toArray();
        T[] array2 = aBag.toArray();
        for(int i = 0; i < array1.length; i++){
            for(int j = 0; j < array2.length; j++){
                if(array1[i].equals(array2[j])) {
                    bagIntersect.add(array1[i]);
                    array2[j] = null;
                    break;
                }
            }
        }
        return bagIntersect;
    }

    @Override
    public BagInterface<T> difference(BagInterface<T> aBag) {
        BagInterface<T> bagDiff = new LinkedBag<T>();
        T[] array1 = this.toArray();
        T[] array2 = aBag.toArray();
        for(int i = 0; i < array1.length; i++){
            for(int j = 0; j < array2.length; j++){
                if(array1[i].equals(array2[j])) {
                    array1[i] = null;
                    array2[j] = null;
                    break;
                }
            }
        }
        for(int i = 0; i < array1.length; i++){
            if(array1[i] != null)
                bagDiff.add(array1[i]);
        }
        return bagDiff;
    }

    /** Retrieves all entries that are in this bag.
     * @return A newly allocated array of all the entries in this bag. */
    @Override
    public T[] toArray() {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];

        int index = 0;
        Node currentNode = firstNode;
        while((index < numberOfEntries) && (currentNode != null)){
            result[index] = currentNode.getData();
            index++;
            currentNode = currentNode.getNextNode();
        }// end while
        return result;
    }// end toArray

    @Override
    public void displayBag() {
        Node currentNode = firstNode;
        System.out.print("{ ");
        while(currentNode != null){
            System.out.print(currentNode.getData());
            if(currentNode.getNextNode() != null)
                System.out.print(", ");
            currentNode = currentNode.getNextNode();
        }

        System.out.println(" }");
    }

    private class Node{
        private T data;     // entry in bag
        private Node next;  // link to next node

        private Node(T dataPortion){
            this(dataPortion, null);
        }// end constructor
        private Node(T dataPortion, Node nextNode){
            data = dataPortion;
            next = nextNode;
        }// end constructor

        private T getData(){
            return data;
        }// end getData
        private Node getNextNode(){
            return next;
        }// end getNextNode
        private void setData(T newData){
            data = newData;
        }// end setData
        private void setNextNode(Node nextNode){
            next = nextNode;
        }// end setNextNode
    }

    /** Locates a given entry within this bag.
     * Returns a reference to the node containing the
     * entry, if located, or null otherwise.*/
    private Node getReferenceTo(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        } // end while
        return currentNode;
    } // end getReferenceTo
}
