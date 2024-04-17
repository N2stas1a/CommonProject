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
    private ArrayList<String> Cur_Name, Cur_ID, Cur_Abbreviation;
    private ArrayList<String> Cur_ParentID, Cur_Code, Cur_Name_Bel, Cur_Name_Eng;
    private FragmentManager fragmentManager;
    private SecondFragment secondFragment = new SecondFragment();

    public CustomAdapter(Context context, ArrayList<String> name, ArrayList<String> id, ArrayList<String> abbreviation, ArrayList<String> parentID, ArrayList<String> code, ArrayList<String> nameBel, ArrayList<String> nameEng, FragmentManager fragmentManager) {
        this.context = context;
        this.Cur_Name = name;
        this.Cur_ID = id;
        this.Cur_Abbreviation = abbreviation;
        this.Cur_ParentID = parentID;
        this.Cur_Code = code;
        this.Cur_Name_Bel = nameBel;
        this.Cur_Name_Eng = nameEng;
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
        if (Cur_Name != null && Cur_ID != null && Cur_Abbreviation != null) {
            holder.TextName.setText(Cur_Name.get(position));
            holder.TextUsage.setText(Cur_ID.get(position));
            holder.TextAbbreviation.setText(Cur_Abbreviation.get(position));
//        holder.TextParentID.setText(Cur_ParentID.get(position));
            holder.TextCode.setText(Cur_Code.get(position));
            holder.TextNameBel.setText(Cur_Name_Bel.get(position));
            holder.TextNameEng.setText(Cur_Name_Eng.get(position));

            holder.ROW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SecondFragment secondFragment = new SecondFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainerView, secondFragment).commit();
                    secondFragment.setPosition(holder.getAdapterPosition());
                    fragmentTransaction.addToBackStack(null);
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return Cur_Name.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ROW;
        TextView TextName, TextUsage, TextAbbreviation;
        TextView TextParentID, TextCode, TextNameBel, TextNameEng, TextparentID;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TextName = itemView.findViewById(R.id.Сur_Name);
            TextUsage = itemView.findViewById(R.id.Сur_Abbreviation);
            TextAbbreviation = itemView.findViewById(R.id.Cur_ID);
            TextParentID = itemView.findViewById(R.id.Cur_ParentID);
            TextCode = itemView.findViewById(R.id.Cur_Code);
            TextNameBel = itemView.findViewById(R.id.Cur_Name_Bel);
            TextNameEng = itemView.findViewById(R.id.Cur_Name_Eng);
            ROW = itemView.findViewById(R.id.row);
        }
    }
}
