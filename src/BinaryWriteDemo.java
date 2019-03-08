import java.io.*;

public class BinaryWriteDemo {

    public static void main(String[] args) {

        String fileName = "binary.dat";
        ObjectOutputStream outStreamName = null;

        try {

            outStreamName = new ObjectOutputStream(new FileOutputStream(fileName));

            // Write 10 int
            for (int i = 1; i <= 10; i++) {
                outStreamName.writeInt(i * 10);
            }
            // Write 10 bytes
            for (int i = 1; i <= 10; i++) {
                outStreamName.writeByte(i * 5);
            }
            // Write 10 doubles
            for (int i = 1; i <= 10; i++) {
                outStreamName.writeDouble(i * -1.0);
            }
            // Write 2 Strings
            outStreamName.writeUTF("Will");
            outStreamName.writeUTF("Cray");

            outStreamName.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
