package com.game1;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //가위바위보 이미지 저장
    int[] image = { R.drawable.img1,R.drawable.img2,R.drawable.img3};

    // 가위 바위 보 값
    int you = 0;
    int com = 0;

    // 승패 횟수
    int win = 0;
    int lose = 0;

    //결과 표시용 이미지
    ImageView imgViewYou;
    ImageView imgViewCom;

    //승패 횟수 표시용
    TextView txtYou;
    TextView txtCom;

    //판정 결과
    TextView txtResult;
    Button.OnClickListener onButtonClick = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            //equals사용 안해도 되나?
            //객체 비교?
            if( v.getId() == R.id.fab) {
                return;
            } else {
                String tag = v.getTag().toString();
                you = Integer.parseInt(tag);
                setGameResult();
            }
        }
    };

    //게임 승패 처리
    private void setGameResult() {
        //컴퓨터 가위바위보 값 결정
        com = new Random().nextInt(3);
        int k = you - com;

        //승패 판정
        String str = "";
        if ( k == 0 ){
            str = "무승부입니다.";
        } else if(k == 1 || k == -2) {
            str = "승리하셨습니다.";
            win++;
        } else {
            str = "패배하셨습니다.";
            lose++;
        }

        //이미지 표시
        SetImages();

        //결과 표시
        txtYou.setText("당신 : " + win);
        txtCom.setText("단말기 : " + lose);
        txtResult.setText(str);
    }

    //이미지 표시
    private void SetImages() {
        imgViewYou.setImageResource(image[you]);
        imgViewCom.setImageResource(image[com]);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //id값(객체)별로 event리스너를 주입하는 것??
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        for(int i : image) {
            //자동으로 뒷자리만 더해주는 것?
            findViewById(R.id.imageButton0 + i).setOnClickListener(onButtonClick);
        }

        imgViewYou = (ImageView)findViewById(R.id.imageView3);
        imgViewCom = (ImageView)findViewById(R.id.imageView4);

        txtYou = (TextView)findViewById(R.id.textView3);
        txtCom = (TextView)findViewById(R.id.textView4);
        txtResult = (TextView)findViewById(R.id.textView5);
    }

    //초기화
    private void initGame(){
        win = 0;
        lose = 0;

        txtYou.setText("당신 : 0");
        txtCom.setText("당신 : 0");
        txtResult.setText("");

        imgViewYou.setImageResource(R.drawable.img4);
        imgViewCom.setImageResource(R.drawable.img4);
    }
    //옵션 메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
