package com.example.cm.mc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ListView listview;      //달력
    TextListAdapter[][] adapter = new TextListAdapter[12][31];      //365일의 Data를 저장할 TextList배열

    /*
    Menu page, Inputpage가 열려있는 상태인지 닫혀있는 상태인지를 판별하기 위한 변수
     */
    boolean isPageOpen = false;
    boolean isPageOpen_i = false;
    boolean isPageOpen_m = true;

    boolean incomeORexpense = true;     // 입력하려고 하는 내역이 수입인지 지출인지를 판별

    Animation translateLeftAnim;        // Page들이 나타나는 animation
    Animation translateRightAnim;       // Page들이 사라지는 animation

    LinearLayout menu_page;     // 수입, 지출 입력 Page와 카메라 입력 Page를 선택할 수 있는 아이콘들을 모아 놓은 Page
    LinearLayout self_input_page;       // 수입, 지출을 직접 입력하는 Page
    LinearLayout menu_button_page;      // menu page가 나타나거나 사라지게 하는 버튼이 있는 page

    Button dateButton;      // 오늘의 날짜를 표시하는 버튼, 클릭시 달력으로 이동
    Button Menu;
    Button Self_input_income;   // 수입을 직접 입력하는 page를 불러오는 버튼
    Button Self_input_expense;  // 지출을 직접 입력하는 page를 불러오는 버튼
    Button Camera_input;        // 카메라로 입력하는 기능을 불러오는 버튼
    Button Add;     // self_input_page에서 입력한 Data를 저장하는 버튼

    EditText edit1;     //수입, 지출 내용 입력
    EditText edit2;     //수입, 지출 상세내용 입력
    EditText edit3;     //수입, 지출 금액 입력

    String data1;       // 수입,지출 내역 List의 내용
    String data2;       // 수입,지출 내역 List의 상세내용
    String data3;       // 수입,지출 내역 List의 금액

    float food;
    float transportation;
    float date_fee;
    float house_fee;
    float living_fee;

    public void inputIncome(int month, int date){   //incomeORexpense에 따라 수입또는 지출을 List에 저장

        if(incomeORexpense)     // incomeORexpense == true일 경우 수입
        {
            data1 = edit1.getText().toString();
            data2 = edit2.getText().toString();
            data3 = edit3.getText().toString();
        }
        else     // incomeORexpense == false일 경우 지출
        {
            data1 = edit1.getText().toString();
            data2 = edit2.getText().toString();
            data3 = "-"+edit3.getText().toString(); // 저장시 -x 의 값으로 저장
        }
        if(incomeORexpense){    // 수입 내용 list에 저장
            if(!data1.equals("")&&!data3.equals(""))
            {
                adapter[month-1][date-1].addItem(data1, data2, data3);
                edit1.setText("");
                edit2.setText("");
                edit3.setText("");

                edit1.setHint("    내용");
                edit2.setHint("    상세 내용");
                edit3.setHint("    원");
            }
            if(data1.equals("")||data3.equals(""))  // 입력이 완료되지 않은 상태로 add버튼을 누를경우 에러메세지 팝업
            {
                if(data1.equals("") && !data3.equals(""))
                    Toast.makeText(this,"내용을 입력하세요",Toast.LENGTH_LONG).show();
                if(!data1.equals("") && data3.equals(""))
                    Toast.makeText(this,"금액을 입력하세요",Toast.LENGTH_LONG).show();
                if(data1.equals("") && data3.equals(""))
                    Toast.makeText(this,"내용과 금액을 입력하세요",Toast.LENGTH_LONG).show();
            }

        }
        else{    // 지출 내용 list에 저장
            if(!data1.equals("")&&!data3.equals("-"))
            {
                adapter[month-1][date-1].addItem(data1, data2, data3);
                edit1.setText("");
                edit2.setText("");
                edit3.setText("");

                edit1.setHint("    내용");
                edit2.setHint("    상세 내용");
                edit3.setHint("    원");
            }
            if(data1.equals("")||data3.equals("-"))  // 입력이 완료되지 않은 상태로 add버튼을 누를경우 에러메세지 팝업
            {
                if(data1.equals("") && !data3.equals("-"))
                    Toast.makeText(this,"내용을 입력하세요",Toast.LENGTH_LONG).show();
                if(!data1.equals("") && data3.equals("-"))
                    Toast.makeText(this,"금액을 입력하세요",Toast.LENGTH_LONG).show();
                if(data1.equals("") && data3.equals("-"))
                    Toast.makeText(this,"내용과 금액을 입력하세요",Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateButton = (Button)findViewById(R.id.DateButton);

        Self_input_income = (Button)findViewById(R.id.self_input_income);
        Self_input_expense = (Button)findViewById(R.id.self_input_expense);
        self_input_page = (LinearLayout)findViewById(R.id.s_inputpage);
        Camera_input = (Button)findViewById(R.id.camera_input);
        Add = (Button)findViewById(R.id.input);

        menu_button_page = (LinearLayout)findViewById(R.id.menu_button_page);
        Menu = (Button)findViewById(R.id.menuButton);
        menu_page = (LinearLayout) findViewById(R.id.menuPage);



        edit1 = (EditText)findViewById(R.id.contents);
        edit2 = (EditText)findViewById(R.id.details);
        edit3 = (EditText)findViewById(R.id.price);

        /*
            달력 구성
        */
        final int date;
        final int month;
        final int year;

        Intent me = getIntent();
        final int[] check = new int[1];

        if(!me.hasExtra("check"))
        {
            check[0] = 0;
        }
        else
        {
            check[0] = me.getExtras().getInt("check");
        }
        if(check[0] != 1) {
            date = Calendar.getInstance().get(Calendar.DATE);
            month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            year = Calendar.getInstance().get(Calendar.YEAR);
        }
        else
        {
            date = me.getExtras().getInt("datedate");
            month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            year = Calendar.getInstance().get(Calendar.YEAR);
        }

        dateButton.setText(Integer.toString(year) + ". " + Integer.toString(month) + ". " + Integer.toString(date) + ".");

        /*
            파이 그래프 구현
        */
        PieChart chart = (PieChart)findViewById(R.id.chart);
        ArrayList <Entry> entries = new ArrayList<>();
        ArrayList <String> labels = new ArrayList<>();

        food = 100000;
        transportation = 50000;
        date_fee = 200000;
        house_fee = 20000;
        living_fee = 25000;
        entries.add(new Entry(food,0));
        entries.add(new Entry(transportation,1));
        entries.add(new Entry(date_fee,2));
        entries.add(new Entry(house_fee,3));
        entries.add(new Entry(living_fee,4));

        PieDataSet dataset = new PieDataSet(entries,"");
        labels.add("식 비") ;
        labels.add("교통비") ;
        labels.add("데이트") ;
        labels.add("관리비") ;
        labels.add("생필품") ;

        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        dataset.setValueTextSize(10.0f);
        PieData data = new PieData(labels, dataset);
        chart.setData(data);
        chart.animateY(1000);

        chart.setDescription("");
        // Pie chart는 구현은 했는데 그냥 어거지로 넣으니까 돌아간 느낌 좀더 연구하면 더 이쁘게 쓸수 있을지도

        listview = (ListView) findViewById(R.id.expenseList);

        adapter[month-1][date-1] = new TextListAdapter(this);

        //listview.setAdapter(adapter[month-1][date-1]);  // 수입, 지출 내역 list에 최초 내용을 초기화 -> 바꿔야함함
        loadView(year, month, date);
        dateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){        // 날짜 버튼을 클릭하면 달력이 있는 Month_page로 이동한다
            if(!isPageOpen_i) {
                final int m_year = year;
                final int m_month = month;

                Intent month_view = new Intent(getApplicationContext(), MonthActivity.class);
                month_view.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                month_view.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                month_view.putExtra("to_month_year", m_year);     // 현재 보고 있는 날짜의 년도값을 Month_page로 넘긴다
                month_view.putExtra("to_month_month", m_month);     // 현재 보고 있는 날짜의 월값을 Month_page로 넘긴다
                startActivity(month_view);
                finish();
                }
            }
        });

        // 여기서부턴 버튼 누르면 page들 나오는 부분

        Self_input_expense.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!isPageOpen_i)
                {
                    self_input_page.setVisibility(View.VISIBLE);
                    self_input_page.startAnimation(translateLeftAnim);
                    isPageOpen_i = true;
                    incomeORexpense = false;
                }
                menu_button_page.startAnimation(translateRightAnim);
            }
        });
        Self_input_income.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!isPageOpen_i)
                {
                    self_input_page.setVisibility(View.VISIBLE);
                    self_input_page.startAnimation(translateLeftAnim);
                    isPageOpen_i = true;
                    incomeORexpense = true;
                }
                menu_button_page.startAnimation(translateRightAnim);
            }
        });

        Add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                inputIncome(month, date);
                isPageOpen_i = false;
                self_input_page.setVisibility(View.INVISIBLE);
                self_input_page.startAnimation(translateRightAnim);
                menu_button_page.setVisibility(View.VISIBLE);

            }
        });
        Menu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (isPageOpen)
                {
                    menu_page.startAnimation(translateRightAnim);
                }
                else
                {
                    menu_page.setVisibility(View.VISIBLE);
                    menu_page.startAnimation(translateLeftAnim);
                }
            }
        });


        // 애니메이션 객체 로딩
        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        //애니메이션 객체에 리스너 설정
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);

    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        /**
         * 애니메이션이 끝날 때 호출되는 메소드
         */
        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                menu_page.setVisibility(View.INVISIBLE);
                menu_button_page.setVisibility(View.INVISIBLE);
                isPageOpen = false;
                isPageOpen_m = false;
            } else {
                isPageOpen = true;
            }
            if(isPageOpen==true&&isPageOpen_i==false){
                self_input_page.setVisibility(View.INVISIBLE);
                isPageOpen_i = false;
                isPageOpen_m = false;
            }
            if(!isPageOpen_i){
                self_input_page.setVisibility(View.INVISIBLE);
                menu_button_page.setVisibility(View.VISIBLE);
            }

        }
        public void onAnimationRepeat(Animation animation) {
        }
        public void onAnimationStart(Animation animation) {
        }

    }

    public void loadView(int year, int month, int date)
    {
        MCDBHandler MCDB = new MCDBHandler(this, null, null ,1);
        MCDB.InsertFirst();
        InputData a = MCDB.findASDF();

        adapter[month-1][date-1].addItem("내용", "상세내용", "금액");
        adapter[month-1][date-1].addItem("asdf", "asdf", "10000");
        listview.setAdapter(adapter[month-1][date-1]);
    }

}


