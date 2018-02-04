package ua.email.vrbox.arsenalfactory;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button butSign;

    String uid;

    private EditText textEmail;
    private EditText textPassword;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butSign = (Button) findViewById(R.id.button2);
        butSign.setOnClickListener(onClickListener);

        textEmail = (EditText) findViewById(R.id.editEmail);
        textPassword = (EditText) findViewById(R.id.editPass);

        mAuth = FirebaseAuth.getInstance();

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            signing(textEmail.getText().toString(),textPassword.getText().toString());
        }
    };



    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                uid = user.getUid();
                Toast.makeText(MainActivity.this, "Авторизация успешна. " + uid, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                startActivity(intent);


            } else {
                // User is signed out

                Toast.makeText(MainActivity.this, "Авторизация не успешна.", Toast.LENGTH_SHORT).show();

            }
            // ...
        }
    };




    public void signing(String email, String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Авторизация успешна.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Авторизация не выполнена.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
