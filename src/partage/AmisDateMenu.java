package partage;

import com.example.lexiquiz.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AmisDateMenu extends Activity {
	
    Button btn_buttonOne;
    Button btn_buttonTwo;
    Button btn_buttonThree;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gerer);
		setButtonAndText();
		btn_buttonOne.setText("Les plus recents");
		btn_buttonTwo.setText("Les plus anciens");
		btn_buttonThree.setVisibility(View.INVISIBLE);
	}
	
	/**
	 * LES PLUS RECENTS
	 * @param view le premier bouton
	 */
	public void buttonOne(View view){
		Intent intent = new Intent(getApplicationContext(), AmisDownload.class);
        intent.putExtra("type", "get.php?TriDate=0");
    	startActivity(intent);
	}
	
	/**
	 * LES PLUS ANCIENS
	 * @param view le deuxieme bouton
	 */
	public void buttonTwo(View view){
		Intent intent = new Intent(getApplicationContext(), AmisDownload.class);
        intent.putExtra("type", "get.php?TriDate=1");
    	startActivity(intent);
	}
	
	/**
	 * Methode qui initialise les elements de la vue.
	 */
	public void setButtonAndText(){
        btn_buttonOne = (Button) findViewById(R.id.btn_buttonOne);
        btn_buttonTwo = (Button) findViewById(R.id.btn_buttonTwo);
        btn_buttonThree = (Button) findViewById(R.id.btn_buttonThree);
	}
}