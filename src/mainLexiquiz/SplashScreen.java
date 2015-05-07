package mainLexiquiz;

import java.io.ByteArrayOutputStream;

import com.example.lexiquiz.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import database.DataBaseHandler;
import database.Question;
import database.Quiz;
 
public class SplashScreen extends Activity {
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new PrefetchData().execute(); 
    }
 
    private class PrefetchData extends AsyncTask<Void, Void, Void> {
    	
    	private byte[] icon;
 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
        	
        	DataBaseHandler db = new DataBaseHandler(SplashScreen.this);
			if( db.getAllQuiz(true) == null){
		    	Log.d("Insert: ", "Inserting quiz..");
		        Quiz anglais = new Quiz("Anglais", "LexiQuiz");
		        Quiz allemand = new Quiz("Allemand", "LexiQuiz");
		        Quiz italien = new Quiz("Italien", "LexiQuiz");
		        Quiz QUIZ_DE_TEST = new Quiz("QUIZ_DE_TEST", "QUIZ_DE_TEST");

		        insertIcon(R.drawable.drapeau_angleterre);
		        anglais.setIcon(icon);
		        
		        insertIcon(R.drawable.drapeau_allemand);
		        allemand.setIcon(icon);
		        
		        insertIcon(R.drawable.drapeau_italie);
		        italien.setIcon(icon);
		        
		        insertIcon(R.drawable.listviewquiz);
		        QUIZ_DE_TEST.setIcon(icon);
		        
		    	db.addQuiz(anglais);
		    	db.addQuiz(allemand);
		    	db.addQuiz(italien);
		    	db.addQuiz(QUIZ_DE_TEST);
		        db.insertList_Questions(1, db.readCsv(SplashScreen.this, "anglais.csv"), 0, true);
		        db.insertList_Questions(1, db.readCsv(SplashScreen.this, "verbes-irreguliers-anglais.csv"), 0, false);
		        db.insertList_Questions(2, db.readCsv(SplashScreen.this, "verbes-irreguliers-allemands.csv"), 0, false);
		        db.insertList_Questions(3, db.readCsv(SplashScreen.this, "verbes-irreguliers-italiens.csv"), 0, false);
		        
		        Question q = new Question(4, "testQ", "testR", 0);
		        Question q2 = new Question(4, "testQ2", "testR2", 0);
		        db.addQuestion(q);
		        db.addQuestion(q2);
			}
            return null;
        }
        
        public void insertIcon(int idDrawable){
        	Resources res = getResources();
	        Drawable drawable = res.getDrawable(idDrawable);
	        BitmapDrawable bitDw = ((BitmapDrawable) drawable);
	        Bitmap bitmap = bitDw.getBitmap();
	        ByteArrayOutputStream stream = new ByteArrayOutputStream();
	        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
	        icon = stream.toByteArray();
        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
           ;
            Intent i = new Intent(SplashScreen.this, Menu.class);
            startActivity(i);
            finish();
        }
    }
}