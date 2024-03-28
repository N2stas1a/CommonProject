package com.example.commonapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> Name, Usage, Abbreviation;
    private FragmentManager fragmentManager;
    private SecondFragment secondFragment = new SecondFragment();

    public CustomAdapter(Context context, ArrayList<String> name, ArrayList<String> usage, ArrayList<String> abbreviation) {
        this.context = context;
        this.Name = name;
        this.Usage = usage;
        this.Abbreviation = abbreviation;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.TextName.setText(Name.get(position));
        holder.TextUsage.setText(Usage.get(position));
        holder.TextAbbreviation.setText(Abbreviation.get(position));
        holder.ROW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, secondFragment).commit();
                secondFragment.setPosition(holder.getAdapterPosition());
                fragmentTransaction.addToBackStack(null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ROW;
        TextView TextName, TextUsage, TextAbbreviation;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TextName = itemView.findViewById(R.id.curName);
            TextUsage = itemView.findViewById(R.id.curCode);
            TextAbbreviation = itemView.findViewById(R.id.curID);
            ROW = itemView.findViewById(R.id.row);
        }
    }
}
