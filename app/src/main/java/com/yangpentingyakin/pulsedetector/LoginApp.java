package com.yangpentingyakin.pulsedetector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.mukeshsolanki.sociallogin.facebook.FacebookHelper;
import com.mukeshsolanki.sociallogin.facebook.FacebookListener;

public class LoginApp extends AppCompatActivity implements FacebookListener {

    FacebookHelper mFaceBook;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode,data);

        mFaceBook.onActivityResult(requestCode,resultCode,data);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        //Init Facebook

        FacebookSdk.setApplicationId(getResources().getString(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(this);
        mFaceBook = new FacebookHelper(this);

        Button btnFacebook = (Button)findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                mFaceBook.performSignIn(LoginApp.this);
            }
        });
    }

    @Override
    public void onFbSignInFail(String errorMessage) {
        Toast.makeText(this, ""+errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFbSignInSuccess(String authToken, String userId) {
        Toast.makeText(this, ""+userId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFBSignOut() {
        Toast.makeText(this, "Signout !!!", Toast.LENGTH_SHORT).show();
    }
}

