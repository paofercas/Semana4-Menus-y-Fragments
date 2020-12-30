package com.paocas.mascotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;

public class ContactoActivity extends AppCompatActivity {
    TextInputEditText tieNombre;
    TextInputEditText tieCorreo;
    TextInputEditText tieSubject;
    TextInputEditText tieMensaje;
    Button btnEnviar;
    Session session;
    String password;
    String correoEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tieNombre = (TextInputEditText)findViewById(R.id.tieNombre);
        tieCorreo = (TextInputEditText)findViewById(R.id.tieCorreo);
        tieSubject = (TextInputEditText)findViewById(R.id.tieSubjet);
        tieMensaje = (TextInputEditText)findViewById(R.id.tieMensaje);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        correoEmpresa=getResources().getString(R.string.correo_empresa);
        password= getResources().getString(R.string.password_correo);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Properties properties = new Properties();
                properties.setProperty("mail.smtp.host", "smtp.gmail.com");
                properties.setProperty("mail.smtp.socketFactory.port","465");
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.port", "465");
                try{
                    session= Session.getDefaultInstance(properties,new Authenticator(){
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(tieCorreo.getText().toString(),password);
                        }
                    });

                    if (session!= null){
                        Message message= new MimeMessage(session);
                        message.setFrom(new InternetAddress(tieCorreo.getText().toString()));
                        message.setSubject(tieSubject.getText().toString());
                        message.addRecipients(Message.RecipientType.TO,InternetAddress.parse(correoEmpresa));
                        message.setText(tieMensaje.getText().toString());
                        Transport.send(message);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.mLike:
                Intent intent = new Intent(this,SegundaActividad.class);
                startActivity(intent);
                //this.finish();
                break;
            case R.id.mContact:
                Intent i= new Intent(this, ContactoActivity.class);
                startActivity(i);
                break;
            case R.id.mAbout:
                Intent intent2= new Intent(this, AboutActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}