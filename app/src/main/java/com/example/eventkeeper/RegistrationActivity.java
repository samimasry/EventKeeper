package com.example.eventkeeper;

import android.content.Intent;
import android.provider.Settings;
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

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName, userEmail, userPassword;
    private Button regButton;
    private TextView userLogin;
    //remove below
    private TextView apiReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIElements();

        //listener for registration button
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if user entered complete data
                if(validate()){
                    //upload to database
                    createUser();
                }

            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate back to login page, startActivity(new Intent(source, dest))
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }
    //attach xml view stuff to variables
    private void setupUIElements(){
        userName = (EditText) findViewById(R.id.etUserName);
        userPassword = (EditText) findViewById(R.id.etUserPassword);
        userEmail = (EditText) findViewById(R.id.etUserEmail);
        regButton = (Button) findViewById(R.id.btnRegister);
        userLogin = (TextView) findViewById(R.id.tvUserLogin);
        apiReturn= (TextView)findViewById(R.id.apiReturn);
    }
    private Boolean validate(){
        Boolean result = false;
        String name = userName.getText().toString();
        String email = userEmail.getText().toString();
        String password= userPassword.getText().toString();
        //check if all values are entered
        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            //if not output error
            Toast.makeText(this, "Please Fill all the Fields", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;


    }

    private void createUser(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eventkeeperofficial.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi  = retrofit.create(JsonPlaceHolderApi.class);
        String[] somename = new String[2];
        somename[0] = "john";
        somename[1] = "doe";
        String[] someAdress = new String[4];
        someAdress[0] = "1234 main st";
        someAdress[1] = "orlando";
        someAdress[2] = "florida";
        someAdress[3] = "12345";



        User user = new User(userName.getText().toString(), userEmail.getText().toString(), userPassword.getText().toString(), somename, someAdress);
        Call<User> call = jsonPlaceHolderApi.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    apiReturn.setText("cde " + response.code());
                    return;
                }
                User userResponse = response.body();

                String content = "";
                content += "code " + response.code() + "\n";
                content +="Username " + userResponse.getUsername() + "\n";
                content +="Email " + userResponse.getEmail() + "\n";
                content +="Password " + userResponse.getPassword() + "\n\n";
                apiReturn.setText(content);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                apiReturn.setText(t.getMessage());

            }
        });



    }
}
