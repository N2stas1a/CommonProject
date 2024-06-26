package com.example.commonapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private RequestQueue rQueue;
    private FloatingActionButton rButton;
    private Toast successful;
    private Toast failed;
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;

    ArrayList<String> Cur_Name = new ArrayList<>(),
            Cur_Abbreviation = new ArrayList<>(),
            Cur_ID = new ArrayList<>();

    public FirstFragment() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rButton = view.findViewById(R.id.reload_btn);
        rQueue = Volley.newRequestQueue(requireContext());
        successful = Toast.makeText(requireContext(), "Ok", Toast.LENGTH_SHORT);
        failed = Toast.makeText(requireContext(), "Fail", Toast.LENGTH_SHORT);
        recyclerView = view.findViewById(R.id.recyclerView);
        rButton.setOnClickListener(view1 -> jsonParse());
        jsonParse();
        customAdapter = new CustomAdapter(requireContext(), Cur_Name, Cur_ID, Cur_Abbreviation, requireActivity().getSupportFragmentManager());
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    private void jsonParse() {
        String url = "https://api.nbrb.by/exrates/currencies";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Cur_Name.clear();
                    Cur_ID.clear();
                    Cur_Abbreviation.clear();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject currency = response.getJSONObject(i);
                        Cur_Name.add(currency.getString("Cur_Name"));
                        Cur_ID.add(currency.getString("Cur_ID"));
                        Cur_Abbreviation.add(currency.getString("Cur_Abbreviation"));
                    }
                    successful.show();
                    customAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failed.show();
            }
        });
        rQueue.add(request);
    }
}
