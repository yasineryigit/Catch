package com.example.acatch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText,timerText;
    int score,sayac,i;
    Runnable r,r2;
    Handler h,h2;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
    ImageView[] imageArray;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random = new Random();
        timerText =  findViewById(R.id.textView1);
        scoreText =  findViewById(R.id.textView2);
        imageView1 = findViewById(R.id.imageView1);
        imageView2= findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[]{imageView1,imageView2, imageView3,imageView4, imageView5, imageView6,imageView7,imageView8,imageView9};
        score=0;
        sayac=5;

        hideImage();

        //Geri sayım başladı
        h = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                sayac--;
                if(sayac>0){
                        timerText.setText("Time: " + sayac);
                        System.out.println(sayac);

                }else if(sayac==0){//süre bittiğinde
                    timerText.setText("Finished!");
                    h2.removeCallbacks(r2);
                    clearImages();
                    sor();
                }
                h.postDelayed(r, 1000);
            }};
        h.post(r);








    }

    public void tapped(View v){
        score++;
        scoreText.setText("Score: " + score);
    }


    public void hideImage(){

        //Kenny'nin gözükmesini sağlayacak runnable
        h2 = new Handler();
        r2=new Runnable() {
            @Override
            public void run() {

                clearImages();

                i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                h2.postDelayed(r2,500);
            }

        };
        h2.post(r2);


    }

    public void clearImages(){
        //Tüm resimleri görünmez yapacak olan kısım
        for(ImageView image : imageArray){
            image.setVisibility(View.INVISIBLE);
        }
    }

    public void sor(){
        AlertDialog.Builder sor = new AlertDialog.Builder(MainActivity.this);
        sor.setTitle("Skorunuz: " + score);
        sor.setMessage("Tekrar oynamak ister misiniz?");

        sor.setPositiveButton("Oyna", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                oynat();

            }
        });

        sor.setNegativeButton("Hayir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Son skorunuz: " + score, Toast.LENGTH_SHORT).show();
            }
        });

        sor.show();
    }

    public void oynat(){
        Intent i1 = getIntent();
        finish();
        startActivity(i1);
    }
}
