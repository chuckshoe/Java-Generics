// JAVA Programming Assignment

import java.util.* ;

interface Sorted<T> extends List<T>  {
	Sorted<T> merge (Sorted<T> a); // to merge 2 sorted lists to 1
} 

class SortedList<T extends Comparable<T>> extends ArrayList<T>
					implements Sorted<T>, Comparable<SortedList<T>> {
	
	public boolean add(T element) {
		int i;
		for(i=0; i< this.size(); i++){
			if (element.compareTo( this.get( i ) ) == -1){
				
				break;
			}
		}
		super.add(i,element);
		return true;
	}
	
	@Override
	public int compareTo (SortedList<T> l){
		int flag = 0; //assume this list are equal
		int len1 = this.size();
		int len2 = l.size();
		int i,size ;
		
		if (len1 < len2) size = len1;
		else size = len2;
		
		for(i = 0; i < size; i++){
				if (this.get(i).compareTo(l.get(i) )== (-1) ){
					flag = -1;
					break;
				}
				else if (this.get(i).compareTo(l.get(i) ) == (1)){
					flag = 1;
					break;
				}
		} 
		if (flag == -1) return -1;
		else if (flag == 1) return 1;
		else{
			if (len1 > len2) return 1;
			else if (len1 < len2)return -1;
         else return 0 ;
		}			
	}
	
	public Sorted<T> merge (Sorted<T> l){
		int len1 = this.size();
		int len2 = l.size() ;
		Sorted<T> temp = new SortedList<T>();
		int i = 0;
		int j = 0;
		int k = 0;
		
		while ((i<len1) && (j<len2)){
			if (this.get(i).compareTo(l.get(j)) == -1){
				temp.add(this.get(i));
				i++ ;
				k++ ;
				
			}
			else {
				temp.add(l.get(j));
				j++ ;
				k++;
			}			
		}
		while (i < len1) {
			temp.add(this.get(i));
			i++ ;
			k++ ;
		}
		while (j < len2) {
			temp.add(l.get(j));
			j++ ;
			k++ ;
		}

		return temp;

	}	
	
}

class A implements Comparable<A> {
	Integer x;
	Integer y;
	
	A (Integer x, Integer y){
		this.x = x;
		this.y = y;
	}
	
	public int getSum(){
		return (x.intValue() + y.intValue());
	}
	
	@Override
	public int compareTo(A o) {
		
		if (this.getSum() > o.getSum()) return 1;
		else if (this.getSum() == o.getSum()) return 0;
		else return -1 ;	
		
	}
	public String toString(){
		return "A<" + x + "," + y +">";
	}

}

class B extends A {
	 Integer z;
	
	B (Integer x, Integer y,Integer z){
		super(x,y);
		this.z = z;
	}
	
	public int getSum(){
		return (x.intValue() + y.intValue() +z.intValue());
	}
	public String toString(){
		return "B<" + x + "," + y +","+ z +">";
	}
	
} 

public class Part1 {

    static <T extends Comparable<T>> void addToSortedList(SortedList<T> L , T z){
    	L.add(z) ;
    }
    
	static void test() {
	SortedList<A> c1 = new SortedList<A>();
	SortedList<A> c2 = new SortedList<A>();
	for(int i = 35; i >= 0; i-=5) {
	    addToSortedList(c1, new A(i,i+1));
	    addToSortedList(c2, new B(i+2,i+3,i+4));
	}
	
	System.out.print("c1: ");
	System.out.println(c1);
	
	System.out.print("c2: ");
	System.out.println(c2);

	switch (c1.compareTo(c2)) {
	case -1: 
	    System.out.println("c1 < c2");
	    break;
	case 0:
	    System.out.println("c1 = c2");
	    break;
	case 1:
	    System.out.println("c1 > c2");
	    break;
	default:
	    System.out.println("Uh Oh");
	    break;
	}

	Sorted<A> res = c1.merge(c2);
	System.out.print("Result: ");
	System.out.println(res);
   }
	
	public static void main(String[] args) {
		
		test();

	}

}
