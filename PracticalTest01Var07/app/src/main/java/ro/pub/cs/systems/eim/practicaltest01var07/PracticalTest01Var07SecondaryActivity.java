package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var07SecondaryActivity extends AppCompatActivity {

    private TextView nameEditText = null;
    private TextView groupEditText = null;
    private Button okButton = null;
    private Button cancelButton = null;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.ok_button:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel_button:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_secondary);

        nameEditText = (TextView)findViewById(R.id.secondary_name_edit_text);
        groupEditText = (TextView)findViewById(R.id.secondary_group_edit_text);
        okButton = (Button)findViewById(R.id.ok_button);
        cancelButton = (Button)findViewById(R.id.cancel_button);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("name")) {
            String name = intent.getStringExtra("name");
            nameEditText.setText(String.valueOf(name));
        }

        if (intent != null && intent.getExtras().containsKey("group")) {
            String group = intent.getStringExtra("group");
            groupEditText.setText(String.valueOf(group));
        }
        okButton.setOnClickListener(buttonClickListener);
        cancelButton.setOnClickListener(buttonClickListener);
    }

}
