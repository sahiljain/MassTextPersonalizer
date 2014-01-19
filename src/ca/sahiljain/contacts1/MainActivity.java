package ca.sahiljain.contacts1;

import java.util.ArrayList;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.database.Cursor;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		final ArrayList<MyContact> contacts = new ArrayList<MyContact>();
		Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
		
		while (phones.moveToNext())
		{
		  String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
		//  name = name.split(" ")[0];
		  String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replaceAll("\\D+", "");
		  
		  contacts.add(new MyContact(name, phoneNumber));

		}
		phones.close();
		
		System.out.println(contacts.get(3).name);
		//TextView text1 = (TextView) findViewById(R.id.textView1);
	//	text1.setText(contacts.get(15).name + " " + contacts.get(5).phoneNumber);
		ScrollView sv = new ScrollView(this);
		final LinearLayout ll = new LinearLayout(this);
	    ll.setOrientation(LinearLayout.VERTICAL);
	    sv.addView(ll);
	    Button selectb = new Button(this);
	    selectb.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    selectb.setText("Select All");
	    ll.addView(selectb);
	    for(MyContact c : contacts){
	    	CheckBox cb = new CheckBox(this);
	    	cb.setText(c.name + " " + c.phoneNumber);
	    	c.cb = cb;
	    	ll.addView(cb);
	    }
	    Button b = new Button(this);
	    final SmsManager sms = SmsManager.getDefault();
	    b.setText("text them!");
	    ll.addView(b);
	    b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for(MyContact c: contacts){
					if(c.cb.isChecked()){
						String first = c.name.split(" ")[0];
				sms.sendTextMessage(c.phoneNumber, null, "Hey " + first + "! My new number is 647 894 8165. -Sahil Jain", null, null);
					Log.v("sahil",  "Hey " + first + "! My new number is 647 894 8165. -Sahil Jain");
					}
				
				}
				
			}
	    	
	    });
	    
	    
	  //  TextView dev = new TextView(this);
	 //   dev.setText("By: Sahil Jain");
	//    ll.addView(dev);
	    this.setContentView(sv);
	    
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
