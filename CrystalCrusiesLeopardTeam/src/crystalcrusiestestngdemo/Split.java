package crystalcrusiestestngdemo;

import crystalcrusiestestng.CallingClassName;

public class Split {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
				CallingClassName p = new CallingClassName();
				p.print();
		
		
		String a = "baksar10576@gmail.com,baskarsek@gmail.com";
		
		String[] str = a.split(",");
		
		for(int i=0;i<str.length;i++){
			System.out.println(str[i]);
		}

	}

}