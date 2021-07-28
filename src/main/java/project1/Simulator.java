package project1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Simulator {
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("db.properties");
			System.out.println(fis.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
