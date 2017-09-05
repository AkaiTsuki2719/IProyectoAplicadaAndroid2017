package com.example.proyecto1aplicad2017;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class BuyActivity extends Activity implements  OnItemSelectedListener, View.OnClickListener{

	Spinner spinner;
	private ImageButton imgBtnFind;
	
	private EditText etByName;
	ProgressDialog progressBar;
	private int progressBarStatus = 0;
	
	private JSONObject jsonObject;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy);
		
		start();
	
	}

	//initialize components
	public void start(){
		
		imgBtnFind=(ImageButton)findViewById(R.id.imgBtnFind);
		imgBtnFind.setOnClickListener(this);
		
		etByName=(EditText)findViewById(R.id.etByName);
		
		spinner = (Spinner) findViewById(R.id.categories_spinner);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.categories_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		
	}
	
	@Override
	public void onClick(View view){
		if(view.getId()==imgBtnFind.getId()){
			
			//etByName is null?
			if(etByName.getText().toString().isEmpty()){
				//spinner onselected
				if(spinner.getSelectedItem().toString().equals("Nothing")){
		
					 //load bar
					 progressBar = new ProgressDialog(view.getContext());
		             progressBar.setCancelable(false);
		             progressBar.setMessage("Loading...");
		             progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		             progressBar.setProgress(0);
		             progressBar.setMax(100);
		             progressBar.show();
				 
		             //async thread to request
			         AsyncTask .execute(new Runnable() {
			 		 @Override
			 		 public void run() {
			 		    //URL request
			 		   	jsonObject = DoMethods.getAllProducts("http://10.234.221.222:81/asp1.0/ServiceRest.svc/selectAllProductAndroid");
		
			 		   	//jsonObject is null?
                 		if(jsonObject.isNull("selectAllProductAndroidResult")){
								
                 			progressBar.cancel();
								
                 			//toast thread
							runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(getApplicationContext(), "No products available", Toast.LENGTH_LONG).show();
							}
							});
					    }else{
							try {
								String stringAnswer = jsonObject.getString("selectAllProductAndroidResult");
								String subStringAnswer=stringAnswer.substring(2, stringAnswer.length()-2);	
								
								//open activity and extra
								Intent intent = new Intent(BuyActivity.this, AllProductsActivity.class);
								intent.putExtra("products", subStringAnswer);
								startActivity(intent);
													      
								progressBar.cancel();
													
								} catch (JSONException e) {
										e.printStackTrace();
													       }
						}
			 			 
			 		}

			     });
				}else{
					if(spinner.getSelectedItem().toString().equals("Woman")){
						
						
						
						 progressBar = new ProgressDialog(view.getContext());
			             progressBar.setCancelable(false);
			             progressBar.setMessage("Loading...");
			             progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			             progressBar.setProgress(0);
			             progressBar.setMax(100);
			             progressBar.show();
					 
				           	 AsyncTask .execute(new Runnable() {
				 			    @Override
				 			    public void run() {
				 			    	jsonObject = DoMethods.getAllProducts("http://10.234.221.222:81/asp1.0/ServiceRest.svc/selectProductbyCategory/"+spinner.getSelectedItem().toString());

												  if(jsonObject.isNull("selectProductbyCategoryResult")){
													progressBar.cancel();
													
													  runOnUiThread(new Runnable() {
														    public void run() {
														    	  Toast.makeText(getApplicationContext(), "No products available", Toast.LENGTH_LONG).show();
																  }
														});
					                                     
												  }else{
													
														try {
															String stringAnswer = jsonObject.getString("selectProductbyCategoryResult");
															String subStringAnswer=stringAnswer.substring(2, stringAnswer.length()-2);
															 
															android.util.Log.i("Loi roi : ",stringAnswer);	
															
															  Intent intent = new Intent(BuyActivity.this, AllProductsActivity.class);
														      intent.putExtra("products", subStringAnswer);
															  startActivity(intent);
														      
															 
															progressBar.cancel();
														
														} catch (JSONException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
														  
														
													
												  }
				 			 
				 			    }

				     });
						
					}else{
						if(spinner.getSelectedItem().toString().equals("Man")){
						
							progressBar = new ProgressDialog(view.getContext());
				             progressBar.setCancelable(false);
				             progressBar.setMessage("Loading...");
				             progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				             progressBar.setProgress(0);
				             progressBar.setMax(100);
				             progressBar.show();
						 
					           	 AsyncTask .execute(new Runnable() {
					 			    @Override
					 			    public void run() {
					 			    	jsonObject = DoMethods.getAllProducts("http://10.234.221.222:81/asp1.0/ServiceRest.svc/selectProductbyCategory/"+spinner.getSelectedItem().toString());

													  if(jsonObject.isNull("selectProductbyCategoryResult")){
														progressBar.cancel();
														
														  runOnUiThread(new Runnable() {
															    public void run() {
															    	  Toast.makeText(getApplicationContext(), "No products available", Toast.LENGTH_LONG).show();
																	  }
															});
						                                     
													  }else{
														
															try {
																String stringAnswer = jsonObject.getString("selectProductbyCategoryResult");
																String subStringAnswer=stringAnswer.substring(2, stringAnswer.length()-2);
																 
																android.util.Log.i("Loi roi : ",stringAnswer);	
																
																  Intent intent = new Intent(BuyActivity.this, AllProductsActivity.class);
															      intent.putExtra("products", subStringAnswer);
																  startActivity(intent);
															      
																 
																progressBar.cancel();
															
															} catch (JSONException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
															  
															
														
													  }
					 			 
					 			    }

					     });
							
						}else{
							if(spinner.getSelectedItem().toString().equals("Boy")){
								
								progressBar = new ProgressDialog(view.getContext());
					             progressBar.setCancelable(false);
					             progressBar.setMessage("Loading...");
					             progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
					             progressBar.setProgress(0);
					             progressBar.setMax(100);
					             progressBar.show();
							 
						           	 AsyncTask .execute(new Runnable() {
						 			    @Override
						 			    public void run() {
						 			    	jsonObject = DoMethods.getAllProducts("http://10.234.221.222:81/asp1.0/ServiceRest.svc/selectProductbyCategory/"+spinner.getSelectedItem().toString());

														  if(jsonObject.isNull("selectProductbyCategoryResult")){
															progressBar.cancel();
															
															  runOnUiThread(new Runnable() {
																    public void run() {
																    	  Toast.makeText(getApplicationContext(), "No products available", Toast.LENGTH_LONG).show();
																		  }
																});
							                                     
														  }else{
															
																try {
																	String stringAnswer = jsonObject.getString("selectProductbyCategoryResult");
																	String subStringAnswer=stringAnswer.substring(2, stringAnswer.length()-2);
																	 
																	android.util.Log.i("Loi roi : ",stringAnswer);	
																	
																	  Intent intent = new Intent(BuyActivity.this, AllProductsActivity.class);
																      intent.putExtra("products", subStringAnswer);
																	  startActivity(intent);
																      
																	 
																	progressBar.cancel();
																
																} catch (JSONException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																}
																  
																
															
														  }
						 			 
						 			    }

						     });
								
							}else{
								if(spinner.getSelectedItem().toString().equals("Girl")){
									
									progressBar = new ProgressDialog(view.getContext());
						             progressBar.setCancelable(false);
						             progressBar.setMessage("Loading...");
						             progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
						             progressBar.setProgress(0);
						             progressBar.setMax(100);
						             progressBar.show();
								 
							           	 AsyncTask .execute(new Runnable() {
							 			    @Override
							 			    public void run() {
							 			    	jsonObject = DoMethods.getAllProducts("http://10.234.221.222:81/asp1.0/ServiceRest.svc/selectProductbyCategory/"+spinner.getSelectedItem().toString());

															  if(jsonObject.isNull("selectProductbyCategoryResult")){
																progressBar.cancel();
																
																  runOnUiThread(new Runnable() {
																	    public void run() {
																	    	  Toast.makeText(getApplicationContext(), "No products available", Toast.LENGTH_LONG).show();
																			  }
																	});
								                                     
															  }else{
																
																	try {
																		String stringAnswer = jsonObject.getString("selectProductbyCategoryResult");
																		String subStringAnswer=stringAnswer.substring(2, stringAnswer.length()-2);
																		 
																		android.util.Log.i("Loi roi : ",stringAnswer);	
																		
																		  Intent intent = new Intent(BuyActivity.this, AllProductsActivity.class);
																	      intent.putExtra("products", subStringAnswer);
																		  startActivity(intent);
																	      
																		 
																		progressBar.cancel();
																	
																	} catch (JSONException e) {
																		// TODO Auto-generated catch block
																		e.printStackTrace();
																	}
																	  
																	
																
															  }
							 			 
							 			    }

							     });
									
								}
							}
								
						}
							
					}
				}
			}else{
				 progressBar = new ProgressDialog(view.getContext());
	             progressBar.setCancelable(false);
	             progressBar.setMessage("Loading...");
	             progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	             progressBar.setProgress(0);
	             progressBar.setMax(100);
	             progressBar.show();
			 
		           	 AsyncTask .execute(new Runnable() {
		 			    @Override
		 			    public void run() {
		 			    	jsonObject = DoMethods.getAllProducts("http://10.234.221.222:81/asp1.0/ServiceRest.svc/selectProductbyName/"+etByName.getText().toString());

										  if(jsonObject.isNull("selectProductbyNameResult")){
											progressBar.cancel();
											
											  runOnUiThread(new Runnable() {
												    public void run() {
												    	  Toast.makeText(getApplicationContext(), "No products available", Toast.LENGTH_LONG).show();
														  }
												});
			                                     
										  }else{
											
												try {
													String stringAnswer = jsonObject.getString("selectProductbyNameResult");
													String subStringAnswer=stringAnswer.substring(2, stringAnswer.length()-2);
													 
													android.util.Log.i("Loi roi : ",stringAnswer);	
													
													  Intent intent = new Intent(BuyActivity.this, AllProductsActivity.class);
												      intent.putExtra("products", subStringAnswer);
													  startActivity(intent);
												      
													 
													progressBar.cancel();
												
												} catch (JSONException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												  
										  }
		 			    }

		     });
			}
						
			}

	}


	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
