package partage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lexiquiz.R;

public class AmisMenu extends Activity {
    
	Button btn_buttonOne;
    Button btn_buttonTwo;
    Button btn_buttonThree;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerer);
        setButtonAndText();
        btn_buttonOne.setText(R.string.download_quiz);
        btn_buttonTwo.setText(R.string.upload_quiz);
        btn_buttonThree.setVisibility(View.INVISIBLE);
    }
    
	/**
	 * BOUTON TELECHARGER
	 * @param view le premier bouton
	 */
	public void buttonOne(View view){
    	Intent intent = new Intent(getApplicationContext(), AmisDownloadMenu.class);
    	startActivity(intent);
    }
	
	/**
	 * BOUTON PARTAGER  
	 * @param view le deuxieme bouton
	 */
    public void buttonTwo(View view){
    	Intent intent = new Intent(getApplicationContext(), AmisUploadAsync.class);
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
