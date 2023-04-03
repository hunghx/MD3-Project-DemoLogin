package ra.data;

import java.io.*;
import java.util.List;

public class FileMethod<T> {
   public List<T> readFromFile(String path){
       List<T> list = null;
       File file = null;
       FileInputStream fis =null;
       ObjectInputStream ois = null;
       try {
           file = new File(path);
           if (file.exists()){
               fis = new FileInputStream(file);
               ois = new ObjectInputStream(fis);
               list = (List<T>) ois.readObject();
           }
       }catch (Exception e) {
           e.printStackTrace();
       }finally {
           try {
               if(fis!=null){
                   fis.close();
               }
               if (ois!=null) {
                   ois.close();
                }
           }catch (IOException ioe){
               ioe.printStackTrace();
           }
       }
       return  list;
   }

   public boolean writeToFile(String path, List<T> list){
       File file = null;
       FileOutputStream fos = null;
       ObjectOutputStream oos = null;
       try {
           file = new File(path);
           fos = new FileOutputStream(file);
           oos = new ObjectOutputStream(fos);
           oos.writeObject(list);
       }catch (Exception e) {
           e.printStackTrace();
           return false;
       }finally {
           try {
               if(fos!=null){
                   fos.close();
               }
               if (oos!=null) {
                   oos.close();
               }
           }catch (IOException ioe){
               ioe.printStackTrace();
           }
       }
       return true;
   }
}
