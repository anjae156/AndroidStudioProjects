package com.example.botnav;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Frag1 extends Fragment {
    private View view;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.frag1,container, false);

        listView = (ListView) layout.findViewById(R.id.listview);

        List<String> list = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_multiple_choice,list);

        listView.setAdapter(adapter);

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


        Button result_button = (Button)layout.findViewById(R.id.result_button);
        result_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final List<String> selectedItems = new ArrayList<>();

                //리스트뷰에서 서낵된아이템의 목록을 가져옴.
                SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
                for (int i = 0; i<checkedItemPositions.size(); i++){
                    int pos = checkedItemPositions.keyAt(i);

                    if (checkedItemPositions.valueAt(i)){
                        selectedItems.add(listView.getItemAtPosition(pos).toString());
                    }
                }
                final  CharSequence[] items = selectedItems.toArray(new String[selectedItems.size()]);

                // 다이얼로그에 가져온 몰록을 보여준다.
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                dialogBuilder.setTitle("선택된사람들");
                dialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int pos) {
                        String selectedText = items[pos].toString();
                        Toast.makeText(getActivity(),selectedText,Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        });
    return layout;

    }
}
