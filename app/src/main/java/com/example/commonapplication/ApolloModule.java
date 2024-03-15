package com.example.commonapplication;

import android.util.Log;

import com.apollographql.apollo3.ApolloClient;

import org.jetbrains.annotations.NotNull;

//package com.example.commonapplication;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import okhttp3.logging.HttpLoggingInterceptor.Level;
//import org.jetbrains.annotations.NotNull;
//import android.util.Log;
//import com.apollographql.apollo3.ApolloClient;
//import com.apollographql.apollo3.exception.ApolloException;
//import com.apollographql.apollo3.response.Response;
//import com.example.graphqlql.LaunchDetailsQuery;
//
public class ApolloModule {

    ApolloClient apolloClient = ApolloClient.builder()
            .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
            .build();
}

//    public void makeGraphQLRequest() {
//
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new HttpLoggingInterceptor().setLevel(Level.BODY))
//                .build();

//     apolloClient.query(new LaunchDetailsQuery("83"))
//            .enqueue(new ApolloCall.Callback<LaunchDetailsQuery.Data>() {
//        @Override
//        public void onResponse(@NotNull Response<LaunchDetailsQuery.Data> response) {
//            Log.e("Apollo", "Launch site: " + response.getData().launch().site());
//        }
//
//        @Override
//        public void onFailure(@NotNull ApolloException e) {
//            Log.e("Apollo", "Error", e);
//        }
//    });
