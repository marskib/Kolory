package autyzmsoft.pl.kolory;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by developer on 2017-11-10.
 */

public class SplashKlasa extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        Button klawisz = (Button) findViewById(R.id.button1);
        klawisz.setOnClickListener(this);

        TextView autyzmsoftpl = (TextView) findViewById(R.id.autyzmsoftpl);
        autyzmsoftpl.setGravity(Gravity.CENTER);

    }  //onCreate

    public void onClick(View arg0) {
        finish(); //zamyka aktywnosc i wraca do aktywnosci macierzystej
    }

		/* stare rozwiazanie - tez ok....  :

		Handler handler1 = new Handler();

	    handler1.postDelayed(new Runnable() {
	         public void run() {
	        	finish();
	         }
	    }, 4000);
	    */

}
