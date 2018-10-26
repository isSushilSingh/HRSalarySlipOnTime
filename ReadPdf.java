import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import java.util.*;
import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ReadPdf {
	public static void main(String args[]) throws IOException {
		try{
			File folder = new File("PDFs");
			File[] listOfFiles = folder.listFiles();
			String[] pdfFiles=new String[listOfFiles.length];
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					pdfFiles[i]=listOfFiles[i].getName();
				}
			}for(String pstr : pdfFiles){
				System.out.println(pstr);
				String pattern = "[E][P][S][0-9][0-9][0-9]";
				Pattern r = Pattern.compile(pattern);
				File oldFile = new File("PDFs\\"+pstr);
				PDDocument document = PDDocument.load(oldFile);// here file1.pdf is the name of pdf file which we want to read....
				document.getClass();
				if (!document.isEncrypted()){
					String textToPrint="";
					PDFTextStripperByArea stripper = new PDFTextStripperByArea();
					stripper.setSortByPosition(true);
					PDFTextStripper Tstripper = new PDFTextStripper();
					String str = Tstripper.getText(document);
					Scanner scn = null;					
					scn = new Scanner(str);
					String line="";
					while (scn.hasNextLine()) {
						Matcher m = r.matcher(line);
						if (m.find( )) {
							document.close();
							System.out.println("Found value: " + line);
							File newFile=new File("PDFs\\"+line+".pdf");
							oldFile.renameTo(newFile);
							break;
						}//textToPrint+="\n"+line;
						line = scn.nextLine();
						//System.out.println("\n"+line);
					}//System.out.println(textToPrint);
				}document.close();	
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}