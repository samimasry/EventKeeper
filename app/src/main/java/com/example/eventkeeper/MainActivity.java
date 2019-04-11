package com.example.eventkeeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {




    private EditText email, password;
    private Button login;
    private TextView userRegistration;
    //remove below
    private TextView apiReturn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setup UI Elements
        setupUIElements();


        //add a listener for the register button
        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();

            }
        });
    }

    private void setupUIElements(){
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        login = (Button) findViewById(R.id.btnLogin);
        userRegistration = (TextView) findViewById(R.id.tvRegister);
        apiReturn = (TextView) findViewById(R.id.apiReturn);
    }

    private void loginUser(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eventkeeperofficial.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi  = retrofit.create(JsonPlaceHolderApi.class);
//        String[] somename = new String[2];
//        somename[0] = "john";
//        somename[1] = "doe";
//        String[] someAdress = new String[4];
//        someAdress[0] = "1234 main st";
//        someAdress[1] = "orlando";
//        someAdress[2] = "florida";
//        someAdress[3] = "12345";

        //Fullname fullname = new Fullname("john", "doe");
        //Address address  = new Address("1234 main st", "orlando","florida",12345);


        User loginuser = new User( "login", email.getText().toString(),
                password.getText().toString(),
                new Fullname("login", "login"),
                new Address("1", "1","1","1") );

        Call<User> call = jsonPlaceHolderApi.loginUser(loginuser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    apiReturn.setText("cde " + response.code());

                    //Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                    return;
                }
                User userResponse = response.body();
                Toast.makeText(MainActivity.this, userResponse.toString(), Toast.LENGTH_LONG).show();
                String content = "";
                content += "code " + response.code() + "\n";
//                content +="Username " + userResponse.getUsername() + "\n";
//                content +="Email " + userResponse.getEmail() + "\n";
//                content +="Password " + userResponse.getPassword() + "\n\n";
                apiReturn.setText(content);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                apiReturn.setText(t.getMessage());

            }
        });



    }

}
