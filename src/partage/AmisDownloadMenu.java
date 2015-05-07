package partage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lexiquiz.R;

public class AmisDownloadMenu extends Activity {
	
    Button btn_buttonOne;
    Button btn_buttonTwo;
    Button btn_buttonThree;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerer);
        setButtonAndText();
        btn_buttonOne.setText("Voir tous les quiz");
        btn_buttonTwo.setText("Filtrer par infos");
        btn_buttonThree.setText("Filtrer par dates");
    }
    
	/**
	 * BOUTON VOIR TOUS LES QUIZ
	 * @param view le premier bouton
	 */
	public void buttonOne(View view){
    	Intent intent = new Intent(getApplicationContext(), AmisDownload.class);
        intent.putExtra("type", "get.php");
    	startActivity(intent);
    }
	
	/**
	 * BOUTON FILTRER PAR INFOS
	 * @param view le deuxieme bouton
	 */
    public void buttonTwo(View view){
    	Intent intent = new Intent(getApplicationContext(), AmisTriMenu.class);
    	startActivity(intent);
    }
    
	/**
	 * BOUTON FILTRER PAR DATES
	 * @param view le troisieme bouton
	 */
    public void buttonThree(View view){
    	Intent intent = new Intent(getApplicationContext(), AmisDateMenu.class);
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