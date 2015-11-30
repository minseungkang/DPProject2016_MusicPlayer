import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FileIO {
   public String[] readTextFile(String fileAddress, String fileName){
      
      int lineNum = readLine(fileAddress,fileName+".txt");
      String[] information = new String[lineNum];
      
      lineNum = 0;
      
      String readLine = new String("");
      File file = new File(fileAddress,fileName+".txt");
      BufferedReader input ;
      
      try {
         input = new BufferedReader( new InputStreamReader( 
               new FileInputStream(fileAddress+"//"+fileName+".txt"), "UTF-8"));;
      }
      
      catch(Exception e) {
         try {
            FileWriter fw = new FileWriter(file);
            fw.close();
         } 
         catch (IOException e1) {
            e1.printStackTrace();
         }
         return null;
      }
      System.out.println(fileAddress);

      try {
         while((readLine = input.readLine()) != null){
            
            System.out.println(readLine + "1");
            if(readLine.startsWith("//")||(readLine.trim()).equals("")) continue; // �ּ� �Ǵ� �� ��
            else{
               information[lineNum] = readLine;
            
               lineNum++;
            }
         }
      } catch (IOException e) { e.printStackTrace(); }
      
      try {
         input.close();
      } catch (IOException e) {e.printStackTrace();}
      
      return information;
   }
   public void writeTextFile(String fileInformationAddress, String fileName, String[] writeInformation,final String delimiter){
      try {
         File file = new File(fileInformationAddress,fileName+".txt");
         BufferedWriter writer = new BufferedWriter(new FileWriter(file));
         for(int i=0;i<writeInformation.length;i++){
            writer.write(writeInformation[i]);
            writer.write(delimiter);
         }
         writer.close();
      
      } catch (IOException e) {
         
         e.printStackTrace();
      }
   }
   
   public String[] readMusicFile(String fileAddress, String fileName){
      
      String[] information ={};
      String fileLocation = fileAddress+"\\"+fileName+".mp3";
      
      try {
   
         InputStream input = new FileInputStream(new File(fileLocation));
         ContentHandler handler = new DefaultHandler();
         Metadata metadata = new Metadata();
         Parser parser = new Mp3Parser();
         ParseContext parseCtx = new ParseContext();
         parser.parse(input, handler, metadata, parseCtx);
         input.close();
         // List all metadata
         String[] metadataNames = metadata.names();
         
         for(String name : metadataNames){
            System.out.println(name + ": " + metadata.get(name));
         }
         
         // Retrieve the necessary info from metadata
         // Names - title, xmpDM:artist etc. - mentioned below may differ based
   
      } 
      catch (FileNotFoundException e) { e.printStackTrace(); } 
      catch (IOException e) { e.printStackTrace(); } 
      catch (SAXException e) { e.printStackTrace(); } 
      catch (TikaException e) { e.printStackTrace(); }
      
      return information;
   }
   
   private int readLine(String fileAddress, String fileName){
      int lineNum = 0;

      String inputLine = new String("");
      File file = new File(fileAddress,fileName);
      Scanner input ;
      try {
         input = new Scanner(file);
      }
      
      catch(Exception e) {
         try {
            FileWriter fw = new FileWriter(file);
            fw.close();
         } 
         catch (IOException e1) {
            e1.printStackTrace();
         }
         return 0;
      }
      
      while(input.hasNext()){
         inputLine = input.nextLine();
         if(inputLine.startsWith("//")||(inputLine.trim()).equals("")) continue; // �ּ� �Ǵ� �� ��
         lineNum++;
      }
      input.close();

      return lineNum;
   }
   
   public void reNameTextFile(String fileAddress, String fileName, String newName){
      reNameFile(fileAddress, fileName, newName, ".txt");
   }
   public void reNameMP3File(String fileAddress, String fileName, String newName){
      reNameFile(fileAddress, fileName, newName, ".mp3");
   }
   private void reNameFile(String fileAddress, String fileName, String newName, String type){
      String path = fileAddress;
      File oldfile =new File(path,fileName+".txt");
      File newfile =new File(path,newName+".txt");
      oldfile.renameTo(newfile);
   }
}

