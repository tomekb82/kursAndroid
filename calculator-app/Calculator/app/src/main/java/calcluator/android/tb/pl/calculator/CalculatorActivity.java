package calcluator.android.tb.pl.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    /**
     * Ustawia na wyświetlaczu wybrany przycisk
     * @param view
     */
    public void keyClicked(View view) {

        Button button = (Button) view;

        String key =  button.getText().toString();
        TextView displayTextView = (TextView) findViewById(R.id.textView);
        displayTextView.setText(displayTextView.getText().toString() + key);
    }
}
