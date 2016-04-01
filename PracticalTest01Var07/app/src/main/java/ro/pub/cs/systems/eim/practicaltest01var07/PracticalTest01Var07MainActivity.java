package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var07MainActivity extends AppCompatActivity {

    CheckBox firstCheckBoxButton = null;
    CheckBox secondCheckBoxButton = null;
    EditText nameEditText = null;
    EditText groupEditText = null;
    Button navigateToSecondActivityButton = null;

    private NavigateButtonListener navigateButtonListener = new NavigateButtonListener();
    private CheckBoxButtonOnCheckChangeClickListener checkBoxButtonOnCheckChangeClickListener = new CheckBoxButtonOnCheckChangeClickListener();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }

    private class CheckBoxButtonOnCheckChangeClickListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch(buttonView.getId()) {
                case R.id.first_checkbox:
                    if (firstCheckBoxButton.isChecked()) {
                        nameEditText.setEnabled(true);
                    } else {
                        nameEditText.setEnabled(false);
                    }
                    break;
                case R.id.second_checkbox:
                    if (secondCheckBoxButton.isChecked()) {
                        groupEditText.setEnabled(true);
                    } else {
                        groupEditText.setEnabled(false);
                    }
                    break;
            }
        }
    }

    private class NavigateButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class);
            intent.putExtra("name", nameEditText.getText().toString());
            intent.putExtra("group", groupEditText.getText().toString());
            startActivityForResult(intent, 2016);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);

        firstCheckBoxButton = (CheckBox) findViewById(R.id.first_checkbox);
        secondCheckBoxButton = (CheckBox) findViewById(R.id.second_checkbox);
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        groupEditText = (EditText) findViewById(R.id.group_edit_text);

        firstCheckBoxButton.setOnCheckedChangeListener(checkBoxButtonOnCheckChangeClickListener);
        secondCheckBoxButton.setOnCheckedChangeListener(checkBoxButtonOnCheckChangeClickListener);

        navigateToSecondActivityButton = (Button) findViewById(R.id.navigate_to_secondary_activity_button);
        navigateToSecondActivityButton.setOnClickListener(navigateButtonListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_var07_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("firstCheckBox", firstCheckBoxButton.isChecked());
        outState.putBoolean("secondCheckBox", secondCheckBoxButton.isChecked());
        outState.putString("nameEditText", nameEditText.getText().toString());
        outState.putString("groupEditText", groupEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            firstCheckBoxButton.setChecked(savedInstanceState.getBoolean("firstCheckBox"));
            secondCheckBoxButton.setChecked(savedInstanceState.getBoolean("secondCheckBox"));
            nameEditText.setText(savedInstanceState.getString("nameEditText"));
            groupEditText.setText(savedInstanceState.getString("groupEditText"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 2016) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var07Service.class);
        stopService(intent);
        super.onDestroy();
    }
}
