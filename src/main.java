import java.io.IOException;
import java.io.*;
import java.util.*;

public class main {
	public static void main(String[] args) throws IOException {

		//staff vers temp

		FileReader lecteur;
		FileWriter ecrivain;

		lecteur = new FileReader("ressource/staff.txt");
		ecrivain = new FileWriter("ressource/temp.txt");

		BufferedReader reader1 = new BufferedReader(lecteur);
		String s;
		while((s = reader1.readLine()) != null) {
			ecrivain.write("<a href='"+s+".html'>" + s + "</a><br>" + "\n");
			//création des pages staff
			new FileOutputStream("ressource/web/"+s+".html", false).close();
			createStaffHtml(s);
		}
		reader1.close();
		lecteur.close();
		ecrivain.close();

		//supp de accueil.html

		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ressource/web/accueil.html", false)));
		pw.println("");
		pw.close();

		//temp vers accueil.html
		int c;

		lecteur = new FileReader("ressource/temp.txt");
		ecrivain = new FileWriter("ressource/web/accueil.html");

		ecrivain.write("<!DOCTYPE html>\n<html>\n\t<head>\n\t\t<meta charset='utf-8' />\n\t\t<title>Accueil</title>\n\t</head>\n\t<body>\n"); //ecrire le début du fichier html
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

		//__________________________________________________________________
/*
		//test vers temp
		FileReader lecteur;
		FileWriter ecrivain;

		lecteur = new FileReader("ressource/test.txt");
		ecrivain = new FileWriter("ressource/temp.txt");

		BufferedReader reader = new BufferedReader(lecteur);
		String s;
		String[] tab;
		while((s = reader.readLine()) != null) {

			tab = s.split("\t");
			List<String> res = Arrays.asList(tab);
			System.out.println(res);

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

 */

	}
	public static void createStaffHtml(String page){
		try {
			FileReader lecteur1 = new FileReader("ressource/txt/"+page+".txt");
			FileReader lecteur2 = new FileReader("ressource/liste.txt");
			FileWriter ecrivain = new FileWriter("ressource/web/"+page+".html");
			BufferedReader reader = new BufferedReader(lecteur1);
			String s;
			String v;
			int count = 0;

			ecrivain.write("<!DOCTYPE html>\n<html>\n\t<head>\n\t\t<meta charset='utf-8' />\n\t\t<title>"+page+"</title>\n\t</head>\n\t<body>\n"); //ecrire le début du fichier html

			while((s = reader.readLine()) != null && count<2) {
				ecrivain.write(s + "<br>" + "\n");
				count++;
			}
			ecrivain.write("\n<img"+" src=\"../jpg/"+page+".jpg\""+" alt=\"ID card of "+page+"\">");
			count = 0;

			System.out.println(new BufferedReader(lecteur2).readLine());
/*
			while((v = new BufferedReader(lecteur2).readLine()) != null) {
				List<String> res = Arrays.asList(v.split("\t"));
				System.out.println(res);

				while ((s = reader.readLine()) != null) {
					if (count > 4) {
						if (true) {
							ecrivain.write("" +
									"<div>\n" +
									"  <input type=\"checkbox\" id=\"scales\" onclick=\"return false\" name=\"scales\"\n" +
									"         checked>\n" +
									"  <label for=\"scales\">Scales</label>\n" +
									"</div>\n" +
									"\n"
							);
						} else {
							ecrivain.write("" +
									"<div>\n" +
									"  <input type=\"checkbox\" id=\"scales\" onclick=\"return false\" name=\"scales\"\n" +
									"  <label for=\"scales\">Scales</label>\n" +
									"</div>\n" +
									"\n"
							);
						}
					}
				}
			}*/
			//new BufferedReader(lecteur)

			ecrivain.write("\n\t</body>\n</html>"); //ecrire la fin du fichier html

			ecrivain.close();
			lecteur1.close();
			lecteur2.close();

		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

}