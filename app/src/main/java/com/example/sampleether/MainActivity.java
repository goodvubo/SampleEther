package com.example.sampleether;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;



public class MainActivity extends AppCompatActivity {

    public static String ADDRESS;
    public static InfuraAPI ETH3J = new InfuraAPI("https://mainnet.infura.io/v3/44bbb097836f4b75a1a35f489e846e24");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Button mBtnCheckBalance = findViewById(R.id.chk_balance);
        mBtnCheckBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    checkBalance(v);
                } catch (Exception e) {
                    Log.e("myTag", "not working");
                    e.printStackTrace();
                }
            }
        });

//        Button mBtnTxs = findViewById(R.id.chk_txs);
//        mBtnTxs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Create a new intent to open the {@link TransactionsActivity}
//                Intent txsIntent = new Intent(MainActivity.this, TransactionsActivity.class);
//                txsIntent.putExtra("web3OBJ", ETH3J);
//                txsIntent.putExtra("addr", ADDRESS);
//
//                // Start the new activity
//                startActivity(txsIntent);
//            }
//        });



    }

    /**
     * This method is called when the check button is clicked.
     */
    public void checkBalance(View view) {
        EditText addrField = (EditText) findViewById(R.id.addr_field);
        Editable nameEditable = addrField.getText();
        ADDRESS = nameEditable.toString();


        displayMessage(
                ETH3J.getBalance(ADDRESS).toString(),
                ETH3J.getNonce(ADDRESS).toString()
        );
    }

    /**
     * This method displays all info as text on the screen.
     */
    private void displayMessage(String balance, String nonce) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(balance);
        TextView nonceTextView = (TextView) findViewById(R.id.nonce_text_view);
        nonceTextView.setText(nonce);
    }

}