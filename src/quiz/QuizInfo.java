package quiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lexiquiz.R;

import database.DataBaseHandler;
import database.Quiz;

public class QuizInfo extends Activity {
	
	private int idQuiz;	
	private Quiz quiz;	
	private DataBaseHandler db = new DataBaseHandler(this);
    private EditText etxt_for_enonce;
    private EditText etxt_for_reponse;
    private TextView txt_creez_votre_quiz;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.edition);
      	setButtonAndText();
        Bundle extras = getIntent().getExtras();
        this.idQuiz = Integer.parseInt(extras.getString("id")); 
		txt_creez_votre_quiz.setText("Modifier votre Quiz");
		
		this.quiz = this.db.getQuiz(this.idQuiz);
		etxt_for_enonce.setText(this.quiz.getTitre());
		etxt_for_reponse.setText(this.quiz.getAuteur());
	}

	/**
	 * Methode qui modifie un quiz de la base de données.
	 * @param view le bouton submit
	 */
	public void creation(View view){
    	this.quiz.setTitre(etxt_for_enonce.getText().toString());
    	this.quiz.setAuteur(etxt_for_reponse.getText().toString());
		this.db.updateQuiz(this.quiz);
		txt_creez_votre_quiz.setText("Quiz modifie !");
	}
	
	/**
	 * Methode qui initialise les elements de la vue.
	 * Appelee des la creation de QuestionCreate.
	 */
	public void setButtonAndText(){
	    etxt_for_enonce = (EditText) findViewById(R.id.etxt_for_title);
	    etxt_for_reponse = (EditText) findViewById(R.id.etxt_for_auteur);
	    txt_creez_votre_quiz = (TextView) findViewById(R.id.txt_creez_votre_quiz);
	}
}
