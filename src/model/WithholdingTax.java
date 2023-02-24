package model;

import java.io.File;
import java.util.Properties;
import java.util.Scanner;

public class WithholdingTax {
    public Properties withholding_tax_range_contribution = new Properties();
    
    public Properties init_prop() throws Exception {
        Scanner withholding_tax_bracket = new Scanner(new File("MO-IT101-Group4//src/utils/withholding_tax_bracket.csv"));
        withholding_tax_bracket.useDelimiter(",");
        withholding_tax_bracket.nextLine();
        String[] withholding_tax = new String[10];
        while (withholding_tax_bracket.hasNext()) {
            withholding_tax = withholding_tax_bracket.nextLine().split(",");
            this.withholding_tax_range_contribution.setProperty(withholding_tax[0], withholding_tax[1] + "-" + withholding_tax[2]);
            // System.out.println(this.sss_range_contribution);
        }

        return this.withholding_tax_range_contribution;
    }
}
