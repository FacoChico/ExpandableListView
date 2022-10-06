package com.example.fornew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.material.transformation.ExpandableBehavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> mobileConnection;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createGroupList();
        createCollection();
        expandableListView = findViewById(R.id.elvMobile);
        expandableListAdapter = new MyExpandableListAdapter(this, groupList, mobileConnection);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;
            @Override
            public void onGroupExpand(int i) {
                if(lastExpandedPosition != -1 && i != lastExpandedPosition)
                    expandableListView.collapseGroup(lastExpandedPosition);
                lastExpandedPosition = i;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String selected = expandableListAdapter.getChild(i, i1).toString();
                Toast.makeText(getApplicationContext(), "Selected: " + selected, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void createCollection() {
        String[] samsungModels = {"Samsung1", "Samsung2", "Samsung3", "Samsung4", "Samsung5"};
        String[] appleModels = {"Apple1", "Apple2", "Apple3", "Apple4", "Apple5"};
        String[] oneplusModels = {"OnePlus1", "OnePlus2", "OnePlus3", "OnePlus4", "OnePlus5"};
        String[] lenovoModels = {"Lenovo1", "Lenovo2", "Lenovo3", "Lenovo4", "Lenovo5"};
        String[] nokiaModels = {"Nokia1", "Nokia2", "Nokia3", "Nokia4", "Nokia5"};
        mobileConnection = new HashMap<String, List<String>>();
        for(String group: groupList)
        {
            if(group.equals("Samsung"))
                loadChild(samsungModels);
            else if(group.equals("Apple"))
                loadChild(appleModels);
            else if(group.equals("OnePlus"))
                loadChild(oneplusModels);
            else if(group.equals("Lenovo"))
                loadChild(lenovoModels);
            else if(group.equals("Nokia"))
                loadChild(nokiaModels);
            mobileConnection.put(group, childList);
        }
    }

    private void loadChild(String[] mobileModels) {
        childList = new ArrayList<>();
        for(String model: mobileModels)
            childList.add(model);
    }

    private void createGroupList() {
        groupList = new ArrayList<>();
        groupList.add("Samsung");
        groupList.add("Apple");
        groupList.add("OnePLus");
        groupList.add("Lenovo");
        groupList.add("Nokia");
    }
}