package app.driveaz.driveazevendor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {



    EditText editphonelogin, editpasswordlogin;
    Button btnSignin;
    LinearLayout textSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editphonelogin = (EditText) findViewById(R.id.editTextPhoneLogin);
        editpasswordlogin = (EditText) findViewById(R.id.editTextPasswordLogin);
        textSignUp = (LinearLayout) findViewById(R.id.textForSignup);

        btnSignin = findViewById(R.id.btnSignIn);

        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class ));
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone  = editphonelogin.getText().toString();
                String password = editpasswordlogin.getText().toString();

                if (TextUtils.isEmpty(phone)){

                    showToast("Please enter contact number");

                }else  if (phone.length() != 10){

                    showToast("Contact number should be of 10 digits");

                }else  if (TextUtils.isEmpty(password)){

                    showToast("Please enter password");

                }else {

                    NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);

                    Call<Response> loginResponse = networkAPI.loginvendor(phone, password);

                    loginResponse.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                            if (response.body().getCode().equals("success")){

                                Intent intent = new Intent(LoginActivity.this, RequestActivity.class);
                                startActivity(intent);
                                showToast("Login Successful");

                            }else {
                                showToast("Enter valid credentials");
                            }

                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {

                        }
                    });

                }

            }
        });


    }


    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
