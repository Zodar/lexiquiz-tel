package mainLexiquiz;

import partage.AmisMenu;
import quiz.QuizChoix;
import quiz.QuizGerer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lexiquiz.R;

public class Menu extends Activity {
	
    Button btn_buttonOne;
    Button btn_buttonTwo;
    Button btn_buttonThree;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerer);
        setButtonAndText();
        btn_buttonOne.setText(R.string.play_quiz);
        btn_buttonTwo.setText(R.string.modify_quiz);
        btn_buttonThree.setText(R.string.share_quiz);
    }
    
	/**
	 * BOUTON JOUER !
	 * @param view le premier bouton
	 */
	public void buttonOne(View view){
    	Intent intent = new Intent(getApplicationContext(), QuizChoix.class);
    	startActivity(intent);
    }
	
	/**
	 * BOUTON GERER VOS QUIZ
	 * @param view le deuxieme bouton
	 */
    public void buttonTwo(View view){
    	Intent intent = new Intent(getApplicationContext(), QuizGerer.class);
    	startActivity(intent);
    }
    
	/**
	 * BOUTON PARTAGE DE QUIZ
	 * @param view le troisieme bouton
	 */
    public void buttonThree(View view){
    	Intent intent = new Intent(getApplicationContext(), AmisMenu.class);
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
