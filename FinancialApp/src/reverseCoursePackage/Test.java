package reverseCoursePackage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        String linkMatrix="https://www.google.pl/search?q=cad%2Fpln&source=hp&ei=ytyFYOnxM5acjLsPuL-r2AY&iflsig=AINFCbYAAAAAYIXq2rxXVUd3nkIKs-nOrCKHnNFgMJAc&oq=ars%2Fsgd&gs_lcp=Cgdnd3Mtd2l6EAMyBAgAEBMyBAgAEBMyCAgAEA0QHhATMggIABANEB4QEzIICAAQDRAeEBMyCAgAEA0QHhATMgYIABAeEBMyCAgAEA0QHhATMggIABANEB4QEzIICAAQDRAeEBM6CAgAELEDEIMBOgIIADoOCAAQsQMQgwEQxwEQowI6CwgAELEDEMcBEKMCOgUIABCxAzoLCC4QsQMQgwEQkwI6CAguELEDEIMBOgUILhCxAzoCCC46CAguELEDEJMCOggIABDHARCvAToICAAQsQMQyQM6BQgAEJIDOgQIABAeOgcIABDJAxAeOgYILhAKEBM6CggAEA0QChAeEBM6BggAEAoQHjoGCAAQCBAeOggIABAIEAoQHlDcMFjUSGD5SmgAcAB4AIABeogB-wWSAQMxLjaYAQCgAQGqAQdnd3Mtd2l6&sclient=gws-wiz&ved=0ahUKEwjp_piiqZrwAhUWDmMBHbjfCmsQ4dUDCAc&uact=5";
        int pointer=linkMatrix.indexOf('d');
        int pointer2=linkMatrix.indexOf('n');
        System.out.println("index d:"+pointer);
        System.out.println("index n:"+pointer2);
        String rad="usd";
        String mat="nok";
        String partI="https://www.google.pl/search?q=";
        String partII="%2F";
        String partIII="&source=hp&ei=ytyFYOnxM5acjLsPuL-r2AY&iflsig=AINFCbYAAAAAYIXq2rxXVUd3nkIKs-nOrCKHnNFgMJAc&oq=ars%2Fsgd&gs_lcp=Cgdnd3Mtd2l6EAMyBAgAEBMyBAgAEBMyCAgAEA0QHhATMggIABANEB4QEzIICAAQDRAeEBMyCAgAEA0QHhATMgYIABAeEBMyCAgAEA0QHhATMggIABANEB4QEzIICAAQDRAeEBM6CAgAELEDEIMBOgIIADoOCAAQsQMQgwEQxwEQowI6CwgAELEDEMcBEKMCOgUIABCxAzoLCC4QsQMQgwEQkwI6CAguELEDEIMBOgUILhCxAzoCCC46CAguELEDEJMCOggIABDHARCvAToICAAQsQMQyQM6BQgAEJIDOgQIABAeOgcIABDJAxAeOgYILhAKEBM6CggAEA0QChAeEBM6BggAEAoQHjoGCAAQCBAeOggIABAIEAoQHlDcMFjUSGD5SmgAcAB4AIABeogB-wWSAQMxLjaYAQCgAQGqAQdnd3Mtd2l6&sclient=gws-wiz&ved=0ahUKEwjp_piiqZrwAhUWDmMBHbjfCmsQ4dUDCAc&uact=5";
        String newlink=partI+rad+partII+mat+partIII;
        try {
            String classValue =("DFlfde SwHCTb").toLowerCase();
            Document doc = Jsoup.connect(newlink).get();
            String el= doc.getElementsByAttributeValue("class",classValue).get(0).text();
            System.out.println(el);
        }catch (IOException | NullPointerException e){
            JOptionPane.showMessageDialog(null,"Either connection failed, or can't find value, or incorrect data in the input fields!","Error message nr. 4",JOptionPane.ERROR_MESSAGE);
        }
    }


    }

