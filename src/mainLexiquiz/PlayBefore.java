package mainLexiquiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lexiquiz.R;

import database.DataBaseHandler;

public class PlayBefore extends Activity {

	private int idQuiz;
	private DataBaseHandler db = new DataBaseHandler(this);
	private TextView txt_nbquestion;
	private TextView txt_nb_question_level0;
	private TextView txt_title;
	private ImageView img_icon;

	private double nbAllLevel;
	private double nbLevel0;
	private double nbLevel3;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		setContentView(R.layout.play_before);
		setButtonAndText();

		this.idQuiz = Integer.parseInt(extras.getString("id"));
		if (this.db.getQuiz(this.idQuiz).getIcon().equals("uneImage")) {

		} else {
			Bitmap bitMapImage = BitmapFactory.decodeByteArray(
					this.db.getQuiz(this.idQuiz).getIcon(), 0,
					this.db.getQuiz(this.idQuiz).getIcon().length);
			img_icon.setImageBitmap(bitMapImage);
		}
		txt_title.setText(this.db.getQuiz(this.idQuiz).getTitre());
		affichePourcentage();
	}

	protected void onResume() {
		super.onResume();
		affichePourcentage();
	}

	/**
	 * Methode qui affiche le pourcentage de connaissance
	 * 
	 */
	public void affichePourcentage() {
		double pourcentageAcquis;
		double pourcentageVu;
		this.nbAllLevel = this.db.countQuestion(this.idQuiz, -1);
		this.nbLevel0 = this.db.countQuestion(this.idQuiz, 0);
		this.nbLevel3 = this.db.countQuestion(this.idQuiz, 3);

		if (this.nbAllLevel == 0) {
			txt_nbquestion.setText("Oups !");
			txt_nb_question_level0.setText("Ce quiz ne contient aucune question !");
			findViewById(R.id.btn_launch).setVisibility(View.INVISIBLE);
		}
		else {
			if(this.nbLevel0 == this.nbAllLevel){
				txt_nb_question_level0.setText("0% questions vues");
			}
			else if(this.nbLevel0 == 0){
				txt_nb_question_level0.setText("100% questions vues");
			}
			else {
				pourcentageVu = ((this.nbAllLevel - this.nbLevel0) / this.nbAllLevel) * 100;
				java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
				txt_nb_question_level0.setText(df.format(pourcentageVu) + "% questions vues");
			}
			
			if (this.nbLevel3 == 0){
				txt_nbquestion.setText("0% connaissances acquises");
			}
			else if (this.nbAllLevel == this.nbLevel3){
				txt_nbquestion.setText("100% connaissances acquises");
			}else {
				pourcentageAcquis = (this.nbLevel3 / this.nbAllLevel) * 100;
				java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
				txt_nbquestion.setText(df.format(pourcentageAcquis) + "% connaissances acquises");
			}
		}
	}

	/**
	 * Methode qui permet de lancer le quiz
	 * 
	 * @param view  le bouton lancer
	 */
	public void lancer(View view) {
		this.db.close();
		Intent intent = new Intent(getApplicationContext(), Play.class);
		intent.putExtra("id", Integer.toString(this.idQuiz));
		startActivity(intent);
	}

	/**
	 * Methode qui initialise les elements de la vue. Appelee des la creation de
	 * PlayBefore.
	 */
	public void setButtonAndText() {
		txt_nbquestion = (TextView) findViewById(R.id.txt_nbquestion);
		txt_nb_question_level0 = (TextView) findViewById(R.id.txt_nb_question_level0);
		txt_title = (TextView) findViewById(R.id.txt_title);
		img_icon = (ImageView) findViewById(R.id.img_icon_quiz_before);
	}
}
