package pl.mj.lab1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Check {

    private static String getFileChecksum(MessageDigest dig, File file) throws IOException
    {
        FileInputStream inputfile = new FileInputStream(file);

        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        while ((bytesCount = inputfile.read(byteArray)) != -1){
            dig.update(byteArray,0,bytesCount);
        };

        inputfile.close();

        byte[] bytes = dig.digest();
        StringBuilder stringbuilder = new StringBuilder();
        for(int i=0;i<bytes.length; i++)
        {
            stringbuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100,16).substring(1));
        }
        return stringbuilder.toString();
    }

    public List<String> check(String path) throws IOException, NoSuchAlgorithmException {
        List<String> logs = new ArrayList<String>();

        Path filesList = Paths.get(path+"/listoffiles.txt");
        Path md5List = Paths.get(path+"/backap.txt");


        BufferedReader reader = Files.newBufferedReader(filesList, Charset.forName("UTF-8"));
        String filename= null;
        while((filename=reader.readLine()) != null){
         //   System.out.println(filename);
            BufferedReader readermd5 = Files.newBufferedReader(md5List,Charset.forName("UTF-8"));
            String currentLine=null;
            while((currentLine=readermd5.readLine()) != null){
                if(currentLine.startsWith(filename+":")){


                    String checksumfrombackap = currentLine.substring(filename.length()+1);

                    Path CheckedFile = Paths.get(path+"/"+filename);
                    if(Files.exists(CheckedFile)){
                        MessageDigest md5 = MessageDigest.getInstance("MD5");
                        String realchecksum = getFileChecksum(md5,CheckedFile.toFile());

                        if(!realchecksum.equals(checksumfrombackap)) logs.add("Plik: "+filename+" został zmodyfikowany");

                    }else{
                        //System.out.println("Plik: "+filename+" został usunięty lub zmieniono jego nazwę");
                        logs.add("Plik: "+filename+" został usunięty lub zmieniono jego nazwę");
                    }

                }
            }

        }
        return logs;
    }
}
