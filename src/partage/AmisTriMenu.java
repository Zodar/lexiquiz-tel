package partage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lexiquiz.R;

public class AmisTriMenu extends Activity {
	
    Button btn_buttonOne;
    Button btn_buttonTwo;
    Button btn_buttonThree;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerer);
        setButtonAndText();
        btn_buttonOne.setText("Rechercher par id");
        btn_buttonTwo.setText("Rechercher par titre");
        btn_buttonThree.setText("Rechercher par auteur");
    }
    
	/**
	 * BOUTON RECHERCHER PAR ID
	 * @param view le premier bouton
	 */
	public void buttonOne(View view){
    	Intent intent = new Intent(getApplicationContext(), TriId.class);
    	startActivity(intent);
    }
	
	/**
	 * BOUTON RECHERCHER PAR TITRE
	 * @param view le deuxieme bouton
	 */
    public void buttonTwo(View view){
    	Intent intent = new Intent(getApplicationContext(), TriTitre.class);
    	startActivity(intent);
    }
    
	/**
	 * BOUTON RECHERCHER PAR AUTEUR
	 * @param view le troisieme bouton
	 */
    public void buttonThree(View view){
    	Intent intent = new Intent(getApplicationContext(), TriAuteur.class);
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
