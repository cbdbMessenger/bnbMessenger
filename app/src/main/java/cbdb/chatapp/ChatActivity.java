package cbdb.chatapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Reggie on 11/8/2015.
 */
public class ChatActivity extends Activity {

    EditText editTextMessage;
    ListView messageThread;
    int currentListPosition;

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems;
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        currentListPosition = 0;
        listItems = new ArrayList<String>();

        messageThread = (ListView)findViewById(R.id.lstViewMessages);
        messageThread.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        editTextMessage = (EditText)findViewById(R.id.edtTxtMessage);
        editTextMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextMessage.setText("");
            }
        });
        editTextMessage.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_ENTER && !editTextMessage.getText().toString().matches("")) {
                    listItems.add(editTextMessage.getText().toString());
                    updateList();
                    editTextMessage.setText("");
                    if(editTextMessage.getText().toString().matches("\n"))
                        editTextMessage.setText("");
                }

                return true;
            }
        });
    }

    private void updateList() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        messageThread.setAdapter(adapter);
    }

}
