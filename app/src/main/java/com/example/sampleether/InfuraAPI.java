package com.example.sampleether;

import android.util.Log;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class InfuraAPI implements Serializable {

    public static Web3j mETH3J;
    public static EthGetTransactionCount mNonce;
    public static EthGetBalance mBalanceWei;
    public static List<EthBlock.TransactionResult> mTxs;

    public static EthBlock.Block mBlk;
    public static BigInteger mBlkNumber;

    public InfuraAPI(String urlAPI) {
        mETH3J = Web3j.build(new HttpService(urlAPI));
    }

    public static BigInteger getNonce(String addr) {
        try {
            mNonce = mETH3J.ethGetTransactionCount(
                    addr, DefaultBlockParameterName.LATEST).sendAsync().get();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mNonce.getTransactionCount();
    }

    public static BigDecimal getBalance(String addr) {
        try {
            mBalanceWei = mETH3J.ethGetBalance(
                    addr, DefaultBlockParameterName.LATEST).send();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Convert.fromWei(mBalanceWei.getBalance().toString(), Convert.Unit.ETHER);
    }

    public static String getTxs(String addr) {
        try {
            mBlk = mETH3J.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();
            if(mBlk != null)
                mTxs = mBlk.getTransactions();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        if (mBlk != null && mTxs != null) {
//            for (EthBlock.TransactionResult tx : mTxs) {
//                let tx = await this.web3.eth.getTransaction(txHash);
//                if (this.address == tx.to.toLowerCase()) {
//                    console.log("from: " + tx.from.toLowerCase() + " to: " + tx.to.toLowerCase() + " value: " + tx.value);
//                }
//            }
//        }


        if(mTxs != null)
            for (EthBlock.TransactionResult tx : mTxs){
                Log.d("myTag", tx.get().toString());
//                EthBlock.TransactionObject transaction = (EthBlock.TransactionObject)tx.get();
//                if(transaction != null)
//                    return transaction.getValue().toString();
//                Log.d("myTag", transaction.getFrom());
//                Log.d("myTag", transaction.getTo());
//                Log.d("myTag", transaction.getValue().toString());
            }
        else
            Log.d("myTag", "no txs found");

        return "mTxs";
    }

}
