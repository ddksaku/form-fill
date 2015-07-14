package com.development.visualscope;

import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class KeyValueSpinner implements SpinnerAdapter{
    private Context context;
    private List<State> stateList;
    
    public KeyValueSpinner(Context context , List<State> stateList){
        this.context = context;
        this.stateList = stateList;
    }
    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return stateList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return stateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return stateList.get(position).getId();
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
   
    public int getIdFromIndex(int index) {
        return stateList.get(index).getId();       
    }
    
    public int getIndexById(int id) {
        for(int index = 0; index < stateList.size(); index++) {
        	if(stateList.get(index).getId() == id){
                return index;
            }
        }
        
        return -1;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView textView = (TextView) inflater.inflate(android.R.layout.simple_spinner_item, null);
        textView.setText(stateList.get(position).getName());        
       
        return textView;
    }
    
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView textView = (TextView) inflater.inflate(android.R.layout.simple_spinner_item, null);
        textView.setText(stateList.get(position).getName());
        textView.setPadding(10, 10, 10, 10);
       
        return textView;
    }

    @Override
    public int getViewTypeCount() {
        return android.R.layout.simple_spinner_item;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        // TODO Auto-generated method stub       
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        // TODO Auto-generated method stub       
    }   
}