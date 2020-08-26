package implementation;

import java.util.ArrayList;
import java.util.List;

/**
 * Array implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */
public class ArrayMultiset extends RmitMultiset
{

	// This is a dyanmic array with ArrayMultiset
	protected MyObject array[];

	int size;
	int capacity = 1;

	protected static final int minCapacity = 1;


	public ArrayMultiset() {

		array = new MyObject[1];
		size = 0;
	}

    @Override
	public void add(String elem) {
        // Implement me!
    	// check if size if full or not if full then increase size by one on every addition


  		if (this.contains(elem)) {
  			for (int i = 0 ; i < size; i++) {
  				if (array[i].getKey().equals(elem)) {
  					array[i].setValue(array[i].getValue() + 1);
  				}
  			}
  		}
  		else {
  			if (size < array.length) {
  				array[size] = new MyObject(elem, 1);
  				size++;
  			} else {

  				int newSize = array.length + minCapacity;
  				MyObject newArray[] = new MyObject[newSize];

  				for (int i = 0; i < array.length; i++) {

  					newArray[i] = array[i];
  				}

  				newArray[array.length] = new MyObject(elem, 1);
  				array = newArray;
  				size++;
  			}
  		}

    } // end of add()


    @Override
	public int search(String elem) {
        // Implement me!
    	int countInstances = 0;

      for (MyObject item : array){
        if (item.getKey().equals(elem)){
            countInstances = item.getValue();
        }
      }

    	if (countInstances > 0 )
    		return countInstances;
        // Placeholder, please update.
        return searchFailed;
    } // end of search()


    @Override
    public List<String> searchByInstance(int instanceCount) {

    	List<String> searchInstances = new ArrayList<String>();
      //
    	// ArrayMultiset tempSet = new ArrayMultiset();
      //
    	// tempSet = this.getDistinctKeyWithInstances();

    	// check if there is a value of instances matched with the presented number of instances of the key
      for (int i =0 ; i < size ; i++){
        if (array[i].getValue() == instanceCount) {
          searchInstances.add(array[i].getKey());
        }
      }
    	if (searchInstances.size() > 0 ) return searchInstances;

        // Placeholder, please update.
        return null;
    } // end of searchByInstance


    @Override
	public boolean contains(String elem) {
        // Implement me!
        for (int i = 0 ; i < size ; i++){
          if (array[i].getKey().equals(elem)) {
            return true;
          }
        }

        // Placeholder, please update.
        return false;
    } // end of contains()


    @Override
	public void removeOne(String elem) {
        // Implement me!
        if (this.contains(elem)) {
          ArrayMultiset newSet = new ArrayMultiset();
          boolean found = false;
          int newInstance = 0;
          for (int i = 0; i < size; i++) {
            if (array[i].getKey().equals(elem)) {
              found = true;
              newInstance = array[i].getValue() > 1 ? array[i].getValue() - 1 : 0;

            }


            if (found ) {

              if (newInstance > 0) {
                newSet.add(new MyObject(elem, newInstance));
              }
              else if (i+1 < size){
                newSet.add(array[i+1]);

              }
            }
            else {
              newSet.add(array[i]);
            }


          }

          this.array = newSet.array;
          this.size = newSet.size;
          this.capacity = newSet.capacity;
        }

    } // end of removeOne()

//@Warning("On for loop the array should be changed to tempSet")
    @Override
	public String print() {

            //return "This is from print statement";

             String output = "";
              ArrayMultiset tempSet = new ArrayMultiset();

          	tempSet = this.SortingByInstances();
          for(int i = 0 ; i < size ; i++){
            output += tempSet.array[i].getKey() + " : " + tempSet.array[i].getValue() + "\n";
          }
          	if (output.length() > 0 ) {
          		return output;
          	}
        // Placeholder, please update.
        return new String();
    } // end of OrderedPrint


    @Override
	public String printRange(String lower, String upper) {

          String output = "";
          ArrayMultiset tempSet = new ArrayMultiset();

          tempSet = this;

          for (MyObject item : tempSet.array) {
            if (item.getKey().compareTo(lower) > 0 && item.getKey().compareTo(upper) < 0) {
              output += item.getKey() + " : " + item.getValue() + "\n";
            }
          }

          if (output.length() > 0) {
            return output;
          }



        // Placeholder, please update.
        return new String();
    } // end of printRange()


    @Override
	public RmitMultiset union(RmitMultiset other) {

    // Placeholder, please update.
		ArrayMultiset unionSet = new ArrayMultiset();
		unionSet = this;



		if (other instanceof ArrayMultiset) {


			ArrayMultiset otherSet = (ArrayMultiset) other;

			for (MyObject item : otherSet.array) {

				String key = item.getKey();

				boolean duplicate = false;
				for (MyObject union: unionSet.array) {
					if (union.getKey().equals(key)) {
						union.setValue(union.getValue() + item.getValue());
						duplicate = true;
					}
				}
				if (!duplicate) {
					unionSet.add(item);
				}

			}
		}

		if (unionSet.getSize() > 0)
			return unionSet;


        return null;
    } // end of union()


    @Override
	public RmitMultiset intersect(RmitMultiset other) {

            // Placeholder, please update.
        ArrayMultiset interSet = new ArrayMultiset();

        if (other instanceof ArrayMultiset) {

        ArrayMultiset otherSet = (ArrayMultiset) other;

        for (MyObject item : otherSet.array) {

          String key = item.getKey();
          for (MyObject inter: this.array) {
            if (inter.getKey().equals(key)) {

              int intersectValue = inter.getValue() >= item.getValue() ? item.getValue() : inter.getValue();

              MyObject newObject = new MyObject(key, intersectValue);
              interSet.add(newObject);

            }
          }

        }
        }

        if (interSet.getSize() > 0)
        return interSet;

        return null;
    } // end of intersect()


    @Override
	public RmitMultiset difference(RmitMultiset other) {

              // Placeholder, please update.
          ArrayMultiset diffSet = new ArrayMultiset();

          if (other instanceof ArrayMultiset) {


          ArrayMultiset otherSet = (ArrayMultiset) other;

          for (MyObject item : this.array) {

          String key = item.getKey();

          boolean duplicate = false;
          for (MyObject right: otherSet.array) {
            if (right.getKey().equals(key)) {

              if (item.getValue() > right.getValue()) {

                MyObject newObject = new MyObject(key, (item.getValue() - right.getValue()));
                diffSet.add(newObject);
              }
              duplicate = true;
            }
          }

          if (!duplicate) {
            diffSet.add(item);
          }

          }
          }

          if (diffSet.getSize() > 0)
          return diffSet;

        return null;
    } // end of difference()


    /* *****************ADDED METHOD ON THE ARRAY MULTISET*************/



    /*increasing size of the array
     * First create tempArray with the size increment by 1
     * transfer all the data from the dynamic Array to temp Array
     * Finally store tempArray on dynamic Array with remaining one empty size at the end
     * increase the capacity of the dynamic array by 1 each time
     */


    // these are the getter and setter for the attributes I have created
	public MyObject[] getCustomArray() {
		return array;
	}

	public void setDynamicArray(MyObject[] array) {
		this.array = array;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

// add  MyObject with key and value and create dynamic array
  public void add(String key , Integer value) {

  if (size < array.length) {
    array[size] = new MyObject(key, value);
    size++;
  }
  else {

    int newSize = array.length + minCapacity;
    MyObject newArray[] = new MyObject[newSize];

    for (int i = 0 ; i < array.length; i++) {

      newArray[i] = array[i];
    }

    newArray[array.length] = new MyObject(key, value);
    array = newArray;
    size++;
  }
}


//// add  MyObject and create dynamic array
public void add(MyObject newObject) {

if (size < array.length) {
  array[size] = newObject;
  size++;
}
else {

  int newSize = array.length + minCapacity;
  MyObject newArray[] = new MyObject[newSize];

  for (int i = 0 ; i < array.length; i++) {

    newArray[i] = array[i];
  }

  newArray[array.length] = newObject;
  array = newArray;
  size++;
}
}


	// two aditional method to get index value and set value for the index
//	public String getIndexValue(int index) {
//		return this.dynamicArray[index];
//	}
//	public void  setValueIndex(String value, int index) {
//		 this.dynamicArray[index] = value;
//	}




    public ArrayMultiset getDistinctKeyWithInstances() {

    	List<String> searchInstances = new ArrayList<String>();

    	ArrayMultiset tempSet = new ArrayMultiset();
    	//ArrayMultiset tempValues = new ArrayMultiset();


    	for(int i = 0 ; i < size ; i++) {
    		String key = array[i].getKey();
    		if(tempSet.contains(key)) {
    			int previousInstance = tempSet.search(key);
          tempSet.add(key, (previousInstance + 1));
    		}
    		else {
          tempSet.add(key,  1);
    		}

    	}
    	if (tempSet.getSize() > 0 )
    		return tempSet;


        // Placeholder, please update.
        return null;
    } // end of searchByInstance





	  public ArrayMultiset SortingByInstances() {

		  //ArrayMultiset sortedSet = new ArrayMultiset();

      ArrayMultiset tempSet = new ArrayMultiset();
  		tempSet = this;

  		for (int i = 0; i < size - 1; i++) {

  			Integer currentValue = tempSet.array[i].getValue();
  			MyObject min = new MyObject();
  			int maxIndex = i;

  			for (int j = i + 1; j < size; j++) {

  				if (currentValue < tempSet.array[j].getValue()) {
  					maxIndex = j;
  				}

  			}

  			// swap two index items
  			MyObject tempObject = tempSet.array[i];
  			tempSet.array[i] = tempSet.array[maxIndex];
  			tempSet.array[maxIndex] = tempObject;

  		}

  		if (tempSet.size > 0) {
  			return tempSet;
  		}

	      // Placeholder, please update.
	      return null;
	  } // end of sortingByInstance



	  /* NOT IMPLEMENTED --- for future implementation*/
	  public ArrayMultiset SortingByKey() {

		  //ArrayMultiset sortedSet = new ArrayMultiset();

	    ArrayMultiset tempSet = new ArrayMultiset();
	    tempSet = this.getDistinctKeyWithInstances();

	    for (int i = 0 ; i < size -1; i++) {

	    	Integer currentValue = tempSet.array[i].getValue();
	    	MyObject min = new MyObject();
	    	int minIndex = i;

	    	for(int j = i + 1 ; j < size ; j++) {

	    		if(currentValue < tempSet.array[j].getValue()) {
	    			minIndex = j;
	    		}

	    	}

	    	// swap two index items
	    	MyObject tempObject = tempSet.array[i];
	    	tempSet.array[i] = tempSet.array[minIndex];
	    	tempSet.array[minIndex] = tempObject;


	    }

	    if (tempSet.size > 0) {
	    	return tempSet;
	    }

	      // Placeholder, please update.
	      return null;
	  } // end of sortingByKey


	  public int getAsciiValue(String text) {

		  int asciiValue = 0;

		  char[] charArray = text.toCharArray();
		  for (char ch: charArray) {
			  asciiValue += (int) ch;
		  }


		  return asciiValue;
	  }

/*
  public List<String> sortByInstances() {

    List<String> searchInstances = new ArrayList<String>();

    ArrayMultiset tempKeys = new ArrayMultiset();
    ArrayMultiset tempValues = new ArrayMultiset();


    for(int i = 0 ; i < size ; i++) {
      String key = dynamicArray[0];
      if(tempKeys.contains(key)) {
        int previousInstance = Integer.parseInt(tempValues.dynamicArray[i]);
        tempValues.dynamicArray[i] = String.valueOf(previousInstance + 1)  ;
      }
      else {
        tempKeys.add(key);
        tempValues.add("1");
      }

    }

    // check if there is a value of instances matched with the presented number of instances of the key
    for(String value: tempValues.dynamicArray) {

      if (Integer.parseInt(value) == instanceCount) {
        searchInstances.add(value);
      }
    }


    if (searchInstances.size() > 0 ) return searchInstances;



      // Placeholder, please update.
      return null;
  } // end of searchByInstance

*/


	    /*
	    public void increaseSize() {

	    	CustomArray[] tempArray = new CustomArray[++currentCapacity];
	    	for (int i = 0 ; i < currentCapacity; i++) {

	    		tempArray[i] = customArray[i];
	    	}

	    	array = tempArray;
	    	capacity = currentCapacity++;
	    	System.out.println("new size of the array is : " + capacity);
	    }

	    */



} // end of class ArrayMultiset
