package com.example.customview;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MyView extends View{
	//private PointF point;
    private Paint paint;
    String json="";
    JSONObject jObject;
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //if (point != null)
    	String text="";
		DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
		DefaultHttpClient defaultClient = new DefaultHttpClient();
		// Setup the get request
		HttpGet httpGetRequest = new HttpGet("http://10.0.2.2:8080");
		try{
		// Execute the request in the client
		HttpResponse httpResponse = defaultClient.execute(httpGetRequest);
		// Grab the response
		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
		json = reader.readLine();
		
		}catch(Exception e){
			Log.d("Error",e.toString());
		}
	


try{	
	// 	Instantiate a JSON object from the request response
		jObject = new JSONObject(json);
		RectF rectF;
		    text = jObject.getString("name");
		    //Toast.makeText(this, aJsonString, Toast.LENGTH_SHORT).show();
		    Log.d("Check",text);
	}
	catch(Exception e){
	    Log.d("Error",e.toString());
	}
            
			int radius = 50;
            int x=10,y=10;
            
			try {
				x = jObject.getInt("X");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
			try {
				y = jObject.getInt("Y");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                paint.setColor(Color.LTGRAY);
                
				canvas.drawOval(new RectF(x-20,y-20,x+30,y+20), paint);//Circle(x, y, radius, paint);
                paint.setColor(Color.BLUE);
                paint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(text, x, y, paint);
        
    }
    
	
	

}
