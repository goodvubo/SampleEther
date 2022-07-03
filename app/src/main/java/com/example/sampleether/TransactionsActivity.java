package com.example.sampleether;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;

import java.util.ArrayList;
import java.util.List;

public class TransactionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tx_list);

        Intent i = getIntent();
        String addr = i.getStringExtra("addr");
        InfuraAPI web3OBJ = (InfuraAPI) i.getSerializableExtra("web3OBJ");

        String txsList = web3OBJ.getTxs(addr);
        Log.d("myTag", txsList);




        ArrayList<Tx> txs = new ArrayList<Tx>();
        txs.add(new Tx("one", "lutti", "1"));
        txs.add(new Tx("two", "otiiko", "2"));
        txs.add(new Tx("three", "tolookosu", "4"));
        txs.add(new Tx("four", "oyyisa", "1"));


        TxAdapter adapter = new TxAdapter(this, txs);


        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

    }
}