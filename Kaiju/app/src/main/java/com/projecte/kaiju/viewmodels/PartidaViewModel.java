package com.projecte.kaiju.viewmodels;

import androidx.appcompat.app.AppCompatActivity;
import com.projecte.kaiju.R;
import android.os.Bundle;
import com.projecte.kaiju.views.Partida;

public class PartidaViewModel extends AppCompatActivity {
    private PartidaViewModel viewModel;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
        
        //viewModel = new ViewModelProvider(this).get(PartidaViewModel.class);
    }
}
