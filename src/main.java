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


        //création de index.html
        new FileOutputStream("ressource/web/index.html", false).close();

		//temp vers index.html
		int c;

		lecteur = new FileReader("ressource/temp.txt");
		ecrivain = new FileWriter("ressource/web/index.html");

		ecrivain.write("<!DOCTYPE html>\n<html>\n\t<head>\n\t\t<meta charset='utf-8' />\n\t\t<title>Accueil</title>\n\t\t<link rel=\"stylesheet\" href=\"style.css\">\n\t\t<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>\n\t</head>\n\t<body>\n"); //ecrire le début du fichier index.html
		ecrivain.write("\n<div id='logo'>\n\t<img id='gosecuri' src=\"../jpg/GoSecuri.PNG\" alt=\"logo of GoSecuri\">\n</div>\n<div id='agents'>");
		while((c = lecteur.read()) != -1) {
			ecrivain.write(c);
		}
		ecrivain.write("\n</div>\n\t</body>\n</html>"); //ecrire la fin du fichier html
		lecteur.close();
		ecrivain.close();

		//del temp file
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ressource/temp.txt", false)));
		pw.println("");
		pw.close();

	}

	//fonction qui genere le html dans les fiches agents
	public static void createStaffHtml(String page){
		try {
			FileReader lecteur1 = new FileReader("ressource/txt/"+page+".txt");
			FileReader lecteur2 = new FileReader("ressource/liste.txt");
			FileWriter ecrivain = new FileWriter("ressource/web/"+page+".html");
			BufferedReader reader = new BufferedReader(lecteur1);
			String s;
			String str;
			int count = 0;


			ecrivain.write("<!DOCTYPE html>\n<html>\n\t<head>\n\t\t<meta charset='utf-8' />\n\t\t<title>"+page+"</title>\n\t\t<link rel=\"stylesheet\" href=\"style.css\">\n\t\t<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>\n\t</head>\n\t<body>\n"); //ecrire le début du fichier html

			ecrivain.write("<h1>");
			while((s = reader.readLine()) != null && count<2) {
				ecrivain.write(s + "\n");
				count++;
			}
			ecrivain.write("</h1>");
			ecrivain.write("\n<div id='idCard'>\n\t<img"+" src=\"../jpg/"+page+".jpg\""+" alt=\"ID card of "+page+"\">\n</div>");


			BufferedReader haha = new BufferedReader(lecteur2);
			Map<String,String> listeHtml = new HashMap<String,String>();
			while((str = haha.readLine()) != null)
			{
				listeHtml.put(str.split("\t")[0], str.split("\t")[1]);
			}

			count = 0;
            ArrayList outils = new ArrayList();

			for(String i : listeHtml.keySet()) {

                while ((s = reader.readLine()) != null) {

                    if (count > 1) {
                        if (listeHtml.get(s) != null) {
                            outils.add(listeHtml.get(s));
                        }
                    }
                    count++;
                }
				ecrivain.write("<div id='outils'>");
                    if (outils.contains(listeHtml.get(i))){
                        ecrivain.write("\n" +
                                "<div>\n" +
								"  <label for=\"scales\">" + listeHtml.get(i) + "</label>\n" +
                                "  <input type=\"checkbox\" id=\"" + listeHtml.get(i) + "\" onclick=\"return false\" name=\"" + listeHtml.get(i) + "\"\n" +
                                "         checked>\n" +
                                "</div>\n" +
                                "\n"
                        );
                    }else{
                        ecrivain.write("\n" +
                                "<div>\n" +
								"  <label for=\"scales\">"+listeHtml.get(i)+"</label>\n" +
                                "  <input type=\"checkbox\" id=\""+listeHtml.get(i)+"\" onclick=\"return false\" name=\""+listeHtml.get(i)+"\"\n" +
                                "</div>\n" +
                                "\n"
                        );
                    }
				ecrivain.write("</div>");

			}

			ecrivain.write("\n\t</body>\n</html>"); //ecrire la fin du fichier html

			ecrivain.close();
			lecteur1.close();
			lecteur2.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}