package project2;


/*
 * The Number class is used to represent positive integers. It provides several operations to 
 * support number manipulation: addition, multiplication and comparisons.
 * 
 * There is no limit to the number of digits in a Number object
 * 
 * The numbers are represented in the form of a linked list. Each node in the list contains a 
 * single digit. For example,
 * 
 * 134 is represented as a three node list:
 * head --> | 4 | --> | 3 | --> | 1 | --> null 
 * 
 * and 98765 is represented as a five node list:
 * head --> | 5 | --> | 6 | --> | 7 | --> | 8 | --> | 9 | --> null
 * 
 * The least significant digit, or the ones digit, is stored in the very first node. The tens 
 * digit is stored in the second node. And so on ....
 * 
 * @author: Rajneet Mahal with the help of ULC tutors
 * @version: 02/23/2023
 */
public class Number extends Object implements Comparable<Number>{
	
	/*
	 * Internal Node class that uses Tree as an Object and creates references to next node
	 * 
	 * @author Rajneet Mahal
	 * @version 02/23/2023
	 */
	private class Node{
		public int data;
		public Node next;
		
		public Node (int data) {
			this.data = data;
			this.next = null;
		}
	}
	
	private Node head;
	private Node tail;
	private int size;
	
	/*
	 * Constructor: Creates a Number object with value represented by the string argument number.
	 * 
	 * @param number- string representation of the number
	 * 
	 * @throws NullPointerException - if number is null
	 * @throws IllegalArgumentException - if number contains any illegal characters
	 * 
	 * @author: Rajneet Mahal with the help of ULC tutors
	 */
	public Number(String number){
		if(number == null) {
			throw new NullPointerException("number is null");
		}
		if(number.matches(".*[a-zA-Z+=]+.*")) {
			throw new IllegalArgumentException("illegal characters in num");
		}
		
		head = null;
		tail = null;
		size = 0;
		
		
		for (int i = number.length() - 1; i >= 0; i--) {
	        int value = Character.getNumericValue(number.charAt(i));
	        Node node = new Node(value);
	        if (head == null) {
	            head = node;
	            tail = node;
	        } else {
	            tail.next = node;
	            tail = node;
	        }
	        size++;
	    }
		
	}
	
	/*
	 * Computes the sum of this object with other. Returns the result in a new object. 
	 * This object is not modified by call to add.
	 * 
	 * @param other - the value to be added to this object
	 * 
	 * @return a Number object whose value is equal to the sum of this object and other
	 * 
	 * @throws NullPointerException - if other is null
	 * 
	 */
	public Number add(Number other) {
		
		if(other.head == null) {
			throw new NullPointerException("other is null");
		}
		
		Node current = head;
		Node currentOther = other.head;
		
		Number result = new Number("");
		Node resultCurrent = result.head;
		
		int extra = 0;
		
		while(current != null || currentOther != null) {
			int sum = extra;
			
			if(current!= null) {
				sum += current.data;
				current = current.next;
			}
			if(currentOther != null) {
				sum += currentOther.data;
				currentOther = currentOther.next;
			}
			
			Node num = new Node(sum % 10);
			extra = sum /10;
			
			if(result.head == null) {
				result.head = num;
				resultCurrent = num;
			}
			else {
				resultCurrent.next = num;
				resultCurrent = num;
			}
				
		}
		
		if(extra != 0) {
			Node num = new Node(extra);
			if (result.head == null) {
        	    result.head = num;
        	} else {
        	    Node last = result.head;
        	    while (last.next != null) {
        	        last = last.next;
        	    }
        	    last.next = num;
        	}
		}
		return result;
	}
	
	
	/*
	 * Compares this object with other for order. Returns a negative integer if this 
	 * object's value is strictly smaller than the value of other. Returns a positive 
	 * integer if this object's value is strictly greater than the value of other. Returns 
	 * zero if two values are the same
	 * 
	 * @param other - the object to be compared with this object
	 * 
	 * @return a negative integer, zero, or a positive integer as this object is less than,
	 * equal to, or greater than other
	 * 
	 * @throws NullPointerException - if other is null
	 * 
	 * @author: Rajneet Mahal with the help of ULC tutors
	 */
	public int compareTo(Number other) throws NullPointerException {
		if(other == null) {
			throw new NullPointerException("other is null");
		}
		
		Node currOther = other.head;
		Node curr = head;
		
		if(length() == other.length()) {
			while(curr!= null && currOther != null) {
				if(curr.data != currOther.data) {
					return curr.data - currOther.data;
				}
				
				currOther = currOther.next;
				curr = curr.next;
			}
		}
		else {
			return length()- other.length();
		}
		
		return 0;
	}
	
	
	@Override
	/*
	 * Determines if this object is equal to obj. Two Number objects are equal if all of their
	 * digits are the same and in the same order, and if they have the same number of digits.
	 * In other words, if the values represented by the two objects are the same.
	 * 
	 * @param obj- the object to be compared to this semester 
	 * 
	 * @return true if two objects are equal, false otherwise
	 * 
	 */
	public boolean equals(Object obj) {
		if(obj == this) {
	    	return true;
	    }
		if (!(obj instanceof Number)) {   
	        return false;
	    }
	    
	    Number other = (Number) obj;
	    Node curr = head;
	    Node currOther = other.head;

	    for (int i = 0; i < this.length(); i++) {
	    	 if (curr.data != currOther.data) {
		            return false;
		        }
	    	 currOther = currOther.next;
	    	 curr = curr.next;
	    }
	   
	    return true;
	}

	
	/*
	 * Returns the number of digits in this object.
	 * 
	 * @return the number of digits in this object.
	 * 
	 */
	public int length() {
		int count = 0;
		Node current = head;
		
		while(current != null) {
			count ++;
			current = current.next;
		}
		
		return count;
	}
	
	/*
	 * Computes the product of this object and other. Returns the result in a new object. 
	 * This object is not modified by call to multiply
	 * 
	 * @param other - the object to be multiplied by this object
	 * 
	 * @throws NullPointerException - if other is null
	 * 
	 * authors: Rajneet Mahal and tutors at the ULC
	 * 
	 */
	public Number multiply(Number other) throws NullPointerException{
		if (other == null) {
	        throw new NullPointerException();
	    }
		
		Node otherCurrent = other.head;
		Number result = new Number("0");
	
		int position = 0;

		while (otherCurrent != null) {
			
			int extra = 0;
		
			Node current = this.head;
			Number midResult = new Number("");
		     
		    for (int i = 0; i < position; i++) {
		         midResult.addDigit(0);
		    }
		     
		    while (current != null) {
		        int prod = otherCurrent.data * current.data;
		        
		        if(extra != 0) {
					prod += extra;
				}
		        
		        extra = prod / 10;
		        
		        int digit = prod % 10;
		        midResult.addDigit(digit);
		       
		        current = current.next;
		    }
		     
		    if(extra != 0) {
		        midResult.addDigit(extra);
		    }
		        
		    result = result.add(midResult);
		    position++;
		    otherCurrent = otherCurrent.next;
		    
		}
		  
		return result;
	}
	
	/*
	 * 
	 * Adds a digit to the end of the number.
	 * 
	 * @param digit - The digit to add
	 * 
	 */
	public void addDigit(int digit) {
	    Node newNode = new Node(digit);
	    if (head == null) {
	        head = newNode;
	        tail = newNode;
	    } else {
	        tail.next = newNode;
	        tail = newNode;
	    }
	}

	/*
	 * Computes the product of this object and a single digit digit. Returns the result 
	 * in a new object. This object is not modified by call to multiplyByDigit.
	 * 
	 * @param digit - a single positive digit to be used for multiplication
	 * 
	 * @returns a Number object whose value is equal to the product of this object and digit
	 * 
	 * @throws IllegalArgumentException - when digit is invalid (i.e., not a single digit or negative)
	 * 
	 */
	public Number multiplyByDigit(int digit) throws IllegalArgumentException{
		
		if(digit > 9 || digit <= -1) {
			throw new IllegalArgumentException("invalid digit");
		}
		
		Node current = head;
		
		Number result = new Number("");
		Node resultCurrent = result.head;
		
		int carry = 0;
		int prod = 0;
		
		while(current != null) {
			prod = current.data * digit;
			
			if(carry != 0) {
				prod += carry;
			}
			
			Node num = new Node(prod % 10);
			
			carry = prod/10;
			
			if(result.head == null) {
				result.head = num;
				resultCurrent = num;
			}
			else {
				resultCurrent.next = num;
				resultCurrent = num;
			}	
			
			current = current.next;
		}
		
		if (carry != 0) {
	        Node num = new Node(carry);
	        resultCurrent.next = num;
	       
	    }
		
		return result;
		
	}
	
	@Override
	/*
	 * Returns the string representation of this object.
	 * 
	 * @returns string representation of this object
	 * 
	 */
	public String toString() {
		 if (head == null ) {
			 return ""; 
		 }
		 
		 Node current = head;
		 
		 StringBuilder sb = new StringBuilder();
	
		 
		 while (current != null) {
			 sb.append(current.data).append("");
		     current = current.next;
		 }

		 sb.reverse();

		 return sb.toString();
	}
}
