package com.example.museums.view.services.Listeners.textWatchers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;

import com.example.museums.API.models.Author;

import java.util.List;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class TextWatcherSearchWords implements TextWatcher {
    private TextFieldBoxes nameTextFieldBoxes;
    private List<Author> listItems;
    private  ArrayAdapter<String> adapter;
    public TextWatcherSearchWords(TextFieldBoxes nameTextFieldBoxes, List<Author> listItems) {
        this.nameTextFieldBoxes = nameTextFieldBoxes;
        this.listItems = listItems;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if(s.toString().equals("")){

            initList();
        } else {
             searchItem(s.toString());
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
    public void searchItem(String textToSearch){
        for(Author item:listItems){
            if(!item.fullName.contains(textToSearch)){
                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }
    public void initList(){

//        adapter=new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, listItems);
//        listView.setAdapter(adapter);
    }

}
