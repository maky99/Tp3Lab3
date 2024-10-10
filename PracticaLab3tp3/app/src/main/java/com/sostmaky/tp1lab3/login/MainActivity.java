package com.sostmaky.tp1lab3.login;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.sostmaky.tp1lab3.R;
import com.sostmaky.tp1lab3.databinding.ActivityMainBinding;
import com.sostmaky.tp1lab3.model.Usuario;

import registro.RegistroActivity;
import registro.RegistroActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getPermisos();

        //boton para registrar
        binding.BtRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.mostarFormu();
            }
        });

        //boton login
        binding.btIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.leer(binding.etUsuario.getText().toString(),binding.etContraseA.getText().toString());

            }
        });

    }
    private void getPermisos() {

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
        }

    }
}