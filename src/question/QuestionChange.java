package question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.lexiquiz.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import database.DataBaseHandler;
import database.Question;

public class QuestionChange extends Activity {

	private int idQuiz;
	private List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
	private DataBaseHandler db = new DataBaseHandler(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
        Bundle extras = getIntent().getExtras();
        this.idQuiz = Integer.parseInt(extras.getString("id"));
		setContentView(R.layout.quiz_choix);
		affichageListeQuestions();
	}
	
//	protected void onRestart(){
//		super.onRestart();
//		setContentView(R.layout.quiz_choix);
//		affichageListeQuestions();
//	}
	
	/**
	 * Methode qui affiche les questions à modifier.
	 * 
	 */
	public void affichageListeQuestions(){

		List<Question> all = db.getAllQuestionSansNiveau(idQuiz);
		if(all != null){
			for (Question q : all) {
				HashMap<String, String> hm = new HashMap<String, String>();
				hm.put("enonce", "Enonce : " + q.getEnonce());
				hm.put("reponse", "Reponse : " + q.getReponse());
				hm.put("id", "" + q.getId());
				aList.add(hm);
			}
			String[] from = { "enonce", "reponse" };
			int[] to = { R.id.txt_enonce_simplerow, R.id.txt_reponse_simplerow };
			SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.simplerow_without_img, from, to);
			ListView listQuiz = (ListView) findViewById(R.id.lt_listQuiz);
			listQuiz.setAdapter(adapter);
			OnItemClickListener itemClickListener = new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View container,
						int position, long id) {
					callQuestionModificationActivity(aList.get(position).get("id"));
				}
			};
			listQuiz.setOnItemClickListener(itemClickListener);
		}
	}
	
	/**
	 * Methode qui change d'activité pour QuestionModify
	 * @param idQuestion l'id de la question.
	 */
	public void callQuestionModificationActivity(String idQuestion){
    	Intent intent = new Intent(getApplicationContext(), QuestionModify.class);
        intent.putExtra("id", idQuestion);
        startActivity(intent);
	}
}
