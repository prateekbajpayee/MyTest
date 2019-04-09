package com.sapient.feecalculator;


import com.sapient.feecalculator.transactionreader.TransactionReader;
import com.sapient.feecalculator.transactionreader.TransactionReaderFactory;

import java.io.File;

public class FeeCalculator {
    public static void main(String[] args) throws Exception {
        File transactionFile = new File(new File("").getAbsolutePath(),"src/main/resources/SampleData.csv");
        TransactionReader transactionReader = TransactionReaderFactory.getTrasactionReaderInstance(Constants.FILE_TYPE.CSV);
        transactionReader.readFile(transactionFile);
        transactionReader.displayTransactionReport();
    }


}
