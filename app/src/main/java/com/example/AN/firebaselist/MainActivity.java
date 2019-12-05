package com.example.AN.firebaselist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);//시작할떄 리사이클러뷰 연결
        recyclerView.setHasFixedSize(true); //성능강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();// User 객체를 다믈 어레이 리스트 (어댑터 방향)

        database =FirebaseDatabase.getInstance();//파이어베이스의 데이터 베이스 연동

        databaseReference = database.getReference("User");//db 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 db의 데이터를 받아오는곳
                arrayList.clear();//기존배열리스트 가 존재하지않게 초기화
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){//반복분으로 데이터 List 가져옴
                    User user = snapshot.getValue(User.class);// 만들어둔 User객체에 데이터를 담는당.
                    arrayList.add(user);// 담은 데이터들을 배열리스트에넣고 리사이클러 뷰로 보낼준비
                }
                adapter.notifyDataSetChanged();// 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //디비를 가져오던중 에러발생시에 보여줄거
                Log.e("MainActivity",String.valueOf(databaseError.toException()));//에러 출력
            }
        });

        adapter= new CustomAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);//리사이클러뷰에 어댑터 연결
    }
}
