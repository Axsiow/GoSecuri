import java.io.IOException;
import java.io.*;
import java.util.*;

public class main {
	public static void main(String[] args) throws IOException {

		//test vers temp
		FileReader lecteur;
		FileWriter ecrivain;

		lecteur = new FileReader("ressource/test.txt");
		ecrivain = new FileWriter("ressource/temp.txt");

		BufferedReader reader = new BufferedReader(lecteur);
		String s;
		String[] tab;
		while((s = reader.readLine()) != null) {
			/*
			tab = s.split("\t");
			List<String> res = Arrays.asList(tab);
			System.out.println(res);
			*/
			ecrivain.write(s + "<br>" + "\n");
		}
		reader.close();
		lecteur.close();
		ecrivain.close();

		//supp de html
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("src/test_copie.html", false)));
		pw.println("");
		pw.close();

		//temp vers html
		int c;

		lecteur = new FileReader("ressource/temp.txt");
		ecrivain = new FileWriter("src/test_copie.html");
		ecrivain.write("<!DOCTYPE html>\n<html>\n\t<head>\n\t\t<meta charset='utf-8' />\n\t\t<title>Titre</title>\n\t</head>\n\t<body>\n"); //ecrire le début du fichier html
		while((c = lecteur.read()) != -1) {
			ecrivain.write(c);
		}
		ecrivain.write("\n\t</body>\n</html>"); //ecrire la fin du fichier html
		lecteur.close();
		ecrivain.close();

		//del temp file
		pw = new PrintWriter(new BufferedWriter(new FileWriter("ressource/temp.txt", false)));
		pw.println("");
		pw.close();
	}

}