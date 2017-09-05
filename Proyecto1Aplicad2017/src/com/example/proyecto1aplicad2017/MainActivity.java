package com.example.proyecto1aplicad2017;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint.Join;
import android.view.Menu;
import android.view.View;
import android.webkit.JsPromptResult;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

	private EditText etEmail;
	private EditText etPassword;
	private ImageButton iBtnLogin;
	ProgressDialog progressBar;
	private int progressBarStatus = 0;
	
	JSONObject jsonObject;

    private final int DURACION_SPLASH = 4000; // 3 segundos
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start();
	}
	
	
	public void start(){
		etEmail=(EditText)findViewById(R.id.etEmail);
		etPassword=(EditText)findViewById(R.id.etPassword);
		
		iBtnLogin=(ImageButton)findViewById(R.id.iBtnLogin);
		iBtnLogin.setOnClickListener(this);
	}
	

	@Override
	public void onClick(final View view) {
		if(view.getId()==this.iBtnLogin.getId()){
			//progress bar set
			 progressBar = new ProgressDialog(view.getContext());
             progressBar.setCancelable(false);
             progressBar.setMessage("Validating...");
             progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
             progressBar.setProgress(0);
             progressBar.setMax(100);
             progressBar.show();
		 
             
	         AsyncTask .execute(new Runnable() {
	 		 @Override
	 		 public void run() {
	 		   	//request url
	 		   	jsonObject = DoMethods.getUser("http://10.234.221.222:81/asp1.0/ServiceRest.svc/logInAndroid/"+etEmail.getText().toString()+"/"+etPassword.getText().toString());
  			    //result management
	 		   	if(jsonObject.isNull("logInAndroidResult")){
					progressBar.cancel();		
					runOnUiThread(new Runnable() {
					    public void run() {
					    	  Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_LONG).show();
					    }
					});                        
		        }else{
					String stringAnswer;
					try {
						stringAnswer = jsonObject.getString("logInAndroidResult");
						String subStringAnswer=stringAnswer.substring(1, stringAnswer.length()-1);
				    	String pieces[]= subStringAnswer.split(",");
						String emailPiece= pieces[0];
						String email=emailPiece.substring(8, emailPiece.length()-1);
						String passwordPiece=pieces[7];
						String password=passwordPiece.substring(12, passwordPiece.length()-1);
											
				    	Intent intent = new Intent(MainActivity.this, MenuActivity.class);
						intent.putExtra("user", email);
						startActivity(intent);
											      
						} catch (JSONException e) {
							e.printStackTrace();
							        			  }	
		        }   
	 		}

	     });
			
		}
		
	}
}
