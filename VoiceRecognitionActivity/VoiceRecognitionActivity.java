package com.example;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
 
 
 public class VoiceRecognitionActivity extends Activity {
 
 private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
 
/**
 * The function that start the voice recognition.
 */
 private void startVoiceRecognitionActivity() {        
    	Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);         
    	intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);         
    	intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "");         
    	startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);     
    }

/**
 *	Where you can handle the result of the voice recgnition.
 */
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {         
     if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {           
      ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);                        
      final CharSequence[] items = new CharSequence[matches.size()];    
      for(int i = 0; i < matches.size(); i++){     
        items[i] = matches.get(i);    
      }                    
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setItems(items, new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int item) {
		 //Do what you want with the text
        	 Toast.makeText(MainActivity.this, "text: "+ items[item].toString(), Toast.LENGTH_LONG).show();
         
         }
      });
             
      AlertDialog alert = builder.create();          
      alert.show();                      
     }           
     super.onActivityResult(requestCode, resultCode, data);     
    }
}