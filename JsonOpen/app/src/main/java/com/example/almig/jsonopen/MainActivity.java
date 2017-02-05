package com.example.almig.jsonopen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.almig.jsonopen.DBMake.MakeTmpDB;
import com.example.almig.jsonopen.Object.Gym;
import com.example.almig.jsonopen.Object.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public MakeTmpDB makeTmpDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeTmpDB = new MakeTmpDB(getApplicationContext());

        ArrayList<User> tt = makeTmpDB.makeUserDB();
        ArrayList<Gym> ttt = makeTmpDB.makeGymDB();

    }
}
