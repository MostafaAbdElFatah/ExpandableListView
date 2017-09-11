package com.mostafaabdel_fatah.expandablelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ExpandableListView expandableListView;
    List<String> header = new ArrayList<String>();
    HashMap<String,List<String>> child_titles = new HashMap<String,List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);

        List<String> child1 = new ArrayList<String>();
        List<String> child2 = new ArrayList<String>();
        List<String> child3 = new ArrayList<String>();

        String[] arHeader = getResources().getStringArray(R.array.header_title);
        String[] arChild1 = getResources().getStringArray(R.array.child1_title);
        String[] arChild2 = getResources().getStringArray(R.array.child2_title);
        String[] arChild3 = getResources().getStringArray(R.array.child3_title);
        for (String item:arHeader){
            header.add(item);
        }
        for (String item:arChild1){
            child1.add(item);
        }
        for (String item:arChild2){
            child2.add(item);
        }
        for (String item:arChild3){
            child3.add(item);
        }
        child_titles.put(header.get(0),child1);
        child_titles.put(header.get(1),child2);
        child_titles.put(header.get(2),child3);
        ExpAdapter expAdapter = new ExpAdapter(this,header,child_titles);
        expandableListView.setAdapter(expAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Toast.makeText(getApplicationContext(),
                        child_titles.get(header.get(groupPosition)).get(childPosition),Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), header.get(groupPosition)
                        + " Expanding",Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), header.get(groupPosition)
                        +" Collapsing",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
