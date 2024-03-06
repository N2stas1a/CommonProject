package com.example.commonapplication;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import org.jetbrains.annotations.NotNull;
import android.util.Log;
import com.apollographql.apollo3.ApolloClient;
import com.apollographql.apollo3.exception.ApolloException;
import com.apollographql.apollo3.response.Response;
import com.example.graphql.LaunchDetailsQuery;

public class ApolloModule {

    private static final String BASE_URL = "https://apollo-fullstack-tutorial.herokuapp.com/graphql";

    public void makeGraphQLRequest() {
        // Создаем HTTP клиент с логгированием
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(Level.BODY))
                .build();

        // Создаем экземпляр ApolloClient с указанием URL сервера GraphQL и HTTP клиента
        ApolloClient apolloClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build();

        // Создаем запрос LaunchDetailsQuery с указанием аргумента
        LaunchDetailsQuery launchDetailsQuery = new LaunchDetailsQuery("83");

        // Отправляем запрос асинхронно и обрабатываем ответ
        apolloClient.query(launchDetailsQuery).enqueue(new ApolloCall.Callback<LaunchDetailsQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<LaunchDetailsQuery.Data> response) {
                // Обработка успешного ответа
                Log.d("Apollo", "Launch site: " + response.getData().launch().site());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                // Обработка ошибки
                Log.e("Apollo", "Error", e);
            }
        });
    }
}
