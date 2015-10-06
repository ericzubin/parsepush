package com.parsepush.conrads.parsepush;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    public SharedPreferences preferencias;
    public String UsrName =null;
    public String Password =null;


    public static final String usuario_VALUE_KEY = "Usuario";
    private EditText editName;
    private EditText ediPasword;
    private Button btn_ingresar;
    private Button btn_regis;
    //private CheckBox politicas;
    private Toast Msg_acepPoliticas;
    private Toast Msg_camposObli_tel;
    private Toast Msg_camposObli_nom;
    int request_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Para que no ingrese de nuevo sus datos
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            Log.d("Login ","No estaba iniciado");
        } else {
            // show the signup or login
            Log.d("Login ","Ya estaba iniciado");
            //Intent i = new Intent(this, usuarios.class);
            //startActivity(i);

        }

        editName=(EditText) findViewById(R.id.edit_user);
        ediPasword=(EditText) findViewById(R.id.edit_passWord);
        btn_ingresar=(Button) findViewById(R.id.btn_ingresar);
        btn_regis=(Button) findViewById(R.id.btn_registrar);

      btn_ingresar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              UsrName=editName.getText().toString();
              Password=ediPasword.getText().toString();
              Log.d("Botones","ingresar");
          ingresar();
              Log.d("Usuario: ", UsrName);
              Log.d("Password", Password);
              ParseUser.logInInBackground(UsrName, Password, new LogInCallback() {
                  @Override

                  public void done(ParseUser parseUser, ParseException e) {
                      Log.d("Ingresando", "al metodo poarse");
                      if (parseUser != null) {
                          // Hooray! The user is logged in.
                          Log.d("Ingresando", " metodo ingresar");
                          //Esta parte nos vincula nuestro dispositivo con nuestra cuenta
                          ParseInstallation installation = ParseInstallation.getCurrentInstallation();
                          installation.put("Usuarios", ParseUser.getCurrentUser());
                          installation.saveInBackground();
                          Intent i = new Intent(MainActivity.this, usuarios.class);
                          startActivity(i);


                      } else {
                          Log.d("No esta iniciando ", "");
                      }
                  }
              });

          }
      });



        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botones","Registar");
                UsrName=editName.getText().toString();
                Password=ediPasword.getText().toString();
                Log.d("Usuario: ",UsrName);
                Log.d("Password", Password);


                ParseUser user = new ParseUser();
                user.setUsername(UsrName);
                user.setPassword(Password);

// other fields can be set just like with ParseObject
                // user.put("phone", "650-253-0090");

                user.signUpInBackground(new SignUpCallback() {


                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Log.d("Ingresando", " metodo registar");
                            //Esta parte nos vicula nuestro dispositivo con nuestra cuenta
                            ParseInstallation installation = ParseInstallation.getCurrentInstallation();
                            installation.put("Usuarios", ParseUser.getCurrentUser());
                            installation.saveInBackground();
                            Intent i = new Intent(MainActivity.this, usuarios.class);

                            startActivity(i);


                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                        }
                    }
                });
            }
        });

    }
    protected void ingresar()
    {

    }
    protected void registrar()
    {

    }

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
