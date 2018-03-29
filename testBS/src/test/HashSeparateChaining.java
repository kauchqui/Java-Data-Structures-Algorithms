package test;
public class HashSeparateChaining {

	private class Node {
		Entry entry;
		Node next;

		Node(Entry entry) { this.entry = entry; }
	}

	Node[] hashTable;
	int arraySize; // M
	int tableSize; // N

	public HashSeparateChaining(){
		hashTable = new Node[10];
		arraySize = 10;
	}

	public HashSeparateChaining(int arraySize){
		this.arraySize = arraySize;
		this.hashTable = new Node[arraySize];
		this.tableSize = 0;
	}

	/** TODO: Write a resizing method for the hash table.*/
	//Ensures the average length of the chains are between 3 and 5 nodes at all times.
	private void resize() {
		//adjusts the size of the hash table when it less than 3
		if (tableSize <= 3* arraySize){
			HashSeparateChaining temp = new HashSeparateChaining(arraySize / 2);
			int i =0;
			while(i<arraySize) {
				for(Node x = hashTable[i]; x!= null; x = x.next) {
					temp.put(x.entry);
				}
				i++;
			}
			this.arraySize = temp.arraySize;
			this.tableSize = temp.tableSize;
			this.hashTable = temp.hashTable;
		}
		//adjusts the size of the hash table when it greater than 5
		if (tableSize >= 5* arraySize){
			HashSeparateChaining temp = new HashSeparateChaining(arraySize * 2);
			int i = 0;
			while(i< arraySize) {
				for(Node x = hashTable[i]; x!= null; x = x.next) {
					temp.put(x.entry);
				}
				i++;
			}
			this.hashTable = temp.hashTable;
			this.arraySize = temp.arraySize;
			this.tableSize = temp.tableSize;
		}


	}

	/** Computes the index in the hash table from a given key */
	private int hash(String key) {
		int hashCode = key.hashCode();
		return (hashCode & 0x7fffffff) % arraySize;
	}

	/** Returns the number of entries in the hash table. */
	public int size() { return tableSize; }

	/** Checks whether the hash table is empty */
	public boolean isEmpty() { return tableSize == 0; }

	/** Returns the node containing the given key value if it exists in the table.
	    Otherwise, it returns a null value. */
	private Node findEntry(String key) {
		int index = hash(key);

		Node currentNode = hashTable[index];
		while (currentNode != null && !currentNode.entry.getKey().equals(key))
			currentNode = currentNode.next;

		return currentNode;

	}

	/** Returns the integer value paired with the given key, if the key is in the table.
		Otherwise, it returns null. */
	public Integer get(String key) {
		Node searchResult = findEntry(key);

		if (searchResult != null)
			return searchResult.entry.getValue();
		else
			return null;

	}

	 public void put(Entry entry){
	 	Node searchResult = findEntry(entry.getKey());
		if(searchResult != null){
	 		searchResult.entry.setValue(entry.getValue());
	 		return;
		}
    	Node newNode = new Node(entry);
		int index = hash(entry.getKey());
		if (hashTable[index] != null){
	 		newNode.next = hashTable[index]; 
		}
	 	hashTable[index] = newNode;
	 	//
	 	tableSize++; 

    }

	/** If the given key is not in the table, creates a new entry and adds it to the table.
		Otherwise, it updates the value associated with the given key. */
	public void put(String key, Integer value) {
		Node searchResult = findEntry(key);

		if (searchResult != null){
			searchResult.entry.setValue(searchResult.entry.getValue() + 1);
			return;
		}

		Entry newEntry = new Entry(key, value);
		Node newNode = new Node(newEntry);

		int index = hash(key);
		if (hashTable[index] != null){
			newNode.next = hashTable[index]; 
		}
		hashTable[index] = newNode;
		//must increase table size since new element has been added to the hash table
		tableSize++;
		// checks if the length of the chain is within the range of 3-5 and calls resize() otherwise
		if ((tableSize >= 5* arraySize) || (tableSize <= 3* arraySize && arraySize != 1 && tableSize < 5)){
			resize();
		}
		/*
		if (tableSize <= 3* arraySize && arraySize != 1 && tableSize < 5){
			resize();
		}*/
	}

	/** Removes the entry containing the given key from the table, if the key exists in the table. */
	public void delete(String key) {
		Node searchResult = findEntry(key);
		if (searchResult == null)
			return;

		int index = hash(key);
		if (hashTable[index] == searchResult){
			hashTable[index] = searchResult.next; 
			resize();
		}else{
			Node currentNode = hashTable[index];
			while (currentNode.next != searchResult)
				currentNode = currentNode.next;
			currentNode.next = searchResult.next;
		}
		//Must decrease table size since an element has been removed from the hashtable
		tableSize--;
		// checks if the length of the chain is within the range of 3-5 and calls resize() otherwise
		if(tableSize <= 3 * arraySize){
			resize();
		}
	}

	/** Produces a string representation of the table. */
	@Override
	public String toString(){
		String output = "";
		for (int i = 0; i < arraySize; i++){
			output += "(" + i + "): ";
			Node currentNode = hashTable[i];
			if (currentNode == null)
				output += currentNode + "\n";
			else{
				while (currentNode != null){
					output += " -> " + currentNode.entry;
					currentNode = currentNode.next;
				}
				output += "\n";
			}
		}

		return output;

	}
}