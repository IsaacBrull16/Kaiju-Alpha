package com.projecte.kaiju.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.projecte.kaiju.R;

public class TermsConditionsActivity extends AppCompatActivity {

    TextView textView;
    String terms="Antes de utilizar nuestra aplicación, te invitamos a leer y aceptar los siguientes términos y condiciones que rigen el uso de nuestro juego:\n" +
            "\n" +
            "1. Aceptación de los Términos y Condiciones:\n" +
            "Al utilizar nuestra aplicación, aceptas y te comprometes a cumplir con los términos y condiciones establecidos en este documento. Si no estás de acuerdo con alguno de los términos, te pedimos que no utilices nuestra aplicación.\n" +
            "\n" +
            "2. Uso de la Aplicación:\n" +
            "Nuestro  juego está destinado únicamente para uso personal y entretenimiento. No debes utilizarla para ningún propósito ilegal, fraudulento o no autorizado. Te comprometes a cumplir con todas las leyes y regulaciones aplicables mientras utilices nuestra aplicación.\n" +
            "\n" +
            "3. Contenido y Propiedad Intelectual:\n" +
            "Todo el contenido presente en nuestra aplicación, incluyendo pero no limitado a gráficos, diseños, logotipos, texto, imágenes y software, es propiedad exclusiva de los propietarios del juego de cartas móvil y está protegido por leyes de derechos de autor y otras leyes de propiedad intelectual. No puedes copiar, modificar, distribuir o utilizar nuestro contenido sin nuestro consentimiento previo y por escrito.\n" +
            "\n" +
            "4. Privacidad:\n" +
            "Nos preocupamos por tu privacidad y seguridad. Al utilizar nuestra aplicación, aceptas nuestra política de privacidad, la cual puedes consultar en la sección correspondiente en la aplicación. Recopilamos y procesamos ciertos datos personales con el fin de proporcionarte una experiencia de juego óptima y mejorar nuestros servicios.\n" +
            "\n" +
            "5. Responsabilidad:\n" +
            "No nos hacemos responsables por cualquier daño, pérdida o perjuicio derivado del uso de nuestra aplicación. Tampoco nos hacemos responsables por cualquier interrupción en el servicio, errores técnicos o cualquier otra incidencia que pueda surgir durante el uso de la aplicación.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);
        textView=findViewById(R.id.scrollableTextView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(R.string.terms);

       findViewById(R.id.understoodButton).setOnClickListener(v -> finish());
    }


}