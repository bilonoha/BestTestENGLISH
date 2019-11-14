package best.test.english;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import android.app.Activity;


public class CSVReader{
	public Scanner s;
	public ArrayList<String[]> list = new ArrayList<String[]>();
	
	CSVReader (String filesDir) throws FileNotFoundException {
//	s = new Scanner(new File("data/data/best.test.english/files/test1.csv"));//
	s = new Scanner(new File(filesDir+"/data.csv"));
	while (s.hasNextLine()){
	    list.add(s.nextLine().split("`"));
	}
	s.close();
	}
}
