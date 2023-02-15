package connect;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class NBPConnector {

    private URL address;
    private URLConnection connection;
    private InputStream inputStream;

    public NBPConnector(String address) {
        try {
            this.address = new URL(address);
            this.connection = this.address.openConnection();
            this.inputStream = this.connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
