package mainLexiquiz;

import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.lexiquiz.R;

import database.DataBaseHandler;
import database.Question;
import database.Quiz;

public class Play extends Activity {

	final static int COLORLEVEL1A = 215;
	final static int COLORLEVEL1B = 35;
	final static int COLORLEVEL1C = 15;

	final static int COLORLEVEL2A = 77;
	final static int COLORLEVEL2B = 95;
	final static int COLORLEVEL2C = 255;

	final static int COLORLEVEL3A = 211;
	final static int COLORLEVEL3B = 153;
	final static int COLORLEVEL3C = 18;

	private Quiz quiz;
	private int idAuHasard;
	private List<Question> questions = null;
	private DataBaseHandler db = new DataBaseHandler(this);
	
    private Button btn_next_question;
	private Button btn_ask;
    private Button btn_answer;
    private SeekBar sk_niveau;
    private TextView txt_niveau;
    private TextView txt_title;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.play);
    	setButtonAndText();
        this.quiz = this.db.getQuiz(Integer.parseInt(extras.getString("id")));
        txt_title.setText("Quiz : " + this.quiz.getTitre());
        next(new View(getApplicationContext()));
	}
	
	/**
	 * Methode qui pose une question du quiz.
	 */
	public void ask(){
		int niveauQuestion;
        
        int hasard = (int) ((Math.random() * 100) + 1);
		if(hasard <= 70 ) niveauQuestion = 0;
		else if(hasard <= 85 ) niveauQuestion = 1;
		else if(hasard <= 95 ) niveauQuestion = 2;
		else niveauQuestion = 3;
		
		this.questions = db.getAllQuestion(this.quiz.getId(), niveauQuestion);
		
		niveauQuestion = 0;
		while(this.questions == null){
        	this.questions = this.db.getAllQuestion(this.quiz.getId(), niveauQuestion);
        	niveauQuestion ++;
		}
		
//		if((niveauQuestion != 0) && (db.getAllQuestionSansNiveau(this.quiz.getId()).size() < 5)){
//			if((db.getAllQuestion(this.quiz.getId(), 0) != null) && (db.getAllQuestion(this.quiz.getId(), 0).size() > 3)){
//	        	this.questions = this.db.getAllQuestion(this.quiz.getId(), 0);
//			}
//		}
		
		this.idAuHasard = (int)(Math.random() * this.questions.size());
		
		btn_ask.setText(this.questions.get(this.idAuHasard).getEnonce());
	}
	
	/**
	 * Methode qui montre la réponse et gere la SeekBar, enrengistre le niveau.
	 */
	public void answer(){
		btn_answer.setText(this.questions.get(this.idAuHasard).getReponse());
		switch (this.questions.get(this.idAuHasard).getNiveau()) {
		case 1:
			sk_niveau.setProgress(1);
//			sk_niveau.setBackgroundColor(Color.rgb(COLORLEVEL1A, COLORLEVEL1B, COLORLEVEL1C));
			btn_answer.setBackgroundColor(Color.rgb(COLORLEVEL1A, COLORLEVEL1B, COLORLEVEL1C));		
			break;
		case 2:
			sk_niveau.setProgress(50);
//			sk_niveau.setBackgroundColor(Color.rgb(COLORLEVEL2A, COLORLEVEL2B, COLORLEVEL2C));
			btn_answer.setBackgroundColor(Color.rgb(COLORLEVEL2A, COLORLEVEL2B, COLORLEVEL2C));
			break;
		case 3:
			sk_niveau.setProgress(100);
//			sk_niveau.setBackgroundColor(Color.rgb(COLORLEVEL3A, COLORLEVEL3B, COLORLEVEL3C));
			btn_answer.setBackgroundColor(Color.rgb(COLORLEVEL3A, COLORLEVEL3B, COLORLEVEL3C));
			break;
		default:
			break;
		}
		
		sk_niveau.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) { }
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {	}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(progress == 0){
					//NOTHING Empeche juste le different de zero
				}
				else if(progress < 33) {
			    	btn_next_question.setVisibility(View.VISIBLE);
					sk_niveau.setProgress(1);
//					sk_niveau.setBackgroundColor(Color.rgb(COLORLEVEL1A, COLORLEVEL1B, COLORLEVEL1C));
					txt_niveau.setText("Je ne savais pas");
					Play.this.db.updateNiveau(Play.this.questions.get(Play.this.idAuHasard).getId(), 1);
				}
				else if(progress < 66) {
			    	btn_next_question.setVisibility(View.VISIBLE);
					sk_niveau.setProgress(50);
					sk_niveau.setProgressDrawable(getResources().getDrawable(R.drawable.cpbh2));
//					sk_niveau.setBackgroundColor(Color.rgb(COLORLEVEL2A, COLORLEVEL2B, COLORLEVEL2C));
					txt_niveau.setText("Je le saurais");
					Play.this.db.updateNiveau(Play.this.questions.get(Play.this.idAuHasard).getId(), 2);
				}
				else {
			    	btn_next_question.setVisibility(View.VISIBLE);
					sk_niveau.setProgress(100);
					sk_niveau.setProgressDrawable(getResources().getDrawable(R.drawable.cpbh3));
//					sk_niveau.setBackgroundColor(Color.rgb(COLORLEVEL3A, COLORLEVEL3B, COLORLEVEL3C));
					txt_niveau.setText("Je le sais !");
					Play.this.db.updateNiveau(Play.this.questions.get(Play.this.idAuHasard).getId(), 3);
				}
			}
		});
	}
	
	/**
	 * Methode qui change la vue (mode enonce) et lance ask()
	 * @param view le bouton suivant
	 */
	public void next(View view){
		btn_answer.setText("Voir la reponse !");
    	btn_answer.setBackgroundColor(Color.parseColor("#663366"));
//		sk_niveau.setBackgroundColor(Color.rgb(COLORLEVEL1A, COLORLEVEL1B, COLORLEVEL1C));
        sk_niveau.setVisibility(View.INVISIBLE);
        txt_niveau.setVisibility(View.INVISIBLE);
		btn_next_question.setVisibility(View.INVISIBLE);
		ask();
	}
	
	/**
	 * Methode qui change la vue (mode reponse) et lance answer()
	 * @param view le bouton voir la réponse !
	 */
	public void showAnswer(View view){
    	btn_answer.setBackgroundColor(Color.parseColor("#333333"));
        sk_niveau.setVisibility(View.VISIBLE);
        txt_niveau.setVisibility(View.VISIBLE);
        answer();
	}
	
	/**
	 * Methode qui initialise les elements de la vue.
	 * Appelee des la creation de Play.
	 */
	public void setButtonAndText(){
	    sk_niveau = (SeekBar) findViewById(R.id.sk_niveau);
	    btn_next_question = (Button) findViewById(R.id.btn_next_question);
	    txt_niveau = (TextView) findViewById(R.id.txt_niveau);
	    btn_answer = (Button) findViewById(R.id.btn_answer);
		btn_ask = (Button) findViewById(R.id.btn_ask);
		txt_title = (TextView) findViewById(R.id.txt_titre_simplerow);
	}
}
