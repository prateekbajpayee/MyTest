package com.sapient.feecalculator.transactionreader;

import java.io.File;

public interface TransactionReader {
    public void displayTransactionReport();

    public void readFile(File transactionFile);

}
