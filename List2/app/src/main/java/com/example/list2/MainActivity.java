package com.example.list2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        listView = (ListView)findViewById(R.id.listview);

        // 데이터 저장하게 되는 리스트
        List<String> list = new ArrayList<>();

        // 리스트뷰와 리스트를 연결하기위해 사용되는 어댑타
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,list);

        // list뷰의 어댑터를 지정해준다
        listView.setAdapter(adapter);

        // list뷰에 보여질 아이템 추가
        list.add("재영");
        list.add("동호");
        list.add("채빈");
        list.add("선우");
        list.add("예지");
        list.add("영상");
        list.add("현석");
        list.add("현주");
        list.add("기태");
        list.add("명한");
        list.add("지훈");
        list.add("정엽");


        Button result_button = (Button)findViewById(R.id.result_button);
        result_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void  onClick(View view){
                final List<String> selectedItems = new ArrayList<>();

                // 리스트뷰에서 선택된아이템의목록을 가져온다.
                SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
                for (int i =0; i<checkedItemPositions.size();i++){
                    int pos = checkedItemPositions.keyAt(i);

                    if (checkedItemPositions.valueAt(i)){
                        selectedItems.add(listView.getItemAtPosition(pos).toString());
                    }
                }
                final CharSequence[] items = selectedItems.toArray(new String[selectedItems.size()]);

                // 다이얼로그에 가져온 목록을 보여준다.
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setTitle("선택한 아이템목록");
                dialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    public  void onClick(DialogInterface dialog, int pos){
                        String selectedText =items[pos].toString();
                        Toast.makeText(MainActivity.this,selectedText, Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialogObject = dialogBuilder.create();
                alertDialogObject.show();
            }
        });

    }
}
