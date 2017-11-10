package autyzmsoft.pl.kolory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {

    ImageView bCzerwony, bCzarny, bZolty, bBialy, bZielony, bNiebieski;
    private int iZas; 				//identyfikator zasobu - pliku mp3
    private int trybFullScreen;  	//informuje, czy nacisnieto klawisz, a wiec CALY ekran jest jednego koloru
    MediaPlayer mp = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //na caly ekran:
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //


        setContentView(R.layout.activity_main);

        //pokazanie splash screena :
        if (savedInstanceState == null) {
            Intent splashKlasa = new Intent("autyzmsoft.pl.kolory.SplashKlasa");
            startActivity(splashKlasa);
        }

        //Rozwijam na caly ekran :
        try {
            getActionBar().hide(); //uwaga = API dependent - minimum 11
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Ustawienie zmiennych i listenerow :
        bCzerwony = (ImageView) findViewById(R.id.bCzerwony);
        bCzerwony.setOnClickListener(this);

        bCzarny   = (ImageView) findViewById(R.id.bCzarny);
        bCzarny.setOnClickListener(this);

        bBialy    = (ImageView) findViewById(R.id.bBialy);
        bBialy.setOnClickListener(this);

        bNiebieski= (ImageView) findViewById(R.id.bNiebieski);
        bNiebieski.setOnClickListener(this);

        bZielony  = (ImageView) findViewById(R.id.bZielony);
        bZielony.setOnClickListener(this);

        bZolty    = (ImageView) findViewById(R.id.bZolty);
        bZolty.setOnClickListener(this);

        trybFullScreen = 0;

    } //onCreate



    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }



 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        getMenuInflater().inflate(R.menu.main_komunikator, menu);
        return true;
    }*/



    @Override
    public void onClick(View arg0) {

        if (trybFullScreen == 0) {

            switch (arg0.getId()) {
                case R.id.bCzerwony:
                    ustawWszystkieNaKolor(Color.RED);
                    iZas = R.raw.czerwony;
                    break;
                case R.id.bCzarny:
                    ustawWszystkieNaKolor(Color.BLACK);
                    iZas = R.raw.czarny;
                    break;
                case R.id.bBialy:
                    ustawWszystkieNaKolor(Color.WHITE);
                    iZas = R.raw.bialy;
                    break;
                case R.id.bZielony:
                    ustawWszystkieNaKolor(Color.GREEN);
                    iZas = R.raw.zielony;
                    break;
                case R.id.bZolty:
                    ustawWszystkieNaKolor(Color.YELLOW);
                    iZas = R.raw.zolty;
                    break;
                case R.id.bNiebieski:
                    ustawWszystkieNaKolor(Color.BLUE);
                    iZas = R.raw.niebieski;
                    break;
            } //switch

			/*
			proby ponizej ok, ale na api=16 (za wysoko...) :
			Drawable tlo = arg0.getBackground();
			ustawWszystkieNaKolor(tlo);
			*/

            //Odegranie dzwieku:
            if (mp != null) {mp.release();}
            mp = MediaPlayer.create(this, iZas);

            Handler hSnd = new Handler();
            hSnd.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mp.start();
                }
            },600);

            //przywracamy wszystkie klawisze (= ich kolory) do pierwotnej postaci, po okresl. czasie (time-out):
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    przywrocWszystkieKolory();
                }
            }, 4000);
        }  //if
    } //koniec Metody()


    private void przywrocWszystkieKolory() {
        bCzerwony.setBackgroundColor(Color.RED);
        bZielony.setBackgroundColor(Color.GREEN);
        bZolty.setBackgroundColor(Color.YELLOW);
        bNiebieski.setBackgroundColor(Color.BLUE);
        bCzarny.setBackgroundColor(Color.BLACK);
        bBialy.setBackgroundColor(Color.WHITE);
        trybFullScreen = 0; //zezwalamy na zdarzenia OnClick
    }

    private void ustawWszystkieNaKolor(int kolor) {
        trybFullScreen = 1; //blokujemy zdarzenia OnClick
        bCzerwony.setBackgroundColor(kolor);
        bZielony.setBackgroundColor(kolor);
        bZolty.setBackgroundColor(kolor);
        bNiebieski.setBackgroundColor(kolor);
        bCzarny.setBackgroundColor(kolor);
        bBialy.setBackgroundColor(kolor);
    }

}
