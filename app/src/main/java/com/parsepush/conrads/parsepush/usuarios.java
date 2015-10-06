package com.parsepush.conrads.parsepush;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.parse.FindCallback;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ERIC on 04/10/2015.
 */
//
public class usuarios extends Activity {
    private ListView list_usuarios;
    ParseQuery userQuery = ParseUser.getQuery();
    //private String[] colores;
    final ArrayList usuario = new ArrayList();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuarios);
        ListView lv = (ListView) findViewById(R.id.listView);
        new LoadAllProducts().execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                enviar(usuario.get(position).toString());

            }
        });

        /*//Esto ya funciona







               String usuario=user.getText().toString();
                // Find users near a given location
                ParseQuery userQuery = ParseUser.getQuery();

                userQuery.whereEqualTo("username", usuario);


                // Find devices associated with these users
                ParseQuery pushQuery = ParseInstallation.getQuery();
                pushQuery.whereMatchesQuery("Usuarios", userQuery);


                // Send push notification to query
                ParsePush push = new ParsePush();
                push.setQuery(pushQuery); // Set our Installation query
                push.setMessage("Free hotdogs at the Parse concession stand!");
                push.sendInBackground();
                Log.d("Eviando push","");



            }
        });
        */


//  ParseQuery userQuery = ParseUser.getQuery();


/*








////////////////////////////////

    ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<ParseObject>(this, "Instrument");
    adapter.setTextKey("name");
    adapter.setImageKey("photo");

    ListView listView = (ListView) findViewById(R.id.listview);
    listView.setAdapter(adapter);

    */
        }

    public void enviar(String user)
    {
        ParseQuery userQuery = ParseUser.getQuery();

        userQuery.whereEqualTo("username", user);


        // Find devices associated with these users
        ParseQuery pushQuery = ParseInstallation.getQuery();
        pushQuery.whereMatchesQuery("Usuarios", userQuery);


        // Send push notification to query
        ParsePush push = new ParsePush();
        push.setQuery(pushQuery); // Set our Installation query
        push.setMessage("Free hotdogs at the Parse concession stand!");
        push.sendInBackground();
        Log.d("Eviando push", "");
    }
    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        ProgressDialog pDialog = new ProgressDialog(usuarios.this);
        @Override
        protected void onPreExecute() {
            Log.d("paso", "entro al pdialog");
            super.onPreExecute();

            pDialog.setMessage("Analizando datos. Espere porfavor...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {


            ParseQuery<ParseUser> query = ParseUser.getQuery();
            query.whereDoesNotExist("tUKgzMsZ09LbLIIdwSZyIH1PVcu24aEcMpxcWH4A");
            query.findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> list, com.parse.ParseException e) {
                    if (e == null) {
                        // The query was successful.
                        for (int p = 0; p < list.size(); p++) {
                            // Log.d("usuario", list.get(p).getUsername().toString());
                            usuario.add(list.get(p).getUsername().toString());
                        }
                        Log.d("tamaño ", String.valueOf(list.size()));

                    } else {
                        // Something went wrong.
                        Log.d("List ", "wrong");
                    }
                }
            });



            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {



                    ArrayAdapter<String> adapter;
                    adapter=new ArrayAdapter<String>(usuarios.this,android.R.layout.simple_list_item_1,usuario);
                    ListView lv= (ListView) findViewById(R.id.listView);
                    lv.setAdapter(adapter);





                }

            });



        }
    }


    }
