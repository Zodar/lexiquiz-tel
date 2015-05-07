package quiz;

import question.QuestionGerer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lexiquiz.R;

public class QuizMenu extends Activity {
	
	private String idQuiz;	
    Button btn_buttonOne;
    Button btn_buttonTwo;
    Button btn_buttonThree;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerer);
        setButtonAndText();
        btn_buttonOne.setText("Titre/Auteur");
        btn_buttonTwo.setText("Icon");
        btn_buttonThree.setText("Questions");

        Bundle extras = getIntent().getExtras();
        this.idQuiz = extras.getString("id"); 
    }
    
	/**
	 * BOUTON CREER QUESTIONS
	 * @param view le premier bouton
	 */
	public void buttonOne(View view){
    	Intent intent = new Intent(getApplicationContext(), QuizInfo.class);
        intent.putExtra("id", this.idQuiz);
    	startActivity(intent);
    }
	
	/**
	 * BOUTON MODIFIER QUESTION
	 * @param view le deuxieme bouton
	 */
    public void buttonTwo(View view){
    	Intent intent = new Intent(getApplicationContext(), IconUpload.class);
        intent.putExtra("id", this.idQuiz);
    	startActivity(intent);
    }
    
	/**
	 * BOUTON SUPPRIMER QUESTION
	 * @param view le troisieme bouton
	 */
    public void buttonThree(View view){
    	Intent intent = new Intent(getApplicationContext(), QuestionGerer.class);
        intent.putExtra("id", this.idQuiz);
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
