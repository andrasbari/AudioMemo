package net.bandesz.auditorymemorygame;

import java.sql.SQLException;

import net.bandesz.auditorymemorygame.DataManager;
import net.bandesz.auditorymemorygame.model.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewPlayer extends Activity {
	private DataManager	dm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_player);

		dm = DataManager.getInstance(getApplicationContext());
		final EditText etNameValue = (EditText) findViewById(R.id.nameValue);
		final Spinner spGenderValue = (Spinner) findViewById(R.id.genderValue);
		final EditText etbirthYearValue = (EditText) findViewById(R.id.birthYearValue);
		Button btnSave = (Button) findViewById(R.id.save);
		Button btnCancel = (Button) findViewById(R.id.cancel);

		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (etNameValue.getText().toString().contentEquals("") || etbirthYearValue.getText().toString().contentEquals("")) {
					Toast.makeText(getApplicationContext(), "Minden mezőt meg kell adni!", Toast.LENGTH_SHORT).show();

				} else {
					User newUser = new User(etNameValue.getText().toString(), spGenderValue.getSelectedItemPosition() + 1, Integer.parseInt(etbirthYearValue.getText().toString()));
					try {
						dm.addUser(newUser);
						Toast.makeText(getApplicationContext(), "Felhasználó hozzáadva!", Toast.LENGTH_SHORT).show();
						finish();
						Intent intent = new Intent(getApplicationContext(), Settings.class);
						startActivity(intent);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});

		btnCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Settings.class);
				startActivity(intent);
				finish();

			}
		});
	}

}
