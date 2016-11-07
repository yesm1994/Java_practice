/**
 * Created by Steven on 2016/11/7.
 */
import java.io.*;
public class CreatFile {
    private static final String PATH = "F:/file/temp.txt";
    private static final String OUT_PATH = "F:/file/out.txt";
    private static final String OBJECT_PATH = "F:/Object/Student.txt";
    private static File f = null;
    private static File out = null;
    private static File objectFile = null;
    public void creatFile(){
        f = new File(PATH);
        out = new File(OUT_PATH);
        objectFile = new File(OBJECT_PATH);
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

    public void 

    public static void run(){
        CreatFile cf = new CreatFile();
        cf.creatFile();
        cf.computeByteStream();
        cf.objectInputOutput();
        cf.objectDataReadAndWrite();
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