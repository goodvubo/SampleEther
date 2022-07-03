package com.example.sampleether;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class TxAdapter extends ArrayAdapter<Tx> {

    public TxAdapter(Context context, ArrayList<Tx> txs) {
        super(context, 0, txs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Tx currentTX = getItem(position);

        TextView valueTextView = (TextView) listItemView.findViewById(R.id.value_text_view);
        valueTextView.setText("value: " + currentTX.getValue());

        TextView fromTextView = (TextView) listItemView.findViewById(R.id.from_text_view);
        fromTextView.setText("from: " + currentTX.getFrom());

        TextView totTextView = (TextView) listItemView.findViewById(R.id.to_text_view);
        totTextView.setText("to: " + currentTX.getTo());


        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), R.color.category_txs);
        textContainer.setBackgroundColor(color);


        return listItemView;
    }

}
