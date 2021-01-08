/**AUTHOR: HADI EL-KADRI 251015226
 * 
 *Class Dictionary implements DictionaryADT and initializes 
 * a dictionary of input size
 */

/**
 * LinkedList class is imported from java 
 */
import java.util.LinkedList;

public class Dictionary implements DictionaryADT{
	
	//INSTANCE VARIABLES
	private LinkedList<Record>[] hashtable;
	private int count= 0;

	/**
	 * Dictionary is implemented as an array of linked lists and initialized in
	 * this Dictionary constructor
	 * @param size is the desired size of the Dictionary array
	 */
	public Dictionary(int size) {
		hashtable = new LinkedList[size];
		for (int i = 0; i < hashtable.length; i++) {
			hashtable[i] = new LinkedList<Record>();
		}
	}
	
	/**Insert method inserts a desired record into the Dictionary
	 */
	public int insert(Record pair) throws DictionaryException {
		//The hashcode of the string configuration of the record is calculated
		int position = hashFunction(pair.getConfig());
		//The index it is suppose to be placed in is not empty
		if(!hashtable[position].isEmpty()) {
			for( int i = 0; i < hashtable[position].size(); i++) {
				//If its not already in the table then insert it
				if(!hashtable[position].get(i).getConfig().equals(pair.getConfig())) {
					hashtable[position].addFirst(pair);
					return 1;
				}else {
					throw new DictionaryException();
				}
			}
		}
		//If the index is empty make it the first index of the linked list
		else {
			hashtable[position].addFirst(pair);
			return 0;
		}
		return 0;
	}

	/**
	 * Remove method removes a configuration from the hashtable
	 */
	public void remove(String config) throws DictionaryException {
		//Hashcode of the configuration is calculated
		int p = hashFunction(config);
		//If there is nothing in the index throw exception (can not remove what is not there)
		if (hashtable[p].isEmpty()) throw new DictionaryException();
		else {
			//Go through the linked list at the keys index and find the key and remove it
			for(int j = 0; j < hashtable[p].size(); j++) {
				if(hashtable[p].get(j).getConfig().equals(config)){
					hashtable[p].remove(j);
					count --;
				}
				}	
			}
		}

	/**
	 * Get method returns the score of a given configuration
	 */
	public int get(String config) {
		//Hashcode of configuration is calculated
		int p = hashFunction(config);
		//If the index is empty
		if (hashtable[p] == null) return -1;
		else {
			//Go through the linked list at the keys index and find the configuration and return its score
			for(int j = 0; j < hashtable[p].size(); j++) {
				if(hashtable[p].get(j).getConfig().equals(config)) {
					return hashtable[p].get(j).getScore();
				}
			}
		}
		return -1;
	}
	
	/**
	 * Method numElements returns the amount of record objects in the hashtable
	 */
	public int numElements() {
		return count;
	}
	
	/**
	 * Hashfunction method calculates the hashcode of an input string
	 * @param x any string
	 * @return the hashcode of the string
	 */
	private int hashFunction(String x) {
		char[] chars = x.toCharArray();
		int value = (chars[0]);
		int length = chars.length;
		for(int i = 1; i < length; i++) {
			value = (value * 43 + chars[i])% hashtable.length;
		}
		return value % hashtable.length;
	}
}
