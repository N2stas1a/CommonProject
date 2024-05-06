package com.example.commonapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private RequestQueue rQueue;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Toast successful;
    private Toast failed;
    private String temp;
    private TextView
            Cur_ID,
            Cur_ParentID,
            Cur_Code,
            Cur_Abbreviation,
            Cur_Name,
            Cur_Name_Bel,
            Cur_Name_Eng;
    private int position = 0;

    public SecondFragment() {
    }

    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            temp = bundle.getString("1", null );
        }
     }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rQueue = Volley.newRequestQueue(view.getContext());

        Toast successful = Toast.makeText(requireContext(), "Ok", Toast.LENGTH_SHORT);
        failed = Toast.makeText(requireContext(), "Fail", Toast.LENGTH_SHORT);
        Cur_ID = view.findViewById(R.id.Cur_ID);
        Cur_ParentID = view.findViewById(R.id.Cur_ParentID);
        Cur_Code = view.findViewById(R.id.Cur_Code);
        Cur_Abbreviation = view.findViewById(R.id.Cur_Abbreviation);
        Cur_Name = view.findViewById(R.id.Cur_Name);
        Cur_Name_Bel = view.findViewById(R.id.Cur_Name_Bel);
        Cur_Name_Eng = view.findViewById(R.id.Cur_Name_Eng);
        //Cur_ID.setText(temp);
        jsonParse();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    private void jsonParse() {
        String url = "https://api.nbrb.by/exrates/currencies";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject currency = response.getJSONObject(i);
                        String currencyId = currency.getString("Cur_Abbreviation");
                        if(currencyId.equals(temp)) {
                            Cur_ID.setText(currency.getString("Cur_ID"));
                            Cur_ParentID.setText(currency.getString("Cur_ParentID"));
                            Cur_Code.setText(currency.getString("Cur_Code"));
                            Cur_Abbreviation.setText(currency.getString("Cur_Abbreviation"));
                            Cur_Name.setText(currency.getString("Cur_Name"));
                            Cur_Name_Bel.setText(currency.getString("Cur_Name_Bel"));
                            Cur_Name_Eng.setText(currency.getString("Cur_Name_Eng"));
                        }
                    }
//
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, error -> {
        });
        rQueue.add(request);
    }
}