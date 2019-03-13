/* AppendingObjectOutputStream.java - Used to append to existing ObjectOutputStream
 * Author:     StackOverflow website
 * Module:     6
 * Project:    Demonstration
 * Problem Statement: Create an ObjectOutputStream child which can be used to
 *    append to an existing ObjectOutputStream.
 *
 * Description:
 *
 *   Provides override to the writeStreamHeader method which prevents a new
 *   header from being added to the existing stream.
 */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendingObjectOutputStream extends ObjectOutputStream {

    public AppendingObjectOutputStream (OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }
}
