package com.development.visualscope;

import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class ContactKeyValueSpinner implements SpinnerAdapter{
    private Context context;
    private List<Contact> contactList;
    
    public ContactKeyValueSpinner(Context context , List<Contact> contactList){
        this.context = context;
        this.contactList = contactList;
    }
    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
//        return contactList.get(position).getId();
    	return 0;
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
   
    public int getIdFromIndex(int index) {
//        return contactList.get(index).getId();
    	return 0;
    }
    
    public int getIndexById(int id) {
//        for(int index = 0; index < contactList.size(); index++) {
//        	if(contactList.get(index).getId() == id){
//                return index;
//            }
//        }
//        
//        return -1;
    	return 0;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView textView = (TextView) inflater.inflate(android.R.layout.simple_spinner_item, null);
        textView.setText(contactList.get(position).name);        
       
        return textView;
    }
    
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView textView = (TextView) inflater.inflate(android.R.layout.simple_spinner_item, null);
        textView.setText(contactList.get(position).name);
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