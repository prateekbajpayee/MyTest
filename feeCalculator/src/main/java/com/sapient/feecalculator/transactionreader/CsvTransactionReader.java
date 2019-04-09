package com.sapient.feecalculator.transactionreader;

import com.sapient.feecalculator.transactionreader.model.Transaction;

import java.io.*;

public class CsvTransactionReader extends AbstractTransactionReader implements  TransactionReader{

    public static final String DELIMITER = ",";

    public void readFile(File transactionFile) {
        String line = "";
        try(BufferedReader br = new BufferedReader(new FileReader(transactionFile))) {
            line = br.readLine();//Header
            while ((line = br.readLine()) != null) {
                String[] transactionAttributes = line.split(DELIMITER);
                Transaction transaction = getTransaction(transactionAttributes);
                saveTransaction(transaction);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
