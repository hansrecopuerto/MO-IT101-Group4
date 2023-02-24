package model;

import java.io.File;
import java.util.Properties;
import java.util.Scanner;

public class SssBracket {
    
    public Properties sss_range_contribution = new Properties();
    
    public Properties init_prop() throws Exception {
        Scanner sss_object = new Scanner(new File("MO-IT101-Group4//src/utils/sss_bracket.csv"));
        sss_object.useDelimiter(",");
        sss_object.nextLine();
        String[] sss_bracket = new String[10];
        while (sss_object.hasNext()) {
            sss_bracket = sss_object.nextLine().split(",");
            this.sss_range_contribution.setProperty(sss_bracket[0] + "-" + sss_bracket[1], sss_bracket[2]);
            // System.out.println(this.sss_range_contribution);
        }

        return this.sss_range_contribution;
    }
}
