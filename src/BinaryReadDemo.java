import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class BinaryReadDemo {

    public static void main(String[] args) {
        String fileName = "binary.dat";
        ObjectInputStream inStreamName = null;

        try {

            inStreamName = new ObjectInputStream(new FileInputStream(fileName));

            // Read 10 int
            System.out.println("Read 10 int");
            for (int i = 1; i <= 9; i++) {
                System.out.print(inStreamName.readInt() + " ");
            }
            System.out.println("\nRead 10 bytes");
            // Read 10 bytes
            for (int i = 1; i <= 10; i++) {
                System.out.print(inStreamName.readByte() + " ");
            }
            System.out.println("\nRead 10 doubles");
            // Read 10 doubles
            for (int i = 1; i <= 10; i++) {
                System.out.print(inStreamName.readDouble() + " ");
            }
            // Read 2 Strings
            System.out.println("\nRead 2 Strings");
            System.out.print(inStreamName.readUTF() + " ");
            System.out.print(inStreamName.readUTF());

            inStreamName.close();

        } catch (
                IOException e) {
            e.printStackTrace();
            System.exit(0);

        }
    }
}
