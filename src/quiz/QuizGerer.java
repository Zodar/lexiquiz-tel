package quiz;

import com.example.lexiquiz.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuizGerer extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerer);
    }
	
	/**
	 * Methode CREER QUIZ
	 * @param view le premier bouton
	 */
    public void buttonOne(View view){
    	Intent intent = new Intent(getApplicationContext(), QuizCreate.class);
        startActivity(intent);
	}
	
	/**
	 * Methode MODIFIER QUIZ
	 * @param view le deuxieme bouton
	 */
    public void buttonTwo(View view){
    	Intent intent = new Intent(getApplicationContext(), QuizModify.class);
    	startActivity(intent);
	}
	
	/**
	 * BOUTON SUPPRIMER QUIZ
	 * @param view le troisieme bouton
	 */
	public void buttonThree(View view){
    	Intent intent = new Intent(getApplicationContext(), QuizRemove.class);
        startActivity(intent);
	}
}