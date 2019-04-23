package app.driveaz.driveazevendor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;

public class RegistrationActivity extends AppCompatActivity {


    EditText editname, editphone, editemail, editpassword, editGSTNo, editTradeLincNo,
            editPanNo, editAdharNo, editAddress1, editAddress2, editPincode, editCity, editState, editCountry, editLandMark, editTags ;
    Button btnsignup;
    LinearLayout textSignIn;
    String otp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editname = findViewById(R.id.editTextName);
        editphone = findViewById(R.id.editTextPhone);
        editemail =  findViewById(R.id.editTextEmail);
        editpassword = findViewById(R.id.editTextPassword);
        editGSTNo = findViewById(R.id.editTextGST);
        editTradeLincNo = findViewById(R.id.editTextTradeLicnNo);
        editPanNo =  findViewById(R.id.editTextPANNo);
        editAdharNo = findViewById(R.id.editTextAdharNo);

        editAddress1 = findViewById(R.id.editTextAddress1);
        editAddress2 = findViewById(R.id.editTextAddress2);
        editPincode =  findViewById(R.id.editTextPincode);
        editCity = findViewById(R.id.editTextCity);
        editState = findViewById(R.id.editTextState);
        editCountry = findViewById(R.id.editTextCountry);
        editLandMark =  findViewById(R.id.editTextLandmark);
        editTags = findViewById(R.id.editTextTags);


        btnsignup =  findViewById(R.id.btnSignUp);

        textSignIn = findViewById(R.id.textForSignIn);



        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = editname.getText().toString();
                final String phone = editphone.getText().toString();
                final String email = editemail.getText().toString();
                final String password = editpassword.getText().toString();
                final String gstNo = editGSTNo.getText().toString();
                final String tradeLincNo = editTradeLincNo.getText().toString();
                final String panNo = editPanNo.getText().toString();
                final String adharNo = editAdharNo.getText().toString();
                final String address1 = editAddress1.getText().toString();
                final String address2 = editAddress2.getText().toString();
                final String pincode = editPincode.getText().toString();
                final String city = editCity.getText().toString();
                final String state = editState.getText().toString();
                final String country = editCountry.getText().toString();
                final String lankmark = editLandMark.getText().toString();
                final String tags = editTags.getText().toString();

                if (TextUtils.isEmpty(name)){

                    showToast("Enter your name");
                    editname.requestFocus();
                }
                else if (TextUtils.isEmpty(phone)){

                    showToast("Enter your phone number");
                    editname.requestFocus();
                }
                else if (phone.length() != 10){

                    showToast("Phone number should be of 10 digits");
                    editname.requestFocus();
                }
                else if (TextUtils.isEmpty(email)){

                    showToast("Enter your email");
                    editname.requestFocus();
                }

                else if (TextUtils.isEmpty(password)){

                    showToast("Enter your password");
                    editname.requestFocus();
                }

                else if (TextUtils.isEmpty(gstNo)){

                    showToast("Enter your GST No");
                    editname.requestFocus();
                }

                else if (TextUtils.isEmpty(tradeLincNo)){

                    showToast("Enter your Trade License No");
                    editname.requestFocus();
                }

                else if (TextUtils.isEmpty(panNo)){

                    showToast("Enter your PAN Card No.");
                    editname.requestFocus();
                }

                else if (TextUtils.isEmpty(adharNo)){

                    showToast("Enter your Aadhaar Card No");
                    editname.requestFocus();
                }
                else if (TextUtils.isEmpty(address1)){

                    showToast("Enter your address2");
                    editname.requestFocus();
                }

                else if (TextUtils.isEmpty(address2)){

                    showToast("Enter your address 2");
                    editname.requestFocus();
                }

                else if (TextUtils.isEmpty(pincode)){

                    showToast("Enter your pincode");
                    editname.requestFocus();
                }

                else if (TextUtils.isEmpty(city)){

                    showToast("Enter your City");
                    editname.requestFocus();
                }
                else if (TextUtils.isEmpty(state)){

                    showToast("Enter your state");
                    editname.requestFocus();
                }

                else if (TextUtils.isEmpty(country)){

                    showToast("Enter your Country");
                    editname.requestFocus();
                }

                else if (TextUtils.isEmpty(lankmark)){

                    showToast("Enter your Landmark");
                    editname.requestFocus();
                }

                else if (TextUtils.isEmpty(tags)){

                    showToast("Enter your TAgs");
                    editname.requestFocus();
                }


                else {

                    NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);

                    Call<Response> registrationResponse = networkAPI.registervendor(name, email, phone,
                            password, gstNo, tradeLincNo, panNo,adharNo, address1,
                            address2, pincode, city, state, country, lankmark, tags);

                    registrationResponse.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                            if (response.isSuccessful()){

                                Toast.makeText(RegistrationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                verifyCode();
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

    private void verifyCode() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.verify_otp_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText enterOTP = (EditText) dialogView.findViewById(R.id.editTextOTP);
        final Button btnVerify = (Button) dialogView.findViewById(R.id.btnVerifyOTP);
        final TextView showMobile = (TextView) dialogView.findViewById(R.id.textOTPVerify);
        // final ProgressBar progressBar1 = (ProgressBar) dialogView.findViewById(R.id.progressBar);

        showMobile.setText("Enter OTP sent to "+  editphone.getText().toString() + " to verify your account");
        //dialogBuilder.setTitle("Send Photos");
        final AlertDialog dialog = dialogBuilder.create();



        btnVerify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                otp  = enterOTP.getText().toString().trim();

                if (otp.isEmpty()){
                    Toast.makeText(getApplicationContext(),"enter OTP",Toast.LENGTH_SHORT).show();
                }
                else  {

                    NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);

                    Call<Response> verifyResponse = networkAPI.verifyvendorotp(editphone.getText().toString(), otp);

                    verifyResponse.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                            if (response.body().getCode().equals("success")){

                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(intent);
                                showToast("Registration Successful");

                            }else {
                                showToast("Enter valid OTP");
                            }

                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {

                        }
                    });

                }
            }
        });

        dialog.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
