package ua.email.vrbox.arsenalfactory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button butSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butSign = (Button) findViewById(R.id.button2);
        butSign.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "Авторизация", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
            startActivity(intent);

        }
    };
}
