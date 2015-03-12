package com.competitiveexamapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import com.androidplot.xy.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

public class SimpleXYPlotActivity extends Activity{

	private XYPlot plot;
	InputStream is, as;
	StringBuilder sb;
	 String uname, result;
	 Number [] series1Numbers;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
 
        // fun little snippet that prevents users from taking screenshots
        // on ICS+ devices :-)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                                 WindowManager.LayoutParams.FLAG_SECURE);
 
        setContentView(R.layout.simple_xy_plot_example);
 
        // initialize our XYPlot reference:
        plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
        SharedPreferences user = getBaseContext().getSharedPreferences("userdetails", MODE_PRIVATE);
		uname = user.getString("username", "");
        try{
			
    		JSONObject json = new JSONObject();
    		Log.e("log_tag", "In result data 2" );
        	System.out.println(""+ uname);
    	  	json.put("uname",uname);
            HttpPost httppost = new HttpPost("http://192.168.1.11/result1.php");
            List<NameValuePair> nvp = new ArrayList<NameValuePair>(1);
    	  	nvp.add(new BasicNameValuePair("json",json.toString()));
    	  	httppost.setEntity(new UrlEncodedFormEntity(nvp));
    	  	Log.e("log_tag", "In result data 3" );
            HttpClient httpclient1 = new DefaultHttpClient();

            HttpResponse response1 = httpclient1.execute(httppost);
            HttpEntity entity = response1.getEntity();
            is = entity.getContent();
            Log.e("log_tag", "In result data 4" );
          
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                sb = new StringBuilder();
                sb.append(reader.readLine() + "\n");
                String line="0";
              
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                 
                is.close();
                result=sb.toString();
                Log.e("log_tag", "In result data 5" + result.toString() );
    		}catch(Exception e){
    			Log.e("log_tag", "error some where here...." + e.toString() );
    		}
        try{
			JSONArray jArray = new JSONArray(result);
	//		 object = new JSONObject(result);
			Log.e("log_tag", "In result data 9" );
//			object = object.optJSONObject("ar");
	//		JSONArray jA = object.getJSONArray();
			Log.e("log_tag", "In result data 10" );
		JSONObject object = null;
		series1Numbers = new Number [jArray.length()] ;
		int j=0;
		for(int i=0;i<jArray.length();i++){
			
					object = jArray.getJSONObject(i);
					Log.e("log_tag", "In result data 11" + i);
					series1Numbers[j]=object.optInt("percentage");
					  i++;
					  
					  j++;
		    			Log.e("log_tag", "In result data 12" +jArray.length());
		     //           op8=object.optString("percentage");
		}
        }catch(Exception e){
			Log.e("log_tag", "error some where here down...." + e.toString() );
		}
        
        
        
        
        // Create a couple arrays of y-values to plot:
 //       Number[] series1Numbers = {0, 75, 82, 50, 62, 74, 64, 100, 91};
       // Number[] series2Numbers = {4, 6, 3, 8, 2, 10};
 
        // Turn the above arrays into XYSeries':
        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(series1Numbers),          // SimpleXYSeries takes a List so turn our array into a List
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
                "Mock Exam");                             // Set the display title of the series
 
        // same as above
    //    XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");
 
        // Create a formatter to use for drawing a series using LineAndPointRenderer
        // and configure it from xml:
        LineAndPointFormatter series1Format = new LineAndPointFormatter();
        series1Format.setPointLabelFormatter(new PointLabelFormatter());
        series1Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf1);
 
        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);
 
        // same as above:
  /*      LineAndPointFormatter series2Format = new LineAndPointFormatter();
        series2Format.setPointLabelFormatter(new PointLabelFormatter());
        series2Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf2);
        plot.addSeries(series2, series2Format);*/
 
        // reduce the number of range labels
        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);
 
    }
	
}
