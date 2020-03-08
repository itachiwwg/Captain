package slf.captain;

import slf.captain.service.FrontServer;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        FrontServer fs = new FrontServer();
        fs.start();
    }
}
