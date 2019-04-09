package com.sapient.feecalculator.transactionreader;

import java.util.ArrayList;
import java.util.List;
import com.sapient.feecalculator.Constants.TRANSACTION_FEES;
import com.sapient.feecalculator.Constants.TRANSACTION_TYPE;
import com.sapient.feecalculator.Utils;
import com.sapient.feecalculator.transactionreader.model.Transaction;

public abstract class AbstractTransactionReader {

    public List<Transaction> transactionList;

    public void saveTransaction(Transaction transaction){
        if(transactionList==null){
            transactionList = new ArrayList<Transaction>();
        }
        transactionList.add(calculateTransactionFee(transaction));
    }

    public Transaction calculateTransactionFee(Transaction transaction) {
        if(isIntraDayTransaction(transaction)){
            transaction.setTransactionFees(TRANSACTION_FEES.TEN.getFees());
        } else {
            if(transaction.getPriority()){
                transaction.setTransactionFees(TRANSACTION_FEES.FIVE_HUNDRED.getFees());
            } else{
                if(transaction.getTransactionType() == TRANSACTION_TYPE.SELL.getType() ||
                        transaction.getTransactionType() == TRANSACTION_TYPE.WITHDRAW.getType()){
                    transaction.setTransactionFees(TRANSACTION_FEES.HUNDRED.getFees());
                } else if(transaction.getTransactionType() == TRANSACTION_TYPE.BUY.getType() ||
                        transaction.getTransactionType() == TRANSACTION_TYPE.DEPOSIT.getType()){
                    transaction.setTransactionFees(TRANSACTION_FEES.FIFTY.getFees());
                }
            }
        }
        return transaction;
    }

    public boolean isIntraDayTransaction(Transaction transaction) {
        boolean isIntraDayTransaction= false;
        Transaction temp = null;
        if(transactionList.size() > 0 ){
            for (Transaction trans : transactionList) {
                if(trans.getClientId().equals(transaction.getClientId())&&
                        trans.getSecurityId().equals(transaction.getSecurityId()) &&
                        trans.getTransactionDate().equals(transaction.getTransactionDate())){
                    if((trans.getTransactionType()==TRANSACTION_TYPE.BUY.getType() &&
                            transaction.getTransactionType()==TRANSACTION_TYPE.SELL.getType()) ||
                            (trans.getTransactionType()==TRANSACTION_TYPE.SELL.getType() &&
                                    transaction.getTransactionType()==TRANSACTION_TYPE.BUY.getType())){
                        isIntraDayTransaction= true;
                        temp= trans;
                        break;
                    }
                }

            }
            if(temp!=null){
                transactionList.remove(temp);
                temp.setTransactionFees(TRANSACTION_FEES.TEN.getFees());
                transactionList.add(temp);
            }
        } else {
            isIntraDayTransaction= false;
        }
        return isIntraDayTransaction;
    }

    public Transaction getTransaction(String[] transactionAttributes) {
        for (String string : transactionAttributes) {
            System.out.print(" "+string);
        }
        Transaction transaction = new Transaction();
        transaction.setExternalTransactionID(transactionAttributes[0]);
        transaction.setClientId(transactionAttributes[1]);
        transaction.setSecurityId(transactionAttributes[2]);
        switch(transactionAttributes[3]){
            case "BUY":
                transaction.setTransactionType(TRANSACTION_TYPE.BUY.getType());
                break;
            case "SELL":
                transaction.setTransactionType(TRANSACTION_TYPE.SELL.getType());
                break;
            case "DEPOSIT":
                transaction.setTransactionType(TRANSACTION_TYPE.DEPOSIT.getType());
                break;
             case "WITHDRAW":
                transaction.setTransactionType(TRANSACTION_TYPE.WITHDRAW.getType());
                break;
        }
        transaction.setTransactionDate(Utils.parseDate(transactionAttributes[4]));
        transaction.setMarketValue (Double.parseDouble(transactionAttributes[5]));
        transaction.setPriority(transactionAttributes[6].trim().equalsIgnoreCase("Y"));
        return transaction;
    }

    public void displayTransactionReport(){
        System.out.println("Calculated Fees:");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Client Id | Transaction Type | Transaction Date | Priority | Processing Fee |");
        for (Transaction transaction : transactionList) {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println(transaction.getClientId()+"| "+transaction.getTransactionType() +"| "+
                    transaction.getTransactionDate()+"| "+(transaction.getPriority()?"HIGH ":"NORMAL")+ "| "+
                    transaction.getTransactionFees()+"|");
        }
        System.out.println("---------------------------------------------------------------------------");
    }
}
