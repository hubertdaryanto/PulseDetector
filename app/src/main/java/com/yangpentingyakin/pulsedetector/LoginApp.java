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
import com.mukeshsolanki.sociallogin.google.GoogleHelper;
import com.mukeshsolanki.sociallogin.google.GoogleListener;

public class LoginApp extends AppCompatActivity implements FacebookListener, GoogleListener {

    FacebookHelper mFaceBook;
    GoogleHelper mGoogle;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode,data);

        mFaceBook.onActivityResult(requestCode,resultCode,data);
        mGoogle.onActivityResult(requestCode,resultCode,data);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        //init google
        mGoogle = new GoogleHelper(this,this,null);

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

        Button btnGoogle = (Button)findViewById(R.id.btnGoogle);
        btnFacebook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                mGoogle.performSignIn(LoginApp.this);
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

    @Override
    public void onGoogleAuthSignIn(String authToken, String userId) {
        Toast.makeText(this, ""+userId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGoogleAuthSignInFailed(String errorMessage) {
        Toast.makeText(this, ""+errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGoogleAuthSignOut() {
        Toast.makeText(this, "Signout !!!", Toast.LENGTH_SHORT).show();
    }
}

//tes commit

