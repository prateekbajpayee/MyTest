package com.sapient.feecalculator.transactionreader;

import com.sapient.feecalculator.Constants;

public class TransactionReaderFactory {

    public TransactionReaderFactory(){}

    public static TransactionReader getTrasactionReaderInstance(Constants.FILE_TYPE filetype) throws Exception {
        if(filetype.equals(Constants.FILE_TYPE.CSV)){
            return new CsvTransactionReader();
        }else if(filetype.equals(Constants.FILE_TYPE.XML)){
            return new XMLTransactionReader();
        }else{
            throw new Exception("File type not found");
        }
    }

}
