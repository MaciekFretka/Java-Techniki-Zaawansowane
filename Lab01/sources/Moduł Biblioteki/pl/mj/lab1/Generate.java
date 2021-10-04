package pl.mj.lab1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Generate {

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

    public void generate(String path) throws IOException, NoSuchAlgorithmException {
        Path file = (new File(path+"\\").toPath());

        Path filesList = Paths.get(path+"/listoffiles.txt");
        Path md5List = Paths.get(path+"/backap.txt");
     if(Files.exists(filesList)) Files.delete(filesList);
     if(Files.exists(md5List)) Files.delete(md5List);

        Files.walk(Paths.get(path),1).forEach(filein->{
           if(Files.isDirectory(filein)){

            }else{
                String name=filein.getFileName().toString();
                if(!name.equals("backap.txt") && !name.equals("listoffiles.txt") && !name.equals(file.getFileName().toString() )){
                    try {
                        Files.writeString(filesList, name+"\n", StandardOpenOption.APPEND,StandardOpenOption.CREATE);
                        MessageDigest md5 = MessageDigest.getInstance("MD5");
                        String checksum = getFileChecksum(md5,filein.toFile());
                        Files.writeString(md5List,name+":"+checksum+"\n",StandardOpenOption.APPEND,StandardOpenOption.CREATE);
                    } catch (IOException | NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
            }




        });

    }

}
