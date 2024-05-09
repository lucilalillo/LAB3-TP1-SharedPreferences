package com.lula.lab3_tp1_sharedpreferences.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lula.lab3_tp1_sharedpreferences.R;
import com.lula.lab3_tp1_sharedpreferences.databinding.ActivityMainBinding;
import com.lula.lab3_tp1_sharedpreferences.model.Usuario;
import com.lula.lab3_tp1_sharedpreferences.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = binding.etUsuario.getText().toString();
                String contrasenia = binding.etContrasenia.getText().toString();
                vm.Login(usuario, contrasenia);
            }
        });

        binding.btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creo un intent para iniciar la otra activity
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}