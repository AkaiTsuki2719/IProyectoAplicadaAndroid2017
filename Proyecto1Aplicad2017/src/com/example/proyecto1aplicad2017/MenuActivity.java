package com.example.proyecto1aplicad2017;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity implements View.OnClickListener{

	private String user;
	private ImageButton imgBtnBuy;
	private ImageButton imgBtnMyBuys;
	private ImageButton imgBtnProfile;
	private ImageButton imgBtnAbout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		start();
	}

	//inicialize components
	public void start(){
		
		
		user= getIntent().getStringExtra("user");
		
		 Toast.makeText(getApplicationContext(), "Welcome: "+user, Toast.LENGTH_LONG).show();
		 
		 imgBtnBuy=(ImageButton)findViewById(R.id.imgBtnBuy);
			imgBtnBuy.setOnClickListener(this);
			imgBtnMyBuys=(ImageButton)findViewById(R.id.imgBtnMyBuys);
			imgBtnMyBuys.setOnClickListener(this);
			imgBtnProfile=(ImageButton)findViewById(R.id.imgBtnProfile);
			imgBtnProfile.setOnClickListener(this);
			imgBtnAbout=(ImageButton)findViewById(R.id.imgBtnAbout);
			imgBtnAbout.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view){
		if(view.getId()==imgBtnBuy.getId()){
			
			//start activity buy
			Intent intent = new Intent(MenuActivity.this, BuyActivity.class);
            startActivity(intent);
			
		}else{
			if(view.getId()==imgBtnMyBuys.getId()){
				
			}else{
				if(view.getId()==imgBtnProfile.getId()){
					
				}else{
					if(view.getId()==imgBtnAbout.getId()){
						
					}
				}
			}
		}
		
		
	}
	

}
