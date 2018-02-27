package data_structures;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListI <Integer> myList = new LinkedList<Integer>();
		Integer obj = new Integer("7");
		int n = 10;
		int x;
		for(int i =0; i<n;i++){
			myList.addFirst(i);
		}
		for(int i=n-1; i>=0; i--){
			 x = myList.removeFirst();
			if(myList.contains(obj))
				System.out.println(x);
		}
	}

	//int x;
	//for(int i=0; i<n;i++)
	//	x = myList.removeLast();

}



