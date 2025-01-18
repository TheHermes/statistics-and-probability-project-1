package fi.arcada.sos22_1_raknare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class MainActivity extends AppCompatActivity {
    // Deklarera variabler före
    TextView outputText;
    // Tar ut namnet från inputen
    EditText inputText;
    // Tar ut datan från från inputen
    EditText inputData;
    // ArrayList för inmatade namn samt ålder
    ArrayList<DataItem> ageData = new ArrayList<>();
    // ArrayList för själva datan
    ArrayList<Double> ageDataValues = new ArrayList<>();
    RecyclerView recyclerView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Koppling mellan java kod och XML layout
        outputText = findViewById(R.id.outputText);
        inputText = findViewById(R.id.editTextName);
        inputData = findViewById(R.id.inputData);
        recyclerView = findViewById(R.id.dataItemsRecyclerView);

        adapter = new CustomAdapter(this,ageData);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        outputText.setText("Ange namn samt ålder för en person. Det krävs minst 4 personer för att få all statistik men desto mera data desto bättre statistik!");
    }

    public void calculate(View view) {
        String name = inputText.getText().toString();
        String data = inputData.getText().toString();
        // Kollar om man fyllt i fälten, om inte så krashar inte appen
        if (name.isEmpty() || data.isEmpty()) {
            outputText.setText("Fyll i båda fälten!");
            return;
        }

        // ArrayList med namn och värden för RecyclerView
        ageData.add(new DataItem(name, Double.parseDouble(data)));
        // ArrayList med värden för beräkningarna
        ageDataValues.add(Double.parseDouble(inputData.getText().toString()));
        // Uppdaterar recyclerview med ny data
        adapter.notifyDataSetChanged();

        //Tömmer input fälten
        inputText.setText("");
        inputData.setText("");

        // Visar datan om det finns 4 datamängder
        if(ageDataValues.size() < 4) {
            outputText.setText("Mera data behövs, åtminstone 4 stycken");
            return;
        }
        outputText.setText(String.format(Locale.ENGLISH,"Minsta värdet: %.2f\nStörsta värdet: %.2f\nMedelvärde: %.4f\nMedian: %.2f\nTypvärdet: %.2f\nStandardavvikelsen: %.2f\nNedre kvartilen: %.2f\nÖvre kvartilen: %.2f\nInre kvartilavståndet: %.2f\nDatamängd: %s"
                ,Statistics.calcMin(ageDataValues)      // Minsta värdet
                ,Statistics.calcMax(ageDataValues)      // Största värdet
                ,Statistics.calcAverage(ageDataValues)  // Medelvärdet
                ,Statistics.calcMedian(ageDataValues)   // Median
                ,Statistics.calcMode(ageDataValues)     // Typvärdet
                ,Statistics.calcStandDev(ageDataValues) // Standardavvikelsen
                ,Statistics.calcLQ(ageDataValues)       // Lägre kvartilen
                ,Statistics.calcUQ(ageDataValues)       // Övre kvartilen
                ,Statistics.calcQR(ageDataValues)       // Kvartilavståndet
                ,ageDataValues.size()                   // Datamängden
                )
        );
    }
}