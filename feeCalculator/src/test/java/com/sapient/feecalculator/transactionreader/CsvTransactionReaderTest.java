package com.sapient.feecalculator.transactionreader;

import com.sapient.feecalculator.transactionreader.model.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class CsvTransactionReaderTest {
    // TODO Create TransactionBuilder

    @Before
    public void createOutputFile()
    {

    }

    @Test //Two transactions have buy and sell in the same day
    public void isTransactionIntraday(){
        CsvTransactionReader csvTransactionReader = new CsvTransactionReader();
        Transaction transaction1 = new Transaction();
        transaction1.setClientId("DUMMY");
        transaction1.setClientId("DUMMY");
        transaction1.setSecurityId("DUMMY");
        transaction1.setPriority(false);
        transaction1.setTransactionType(2);
        Date date = new Date();
        transaction1.setTransactionDate(date);
        csvTransactionReader.saveTransaction(transaction1);
        Transaction transaction2 = new Transaction();
        transaction2.setClientId("DUMMY");
        transaction2.setClientId("DUMMY");
        transaction2.setSecurityId("DUMMY");
        transaction2.setTransactionDate(date);
        transaction2.setPriority(false);
        transaction2.setTransactionType(1);
        Assert.assertEquals(csvTransactionReader.isIntraDayTransaction(transaction2),true);
    }

    @Test
    public void isTransactionIntradayFalse(){
        CsvTransactionReader csvTransactionReader = new CsvTransactionReader();
        Transaction transaction1 = new Transaction();
        transaction1.setClientId("DUMMY");
        transaction1.setClientId("DUMMY");
        transaction1.setSecurityId("DUMMY");
        transaction1.setPriority(false);
        transaction1.setTransactionType(2);
        Date date = new Date();
        transaction1.setTransactionDate(new Date());
        csvTransactionReader.saveTransaction(transaction1);
        Transaction transaction2 = new Transaction();
        transaction2.setClientId("DUMMY");
        transaction2.setClientId("DUMMY");
        transaction2.setSecurityId("DUMMY");
        transaction2.setTransactionDate(new Date());
        transaction2.setPriority(false);
        transaction2.setTransactionType(2);
        Assert.assertEquals(csvTransactionReader.isIntraDayTransaction(transaction2),false);
    }

    @Test
    public void calculateTransactionFeeForIntraday(){
        CsvTransactionReader csvTransactionReader = new CsvTransactionReader();
        Transaction transaction1 = new Transaction();
        transaction1.setClientId("DUMMY");
        transaction1.setClientId("DUMMY");
        transaction1.setSecurityId("DUMMY");
        transaction1.setPriority(false);
        transaction1.setTransactionType(2);//Type sell
        Date date = new Date();
        transaction1.setTransactionDate(date);
        csvTransactionReader.saveTransaction(transaction1);
        Transaction transaction2 = new Transaction();
        transaction2.setClientId("DUMMY");
        transaction2.setClientId("DUMMY");
        transaction2.setSecurityId("DUMMY");
        transaction2.setTransactionDate(date);
        transaction2.setPriority(false);
        transaction2.setTransactionType(1);
        Assert.assertEquals(csvTransactionReader.isIntraDayTransaction(transaction2),true);

        csvTransactionReader.calculateTransactionFee(transaction2);
        Assert.assertEquals(transaction2.getTransactionFees().toString(), "10.0");
    }

    @Test
    public void calculateTransactionFeeForPriority(){
        CsvTransactionReader csvTransactionReader = new CsvTransactionReader();
        Transaction transaction1 = new Transaction();
        transaction1.setClientId("DUMMY");
        transaction1.setClientId("DUMMY");
        transaction1.setSecurityId("DUMMY");
        transaction1.setPriority(true);
        transaction1.setTransactionType(2);
        Date date = new Date();
        transaction1.setTransactionDate(date);
        csvTransactionReader.saveTransaction(transaction1);
        csvTransactionReader.calculateTransactionFee(transaction1);
        Assert.assertEquals(transaction1.getTransactionFees().toString(), "500.0");
    }

    @Test
    public void calculateTransactionFeeForWithDrawl(){
        CsvTransactionReader csvTransactionReader = new CsvTransactionReader();
        Transaction transaction1 = new Transaction();
        transaction1.setClientId("DUMMY");
        transaction1.setClientId("DUMMY");
        transaction1.setSecurityId("DUMMY");
        transaction1.setPriority(false);
        transaction1.setTransactionType(3);
        Date date = new Date();
        transaction1.setTransactionDate(date);
        csvTransactionReader.saveTransaction(transaction1);
        csvTransactionReader.calculateTransactionFee(transaction1);
        Assert.assertEquals(transaction1.getTransactionFees().toString(), "50.0");
    }

    @Test
    public void calculateTransactionFeeForDeposit(){
        CsvTransactionReader csvTransactionReader = new CsvTransactionReader();
        Transaction transaction1 = new Transaction();
        transaction1.setClientId("DUMMY");
        transaction1.setClientId("DUMMY");
        transaction1.setSecurityId("DUMMY");
        transaction1.setPriority(false);
        transaction1.setTransactionType(4);
        Date date = new Date();
        transaction1.setTransactionDate(date);
        csvTransactionReader.saveTransaction(transaction1);
        csvTransactionReader.calculateTransactionFee(transaction1);
        Assert.assertEquals(transaction1.getTransactionFees().toString(), "100.0");
    }

}
