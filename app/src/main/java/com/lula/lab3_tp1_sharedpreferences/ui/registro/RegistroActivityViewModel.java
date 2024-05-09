package com.lula.lab3_tp1_sharedpreferences.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lula.lab3_tp1_sharedpreferences.model.Usuario;
import com.lula.lab3_tp1_sharedpreferences.request.ApiClient;
import com.lula.lab3_tp1_sharedpreferences.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuarioMutable;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        //obtengo el contexto q necesito para pasarle a la api
        context = application.getApplicationContext();
    }
    public LiveData<Usuario> getUsuarioMutable()
    {
        if(usuarioMutable == null)
        {
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }
    public void guardar(String nombre, String apellido, long dni, String mail, String pass){
        Usuario usu = new Usuario(dni, nombre, apellido, mail, pass);
        ApiClient.guardar(context, usu);
    }

    public void recuperarUsuario()
    {
        Usuario usu = ApiClient.leer(context);
        if(usu != null)
        {
            usuarioMutable.setValue(usu);
        }
        else{
            Toast.makeText(getApplication(), "No hay datos", Toast.LENGTH_LONG).show();
        }
    }
}
