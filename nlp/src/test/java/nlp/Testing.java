package nlp;

import java.util.Scanner;

import module.db.MySQLDatabase;
import module.main.Parser;

public class Testing {

	public static void main(String[] args){
		
		Scanner s = new Scanner(System.in);
		System.out.print("Masukkan pertanyaan: ");
		String sentence = s.nextLine();
		s.close();
		
		Parser p = new Parser(new MySQLDatabase("root", "akinaja"), sentence);
		
		System.out.println(p.getParseTree());
	}
	
}
