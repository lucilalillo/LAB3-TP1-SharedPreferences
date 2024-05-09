package com.lula.lab3_tp1_sharedpreferences.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lula.lab3_tp1_sharedpreferences.R;
import com.lula.lab3_tp1_sharedpreferences.databinding.ActivityRegistroBinding;
import com.lula.lab3_tp1_sharedpreferences.model.Usuario;

public class RegistroActivity extends AppCompatActivity {
    private RegistroActivityViewModel viewModel;
    private ActivityRegistroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long dni = Long.parseLong(binding.etDni.getText().toString());
                String nombre = binding.etNombre.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                String mail = binding.etMail.getText().toString();
                String pass = binding.etPassword.getText().toString();

                viewModel.guardar(nombre, apellido, dni, mail, pass);
            }
        });

        viewModel.getUsuarioMutable().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etNombre.setText(usuario.getNombre());
                binding.etApellido.setText(usuario.getApellido());
                binding.etDni.setText(usuario.getDni()+"");
                binding.etMail.setText(usuario.getMail());
                binding.etPassword.setText(usuario.getPassword());
            }
        });
        if(getIntent().getFlags() == Intent.FLAG_ACTIVITY_NEW_TASK)
        {
            viewModel.recuperarUsuario();
        }
    }

}