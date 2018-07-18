package com.khiraj.rentonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView rview;
    String myString = "Select Categories";
    Spinner sp1;
    Retrofit retrofit;
    String spinner_item;
    RentInterface ri;
    ArrayList<String> list = new ArrayList<String>();

    JSONObject object=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rview=findViewById(R.id.lview);
        sp1=findViewById(R.id.sp1);
        list.add(0,"select one");





         retrofit=new Retrofit.Builder()
                .baseUrl("https://www.rentoutonline.com").
                        addConverterFactory(GsonConverterFactory.create()).build();

        ri=retrofit.create(RentInterface.class);
        Call<Rent_main_pojo> interfaceCall = ri.getData();

        interfaceCall.enqueue(new Callback<Rent_main_pojo>() {
            @Override
            public void onResponse(Call<Rent_main_pojo> call, Response<Rent_main_pojo> response) {
                Log.e("Responce"," "+response.body().getData());

                if(response.isSuccessful()){

                    Rent_main_pojo pojo=response.body();
                    for (int i=0;i<pojo.getData().size();i++){
                        String category_name=pojo.getData().get(i).getCategoryName();
                    Log.e("rassssssi",category_name);
                        list.add(category_name);
                        ArrayAdapter<String> ats=new ArrayAdapter<>
                                (getApplicationContext(), android.R.layout.simple_selectable_list_item,list);
                        sp1.setAdapter(ats);
                        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position>0){
                                    Toast.makeText(MainActivity.this,sp1.getSelectedItem().
                                            toString(),Toast.LENGTH_SHORT).show();
                                     spinner_item=sp1.getSelectedItem().toString();

                                     ri=retrofit.create(RentInterface.class);
                                    Call<SubRent_main_pojo> interfaceCall = ri.getSubCategoriesData();
                                    interfaceCall.enqueue(new Callback<SubRent_main_pojo>() {
                                        @Override
                                        public void onResponse(Call<SubRent_main_pojo> call, Response<SubRent_main_pojo> response) {
                                            Log.e("REEEEEEE",""+response.body().getData());

                                            if (response.isSuccessful()){
                                                SubRent_main_pojo pojo1=response.body();
                                                for (int i=0;i<pojo1.getData().size();i++){
                                                    String asdf=pojo1.getData().get(i).getCategoryName();
                                                    Log.e("asdfghjjkl",asdf);
                                                    if(spinner_item.equals(asdf)){
                                                        String adsf=pojo1.getData().get(i).getSubCategoryName();
                                                        Log.e("LOGGGGG",adsf);
                                                    }
                                                }
                                            }



                                        }

                                        @Override
                                        public void onFailure(Call<SubRent_main_pojo> call, Throwable t) {

                                        }
                                    });


                                }}

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                    }


                }
            }

            @Override
            public void onFailure(Call<Rent_main_pojo> call, Throwable t) {

            }
        });


    }
}
