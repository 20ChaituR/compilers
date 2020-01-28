
/*
 * Created by cravuri on 1/27/20
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws ScanErrorException, FileNotFoundException {
        FileInputStream inStream = new FileInputStream(new File("ScannerTest.txt"));
        Scanner sc = new Scanner(inStream);
        while (sc.hasNext()) {
            System.out.println(sc.nextToken());
        }
    }

}
