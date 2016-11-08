package com.steven.io; /**
 * Created by Steven on 2016/11/7.
 */

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

public class CreatFile {
    private static File f = null;
    private static File out = null;
    private static File objectFile = null;
    public void creatFile(){
        f = new File(Constants.PATH);
        out = new File(Constants.OUT_PATH);
        objectFile = new File(Constants.OBJECT_PATH);
        try {
            f.createNewFile();
            out.createNewFile();
            objectFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void computeByteStream(){
        //compute size of Byte Stream
        byte[] buffer = new byte[6];   //Byte buffer  512bytes
        int number = 0;
        InputStream streamReader = null;
        OutputStream streamWriter = null;
        try {
            streamReader = new FileInputStream(f);
            streamWriter = new FileOutputStream(out);
            while((number = streamReader.read(buffer))!=-1){
                streamWriter.write(buffer, 0 ,number);
            }
            streamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                streamReader.close();
                streamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void objectInputOutput(){
        // Object inputStream outputStream
        ObjectOutputStream objectWriter = null;
        ObjectInputStream objectReader = null;
        try {
            objectWriter = new ObjectOutputStream(new FileOutputStream(objectFile));
            objectWriter.writeObject(new Student("李asd", 10));
            objectWriter.writeObject(new Student("zhangqwe", 20));
            objectWriter.writeObject(new Student("ye", 30));

            objectReader = new ObjectInputStream(new FileInputStream(objectFile));
            for (int i = 0; i < 3; i++) {
                System.out.println(objectReader.readObject());
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                objectWriter.close();
                objectReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void objectDataReadAndWrite(){

        Student[] students = {new Student("A",1),new Student("B",2),new Student("C",3)};
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(objectFile));

            for (Student student:students) {
                dataOutputStream.writeUTF(student.getName());
                dataOutputStream.writeInt(student.getAge());
                System.out.println(student.getName() + student.getAge());
            }
            dataOutputStream.flush();
            dataOutputStream.close();

            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(objectFile));
            for (int i = 0; i < students.length; i++) {
                String name = dataInputStream.readUTF();
                int age = dataInputStream.readInt();
                students[i] = new Student(name, age);
            }
            dataInputStream.close();


            for (Student student:students) {
                System.out.println(student.getName() + student.getAge());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void pushbackStream(){
        String str = new String("Hello,World!");
        PushbackInputStream pushbackInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        pushbackInputStream = new PushbackInputStream(byteArrayInputStream);
        int index = 0;
        try {
            while((index = pushbackInputStream.read()) != -1){
                if(index == ','){
                    pushbackInputStream.unread(index);
                    index = pushbackInputStream.read();
                    System.out.println((char)index);
                }else {
                    System.out.print((char)index);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sequeceStream(){
        SequenceInputStream sequenceInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            Vector<InputStream> vector = new Vector<InputStream>();
            vector.addElement(new FileInputStream(f));
            vector.addElement(new FileInputStream(out));
            vector.addElement(new FileInputStream(objectFile));
            Enumeration<InputStream> enumeration = vector.elements();
            sequenceInputStream = new SequenceInputStream(enumeration);

            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(out));

            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = sequenceInputStream.read()) != -1)
            {
                bufferedOutputStream.write(buffer,0,len);
                bufferedOutputStream.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                sequenceInputStream.close();
                bufferedOutputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void run(){
        CreatFile cf = new CreatFile();
        cf.creatFile();
        cf.computeByteStream();
        cf.objectInputOutput();
        cf.objectDataReadAndWrite();
        cf.pushbackStream();
        cf.sequeceStream();
    }
}

// class Student for usage
class Student implements Serializable{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String name ,int age){
        super();
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString(){
        return name + age;
    }
}