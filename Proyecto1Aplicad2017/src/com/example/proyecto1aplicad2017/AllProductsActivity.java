package com.example.proyecto1aplicad2017;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AllProductsActivity extends Activity {

	//create components
	 private ListView lvProducts;
	 private String products;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_products);
		start();
	}


	//initialize components
	public void start(){
		
		//Extra
		products= getIntent().getStringExtra("products");
		
		//Products string management
		final String productsSplit[]=products.split(",");
		int productQuantity=productsSplit.length/12;
		int n=4;
		String nameString="";
		
		for (int i = 0; i < productQuantity; i++) {
			if(i==0){
				nameString+=productsSplit[n].substring(8, productsSplit[n].length()-1)+";";
			}else{
				if(i==1){
					n=n+12;
				nameString+=productsSplit[n].substring(8, productsSplit[n].length()-1)+";";
				}else{
					if(i==8){
						n=n+12;
						nameString+=productsSplit[n].substring(8, productsSplit[n].length()-1);	
						
						
					}else{
						n=n+12;
						nameString+=productsSplit[n].substring(8, productsSplit[n].length()-1)+";";	
					}
				}
			}
		}
		
		String nameSplit[]=nameString.split(";");
		
	
		//set adapter
		lvProducts = (ListView)findViewById(R.id.lvProducts);
		ArrayAdapter<String> adapterProduct = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameSplit);
		lvProducts.setAdapter(adapterProduct);
		
		
		//onSet
		lvProducts.setOnItemClickListener(new OnItemClickListener(){
			 
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				//Products details management
				int tam=arg0.getAdapter().getCount();
				int m=5;
				
				for (int j = 0; j < tam; j++) {
					String priceToInt=productsSplit[m+2].substring(7, 10);
					String priceToColon=productsSplit[m+3].substring(8, 13);
					int dollarPrice=Integer.parseInt(priceToInt);
					int productPrice=Integer.parseInt(priceToColon);
					
					int productDollarPrice= productPrice/dollarPrice;
							
					if(j==arg2){
						
						Toast.makeText(getApplicationContext(), productsSplit[m]+";"+productsSplit[m+1]+";"+"Price in dollar: "+productDollarPrice+";"+productsSplit[m+3], Toast.LENGTH_LONG).show();
						break; 	
					}else{
							m=m+12;
					}
					
				}

			}
		 
		}); 
		
	}//end of start
}//end of class
