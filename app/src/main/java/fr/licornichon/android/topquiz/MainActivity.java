package fr.licornichon.android.topquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mResultText;
    private EditText mNumberInput;
    private Button mPlayButton;
    private Button mSendButton;
    private String message = "tu es un";
    private String insult;
    private String phoneNumber = "0646898343";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResultText = (TextView) findViewById(R.id.activity_main_result_tv);
        mNumberInput = (EditText) findViewById(R.id.activity_main_number_et);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        mSendButton = (Button) findViewById(R.id.activity_main_send_btn);



        mPlayButton.setEnabled(false);

        //Activ Go button when textfield is not empty
        mNumberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() == 10);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber = mNumberInput.getText().toString();
                Toast.makeText(getBaseContext(), "phonenumber : "+mNumberInput.getText().toString(),
                        Toast.LENGTH_LONG).show();
            }
        });


        mSendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Random random = new Random();
                int i = random.nextInt(7);

                switch (i){
                    case 0 :
                        insult = " gros PD";
                        break;
                    case 1 :
                        insult = " connard";
                        break;
                    case 2 :
                        insult = " gros BATARD";
                        break;
                    case 3 :
                        insult = "e puuuuuuuteuuuuuh";
                        break;
                    case 4 :
                        insult = " noob";
                        break;
                    case 5 :
                        insult = "e tafiole";
                        break;
                    case 6 :
                        insult = " bon gros Tocard";
                        break;
                    default:
                        insult = " kiki mou";
                        break;
                }

                sendSMS(phoneNumber, message+insult+" !");
                mResultText.setText(message+insult+" !");
            }
        });

    }


    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
}
