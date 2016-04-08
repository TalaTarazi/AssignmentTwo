package com.example.tala.assignmenttwo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    public String number1="" , number2="" ,text="0" ,memory="0";
    Boolean flag=true;
    int count = 0;//counter for opperations,
    double answer=0;
    int Ncounter=0;
    public   TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //clear textField;
    public void clearText(){
        if(text.length()==1){
            if(text.charAt(0)=='0'){
                text="";}}
    }


    public void calc(View v) {

        resultText  = (TextView) findViewById(R.id.answer);
        //if the text field has n1/0 then print cannot devide by 0;
        if(text.length()>=3&&text.charAt(text.length()-1)=='0'&&text.charAt(text.length()-2)=='÷'){
            resultText.setText("Cannot divide by zero!");
            text="0";
            count=0;
        }
        else if (v.getId() == R.id.btn0) {
            clearText();
            text = text + "0";
            resultText.setText(text);
        } else if (v.getId() == R.id.btn1) {
            clearText();
            text = text + "1";
            resultText.setText(text);
        } else if (v.getId() == R.id.btn2) {
            clearText();
            text = text + "2";
            resultText.setText(text);
        } else if (v.getId() == R.id.btn3) {
            clearText();
            text = text + "3";
            resultText.setText(text);
        } else if (v.getId() == R.id.btn4) {
            clearText();
            text = text + "4";
            resultText.setText(text);
        } else if (v.getId() == R.id.btn5) {
            clearText();
            text = text + "5";
            resultText.setText(text);
        } else if (v.getId() == R.id.btn6) {
            clearText();
            text = text + "6";
            resultText.setText(text);
        } else if (v.getId() == R.id.btn7) {
            clearText();
            text = text + "7";
            resultText.setText(text);
        } else if (v.getId() == R.id.btn8) {
            clearText();
            text = text + "8";
            resultText.setText(text);
        } else if (v.getId() == R.id.btn9) {
            clearText();
            text = text + "9";
            resultText.setText(text);
        }
        //btn C return everything to its default
        else if (v.getId()==R.id.btnC){
            text="0";
            resultText.setText("0");
            count=0;
            answer=0;
            number1="";
            number2="";}

        //clear one char;
        else if(v.getId()==R.id.btnBack){
            if(text.length()==1){
                text="0";
                resultText.setText(text);
                count=0;
                answer=0;
                number1="";
                number2="";

            }else{
                if(isOperation(text.charAt(text.length()-1)))
                    count--;
                text=text.substring(0,text.length()-1);
                resultText.setText(text);


            }}

        else if(v.getId()==R.id.btnN){
            if(text.charAt(0)!='0'){
                if(Ncounter%2==0){
                    text = '-' + text;
                    resultText.setText(text);
                    Ncounter++;}
                else{
                    text = text.substring(1,text.length());
                    resultText.setText(text);
                    Ncounter++;
                }

            }}
        //save the number on the textfield
        else if(v.getId()==R.id.btnM) {
            if(isOperation(text.charAt(text.length() - 1))){
                memory = text.substring(0,text.length()-1);

            }
            else {
                for (int i = 1; i < text.length(); i++) {
                    if (isOperation(text.charAt(i))) {
                        memory = text.substring(i+1,text.length());
                        flag = false;
                        break;
                    }

                }

                if (flag == true) {
                    memory = text;
                }}}

        else if(v.getId()==R.id.btnMR){
            if(text=="0"){
                text=memory;
                resultText.setText(text);
            }else{
                text = resultText.getText().toString() +memory;
                resultText.setText(text);
            }}

        else if(v.getId()==R.id.btnMC){
            memory="0";
        }
        else if(v.getId()==R.id.btnPls){
            text= text + '+';
            if(mulOperations()){
                count=1;}

            else{
                count++;
                if(count==1) {
                    resultText.setText(text);
                }
                else {
                    split();
                }}}else if(v.getId()==R.id.btnMin){
                      text = text + '-';
                          if(mulOperations()){
                              count=1;}
                          else{
                count++;
                if(count==1){
                    resultText.setText(text);
                }else{
                    split();
                }}
        }

        else if(v.getId()==R.id.btnMul){
            text = text + 'x';
            if(mulOperations()){
                count=1;}
            else{
                count++;
                if(count==1){
                    resultText.setText(text);
                }else{
                    split();
                }}}
        else if(v.getId()==R.id.btnDiv){
            text = text + '÷';
            if(mulOperations()){
                count=1;}
            else{
                count++;
                if(count==1){
                    resultText.setText(text);
                }else{
                    split();
                }}

        }
        else if(v.getId()==R.id.btneql){
            if(isOperation(text.charAt(text.length()-1))==false){
                for(int i =1; i<text.length();i++){
                    if(isOperation(text.charAt(i))){
                        number1 = text.substring(0,i);
                        number2 = text.substring(i+1,text.length());
                        answer= calculate(Double.parseDouble(number1),text.charAt(i),Double.parseDouble(number2));
                        resultText.setText(String.valueOf(answer));
                        text = String.valueOf(answer)+"";
                        count=0;
                        break;
                    }else continue;}}
        }

    }

    //split the text on the textfield to num1 num2 and operation then calculate.
    public void split(){

        for(int i =1; i<text.length();i++){
            if(isOperation(text.charAt(i))){
                number1 = text.substring(0,i);
                number2 = text.substring(i+1,text.length()-1);
                answer= calculate(Double.parseDouble(number1),text.charAt(i),Double.parseDouble(number2));
                resultText.setText(String.valueOf(answer) + text.charAt(text.length() - 1));
                text = String.valueOf(answer)+text.charAt(text.length()-1);count=1;
                break;}}}

    //if the user add multiple opperations one time it will take the last one
    public boolean mulOperations(){
        if(isOperation(text.charAt(text.length()-1))&&isOperation(text.charAt(text.length()-2))){
            text= text.substring(0,text.length()-2)+text.charAt(text.length()-1);

            resultText.setText(text);return true;
        }
        else return false;
    }

    public double calculate(double x,char op,double y){
        switch(op){
            case'+':return x+y;
            case'-':return x-y;
            case'x':return x*y;
            case'÷':{
                if(y!=0)
                    return x/y;

            }
            default:  return (int)Double.POSITIVE_INFINITY;
        }}

    public boolean isOperation(char x){//to check if the char is an operation
        if (x=='+'||x=='-'||x=='x'||x=='÷')
            return true;
        return false;
    }

}

