package question;

import com.example.lexiquiz.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuestionGerer extends Activity {
	
	private String idQuiz;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerer);
        Bundle extras = getIntent().getExtras();
        this.idQuiz = extras.getString("id"); 
    }
    
	/**
	 * Methode CREER QUESTION
	 * @param view le premier bouton
	 */
	public void buttonOne(View view){
    	Intent intent = new Intent(getApplicationContext(), QuestionCreate.class);
        intent.putExtra("id", this.idQuiz);
    	startActivity(intent);
    }
	
	/**
	 * Methode MODIFIER QUESTION
	 * @param view le deuxieme bouton
	 */
    public void buttonTwo(View view){
    	Intent intent = new Intent(getApplicationContext(), QuestionChange.class);
        intent.putExtra("id", this.idQuiz);
    	startActivity(intent);
    }
    
    
	/**
	 * BOUTON SUPPRIMER QUESTION
	 * @param view le troisieme bouton
	 */
    public void buttonThree(View view){
    	Intent intent = new Intent(getApplicationContext(), QuestionRemove.class);
        intent.putExtra("id", this.idQuiz);
    	startActivity(intent);
    }
}
